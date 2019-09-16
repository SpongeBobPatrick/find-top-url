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
        //output file
        File outputFile = new File("data/urlFile");
        //generate 50,000 urls by default
        int urlCount = 50000;
        ArrayList<String> urls = new ArrayList<>(urlCount);
        for (int i = 0; i < urlCount; i++) {
            urls.add(UrlUtil.getUrl(min, max));
        }
        outputFile.getParentFile().mkdirs();
        FileOutputStream fos = new FileOutputStream(outputFile, false);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(fos));

        //take 200,000,000 times randomly from 50000 urls
        //so the output file is almost 100GB(0.5B * 200000000)
        int totalCount = 200000000;
        for (int i=0; i<totalCount; i++){
            int index = UrlUtil.getNum(0, urlCount - 1);
            String url = urls.get(index);
            out.print(url + "\n");
        }
        out.flush();
        out.close();
        long endTime = System.currentTimeMillis();
        System.out.println("generate url file total Seconds: " + (endTime - startTime) / 1000);
    }


}
