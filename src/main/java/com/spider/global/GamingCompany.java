package com.spider.global;

import java.util.HashMap;
import java.util.Map;

/**
 * 关注的博彩公司
 *
 * @author ronnie
 */
public enum GamingCompany {

    JinBaoBo("23", "金宝博"), //真正的金宝博是23，24是ibc，由于改变公司名称程序改动量较大，所以将此id改为24
    LiJi("31", "利记"),
    Crown("3", "皇冠"),
    ibcbet("24", "ibcbet");
    
    private static Map<String, String> name2Abbr = new HashMap<String, String>();
    
    static {
    	name2Abbr.put("金宝博", "jbb");
    	name2Abbr.put("利记", "lj");
    	name2Abbr.put("皇冠", "crown");
    	name2Abbr.put("ibcbet", "ibcbet");
    }

    GamingCompany(String id, String name) {

        this.id = id;
        this.name = name;
    }

    private String id;

    private String name;

    public String getId() {

        return id;
    }

    public String getName() {

        return this.name;
    }

    public static String abbr(String company) {

        return name2Abbr.get(company);
    }

}
