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
 * 1.Client.builder().endpoints(x,x,x)
 * 2.ni.getName().equals("xxx")
 * 3.ipAds.get(x);
 */

public class RegisterOnEtcd {
	
	private static final ByteSequence KEY = ByteSequence.fromString("spider-web");
	private static ByteSequence value;
	private static Client client = Client.builder().endpoints("http://192.168.137.100:2379", "http://192.168.137.101:2379", "http://192.168.137.102:2379").build();
	private static KV kvClient = client.getKVClient();
	private static Lease leaseClient = client.getLeaseClient();
	private static Long leaseId;
	
	private static void myWatch() {
		Watcher watcher=client.getWatchClient().watch(KEY, WatchOption.newBuilder().withNoPut(true).withPrevKV(true).build());
    	try {
    		watcher.listen();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void myRefresh() {
		Thread myRefreshThreaad = new Thread(new MyRefresh());
		myRefreshThreaad.setDaemon(true);
		myRefreshThreaad.start();
	}
	
	//此方法是否会更方便？
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
		return "ip not found";
	}
		
	static class MyRefresh implements Runnable {
    	public void run() {
    		try {
    			while (true) {
    				Thread.sleep(1500L);
    				leaseClient.keepAliveOnce(leaseId);
    			}
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    }
	
	public static void register() throws InterruptedException, ExecutionException, UnknownHostException{
		String ip = getIp();
		value = ByteSequence.fromString(ip);		
		do {
			if (kvClient.get(KEY).get().getCount() != 0  ) {
				myWatch();
			}
			if (kvClient.get(KEY).get().getCount() != 0 ) continue;
			leaseId =leaseClient.grant(3L).get().getID();
			kvClient.put(KEY, value, PutOption.newBuilder().withLeaseId(leaseId).build());
			Thread.sleep(1000L);
		} while (kvClient.get(KEY).get().getCount() == 0 || !kvClient.get(KEY).get().getKvs().get(0).getValue().equals(value));	
		myRefresh();
    }
}
