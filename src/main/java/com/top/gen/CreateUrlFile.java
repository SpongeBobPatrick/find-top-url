package com.top.gen;

import java.io.*;
import java.util.ArrayList;

/**
 * @author zlm
 * @date 2019-09-15 19:15
 * @description Iterate over the url arraylist and write it to the file
 */

public class CreateUrlFile {
    public static void createRulFile(int min, int max) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        //url 文件输出地址
        File outputFile = new File("data/urlFile");
        //指定生成5万个IP
        int ipCount = 50000;
        ArrayList<String> urls = new ArrayList<>(ipCount);
        for (int i = 0; i < ipCount; i++) {
            urls.add(UrlUtil.getUrl(min, max));
        }
        outputFile.getParentFile().mkdirs();
        FileOutputStream fos = new FileOutputStream(outputFile, false);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(fos));

        //随机从50000个IP中取200000000次
        int totalCount = 20000000;
        for (int i=0; i<totalCount; i++){
            int index = UrlUtil.getNum(0, ipCount - 1);
            String url = urls.get(index);
            out.print(url + "\n");
        }
        out.flush();
        out.close();
        long endTime = System.currentTimeMillis();
        System.out.println("total Seconds: " + (endTime - startTime) / 1000);
    }


}
