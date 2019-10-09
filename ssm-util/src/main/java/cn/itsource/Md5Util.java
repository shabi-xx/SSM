package cn.itsource;

import org.apache.shiro.crypto.hash.SimpleHash;

public class Md5Util {
    public static final String SALT = "hzw";
    public static final Integer ITERATION = 10;
    public static String getCode(String code){
        SimpleHash simpleHash = new SimpleHash("md5",code,SALT,ITERATION);
        return simpleHash.toString();
    }
}
