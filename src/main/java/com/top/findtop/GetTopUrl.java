package com.top.findtop;

import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author zlm
 * @date 2019-09-16 16:09
 * @description
 */

public class GetTopUrl {
    private static String subFilePath = "data/subFiles";

    /**
     * read the sub files in turn and insert them into the minHeap
     * @param topK
     * @return
     */
    public static ArrayList<UrlCount> getTopUrl(int topK) {
        long startTime = System.currentTimeMillis();
        ArrayList<UrlCount> topUrls = null;
        File subFileDir = new File(subFilePath);
        //get all sub files
        File[] files = subFileDir.listFiles();
        if(files != null){
            try {
                BufferedReader reader = null;
                String url = null;
                UrlCount urlCount = null;
                String [] urlAndCount = null;
                TopK tk = new TopK(topK);
                for (File file : files){
                    reader = new BufferedReader(new FileReader(file));
                    url = reader.readLine();
                    while (null != url){
                        urlAndCount = url.split(",");
                        urlCount = new UrlCount();
                        urlCount.setUrl(urlAndCount[0]);
                        urlCount.setCount(Long.valueOf(urlAndCount[1]));
                        tk.insert(urlCount);
                        url = reader.readLine();
                    }
                }
                topUrls = tk.getTopUrls();
                if(!CollectionUtils.isEmpty(topUrls)){
                    for (UrlCount count : topUrls){
                        System.out.println("count:"+count.getCount()+",url："+count.getUrl());
                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Millisecond of get topUrl: " + (endTime - startTime));
        return topUrls;
    }
}
