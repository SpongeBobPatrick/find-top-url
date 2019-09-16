package com.top.findtop;

import java.io.*;
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
            //create the sub files in turn
            subFile = new File(subFilePath+"/" + modKey);
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
