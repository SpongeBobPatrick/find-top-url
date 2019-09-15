package com.top.gen;

/**
 * @author zlm
 * @date 2019-09-15 18:33
 * @description  generate a url string randomly
 */

public class UrlUtil {
    private static final String[] url_suffix = ".com,.cn,.gov,.edu,.net,.org,.int,.mil,.biz,.info".split(",");

    public static String base = "abcdefghijklmnopqrstuvwxyz0123456789";

    public static int getNum(int start, int end)
    {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    /***
     * 返回url
     * @param lMin     最小长度
     * @param lMax     最大长度
     * @return
     */
    public static String getUrl(int lMin, int lMax) {
        int length = getNum(lMin, lMax);
        StringBuffer sb = new StringBuffer();
        sb.append("http://");
        for (int i = 0; i < length; i++) {
            int number = (int) (Math.random() * base.length());
            sb.append(base.charAt(number));
        }
        sb.append(url_suffix[(int) (Math.random() * url_suffix.length)]);
        return sb.toString();
    }
    
}
