package com.spider.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.coreos.jetcd.Client;
import com.coreos.jetcd.KV;
import com.coreos.jetcd.Lease;
import com.coreos.jetcd.Watch.Watcher;
import com.coreos.jetcd.data.ByteSequence;
import com.coreos.jetcd.options.PutOption;
import com.coreos.jetcd.options.WatchOption;
import com.google.common.collect.Lists;

/**
 * 需要根据不同环境修改下列xxx的位置：
 * 1.Client.builder().endpoints(x,x,x)  x,x,x分别是运行着etcd的服务器ip与对应端口号
 * 2.ni.getName().equals("xxx")
 * 3.ipAds.get(x);
 *  
 * 关于后两项的例子：比如我的虚拟机上通过“ifconfig”看到网卡名为“ens33”，并且ipAds上第二项是IP地址，则我应该写为
 * 2.ni.getName().equals("ens33")
 * 3.ipAds.get(1);
 * 
 * jetcd的用法一般如下:
 * 1.声明client,指定运行着etcd的服务器ip与对应端口号
 * 2.根据自己的需要get具体client，比如该demo要进行键值的存储，故必申明kvClient
 * 3.运用具体client所具备的方法
 */

public class RegisterOnEtcd {
	//将服务名最为key存到etcd上
	private static final ByteSequence KEY = ByteSequence.fromString("spider-web");
	private static ByteSequence value;
	private static Client client = Client.builder().endpoints("http://192.168.137.100:2379", "http://192.168.137.101:2379", "http://192.168.137.102:2379").build();
	private static KV kvClient = client.getKVClient();
	private static Lease leaseClient = client.getLeaseClient();
	private static Long leaseId;
	
	private static void myWatch() {
		/* 设置watcher。
		 * watcher的意思是持续监听，如果没有监听到指定key有任何变化则持续监听，直到监听到key值有任何变化则不再监听，结束watcher，程序继续往下走。
		 * 1.watcher的声明方法在WatchClient下，所以需要先get。
		 * 2.添加watcher条件，即WatchOption。
		 * 	    withNoPut方法意为不监听put变化。即只有监听到节点del后程序才继续往下走，若监听到的是节点put则继续watch。
		 * 		withPrevKV方法意为在watcher结束时可以获得到变化之前的Key-value（在此demo中貌似没什么用）
		 */
		Watcher watcher=client.getWatchClient().watch(KEY, WatchOption.newBuilder().withNoPut(true).withPrevKV(true).build());
    	try {
    		//启动watcher
    		watcher.listen();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/* 创建守护进程，以持续刷新租约
	 * 如果服务正在正常运行，则守护进程不断
	 * 如果服务终止，则守护进程也终止，不再刷新租约。则3秒内，spider-web节点过期删除。
	 */
	private static void myRefresh() {
		
		Thread myRefreshThreaad = new Thread(new MyRefresh());
		myRefreshThreaad.setDaemon(true);
		myRefreshThreaad.start();
	}
	
	//获取本机IP的方法是否可以改得更方便？
	private static String getIp() {
		Enumeration<NetworkInterface> netInterfaces = null; 
		try {  
			netInterfaces = NetworkInterface.getNetworkInterfaces(); 
		    while (netInterfaces.hasMoreElements()) {  
		        NetworkInterface ni = netInterfaces.nextElement();   
//		        System.out.println("Name:" + ni.getName());//查看对应Name，更改下面ni.getName().equals(str)中的参数str
		        if (ni.getName().equals("ens33")) { 
			        Enumeration<InetAddress> ips = ni.getInetAddresses();
			        List<String> ipAds = Lists.newLinkedList();
			        while (ips.hasMoreElements()) { 
			        	ipAds.add(ips.nextElement().getHostAddress());
//			            System.out.println("IP:"  
//			            + ips.nextElement().getHostAddress());//查看对应IP,更改下面get(n)中的参数n
			        }
			        return ipAds.get(1);
		        }
		    }  
		} catch (Exception e) {  
		    e.printStackTrace();  
		}
		//没有获取到ip，一般能走到这是代码最开头的注释中的 1. 2. 3.配置错误。
		return "ip not found";
	}
		
	static class MyRefresh implements Runnable {
    	public void run() {
    		try {
    			while (true) {
    				//每1.5秒刷新
    				Thread.sleep(1500L);
    				//刷新租约，过期时间变回3秒
    				leaseClient.keepAliveOnce(leaseId);
    			}
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    }
	
	public static void register() throws InterruptedException, ExecutionException, UnknownHostException{
		// 获得本机ip，以便作为value存到etcd上
		String ip = getIp();
		value = ByteSequence.fromString(ip);		
		do {
			// 先检查 /spider-web结点是否存在。若存在说明当前正有其他机器提供服务。若不存在说明没有机器提供服务。
			if (kvClient.get(KEY).get().getCount() != 0  ) {
				// 检测到有其他机器提供服务，进行持续监听。知道监听到任何变化才往下走
				myWatch();
			}
			// 检测到没有其他机器提供服务，进行一下操作
			//leaseClient.grant(3L)用来生成3秒租约，租约生成后需要获取租约id。
			leaseId =leaseClient.grant(3L).get().getID();
			//将spider-web作为key，将本机IP作为value，并将其写入刚才的3秒租约中。一并存入etcd中。
			kvClient.put(KEY, value, PutOption.newBuilder().withLeaseId(leaseId).build());
			//put方法比较耗时，需要暂等1秒
			Thread.sleep(1000L);
			/*put之后，因为会有一些网络延迟和多台服务器争用etcd情况，需要进行以下判断
			 * 1.spider-web节点是否已成功生成。
			 * 2.如果spider-web节点已成功生成，查看spider-web节点所存是否是本机的ip。
			 */
		} while (kvClient.get(KEY).get().getCount() == 0 || !kvClient.get(KEY).get().getKvs().get(0).getValue().equals(value));	
		//已经成功将本机ip注册到/spider-web节点。但有小于3秒的过期时间，需要执行下面的刷新租约的方法。
		myRefresh();
    }
}
