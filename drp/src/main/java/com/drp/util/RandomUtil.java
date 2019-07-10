package com.drp.util;

/**
 *  * Created with IntelliJ IDEA.
 *  * User: dell
 *  * Date: 2017/11/14
 *  * Time: 10:02
 *  * @作用：生成6位随机数
 *  
 */
public class RandomUtil {

    public static int getRandom() {
        return (int)((Math.random()*9+1)*100000);
    }

    public static void main(String[] args) {
        System.out.println(getRandom());
    }
}
