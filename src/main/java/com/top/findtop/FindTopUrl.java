package com.top.findtop;

/**
 * @author zlm
 * @date 2019-09-15 18:32
 * @description   find top k urls which repeat most
 */

public class FindTopUrl {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        //拆分url文件，生成子文件
        SplitUrlFile.splitUrlFile();
        //所有子文件排序，获取top100；
        long endTime = System.currentTimeMillis();
        System.out.println("total Seconds: " + (endTime - startTime) / 1000);

    }
}
