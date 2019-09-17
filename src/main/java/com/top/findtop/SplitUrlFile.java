package com.top.findtop;

import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author zlm
 * @date 2019-09-15 22:51
 * @description Read the large file and split it into 1000 small files
 */

public class SplitUrlFile {
    //100GB size of the original file
    private static String urlFilePath = "data/urlFile";
    //the directory stores sub files
    private static String subFilePath = "data/subFiles";
    private static Map<Integer,Map<String,Long>> allUrlMap = new HashMap<>();

    /**
     * split original file
     */
    public static void splitUrlFile()
    {
        File urlfile = new File(urlFilePath);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(urlfile));
            String url = reader.readLine();
            int modNum = 0;
            Map<String,Long> modMap = null;
            while(url != null)
            {
                //hash the url string
                modNum = SDBMHash(url);
                if(allUrlMap.containsKey(modNum))
                {
                    modMap = allUrlMap.get(modNum);
                    if(modMap.containsKey(url))
                    {
                        modMap.put(url,modMap.get(url) + 1);
                    }else{
                        modMap.put(url,1L);
                    }
                    allUrlMap.put(modNum,modMap);
                    if(modMap.size() >= 500)
                    {
                        writeToSubFile(modNum,modMap,true);
                        allUrlMap.remove(modNum);
                    }
                }else{
                    modMap = new HashMap<>();
                    modMap.put(url,1L);
                    allUrlMap.put(modNum,modMap);
                }
                url = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        createSubFile();
    }

    /**
     * write or append the urls and their occurrences into the sub file
     * @param modNum
     * @param modMap
     * @param ifappend
     */
    public static void writeToSubFile(Integer modNum,Map<String,Long> modMap,Boolean ifappend)
    {
        File subFile = new File(subFilePath);
        if(!subFile.exists())
        {
            subFile.mkdirs();
        }
        FileOutputStream fos = null;
        //create the sub files in turn
        subFile = new File(subFilePath+"/" + modNum);

        try {
            fos = new FileOutputStream(subFile,ifappend);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(fos));
            Set<String> urlKeys = modMap.keySet();
            for(String urlKey:urlKeys)
            {
                //write url and the number of occurrences, seperate with ","
                out.print(urlKey + "," + modMap.get(urlKey) + "\n");
            }
            urlKeys.clear();
            modMap.clear();
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void createSubFile()
    {
        File subFile = new File(subFilePath);
        if(!subFile.exists())
        {
            subFile.mkdirs();
        }
        Set<Integer> modKeys = allUrlMap.keySet();
        FileOutputStream fos = null;
        Map<String,Long> modMap = null;
        Set<String> urlKeys = null;
        for(Integer modKey: modKeys)
        {
            writeToSubFile(modKey,allUrlMap.get(modKey),true);
            //create the sub files in turn
            /*subFile = new File(subFilePath+"/" + modKey);
            try {
                fos = new FileOutputStream(subFile,false);
                PrintWriter out = new PrintWriter(new OutputStreamWriter(fos));
                //the content write into the sub files
                modMap = allUrlMap.get(modKey);
                urlKeys = modMap.keySet();
                for(String urlKey:urlKeys)
                {
                    //write url and the number of occurrences, seperate with ","
                    out.print(urlKey + "," + modMap.get(urlKey) + "\n");
                }
                urlKeys.clear();
                modMap.clear();
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }*/
        }
    }
    public static void mergeSubFile()
    {
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

                Map<String,Long> modMap = null;
                for (File file : files){
                    modMap = new HashMap<>();
                    reader = new BufferedReader(new FileReader(file));
                    url = reader.readLine();
                    while (null != url){
                        urlAndCount = url.split(",");
                        if(modMap.containsKey(urlAndCount[0]))
                        {
                            modMap.put(urlAndCount[0],Long.valueOf(urlAndCount[1])+ modMap.get(urlAndCount[0]));
                        }else {
                            modMap.put(urlAndCount[0], Long.valueOf(urlAndCount[1]));
                        }
                        url = reader.readLine();
                    }
                    //write the merge result into this sub file
                    String filename = file.getName();
                    //filename = filename.substring(0,filename.lastIndexOf("."));
                    writeToSubFile(Integer.valueOf(filename),modMap,false);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * hash url
     * @param url
     * @return
     */
    public static int SDBMHash(String url)
    {
        return (url.hashCode()&  0x7FFFFFFF) % 1000;
    }
}
