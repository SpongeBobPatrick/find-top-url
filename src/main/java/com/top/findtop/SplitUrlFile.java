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
    private static String urlFilePath = "data/urlFile";
    private static String subFilePath = "data/subFiles";
    private static Map<Integer,Map<String,Long>> allUrlMap = new HashMap<>();
    public static void splitUrlFile()
    {
        //读取文件
        File urlfile = new File(urlFilePath);
        //写入文件
        try {
            BufferedReader reader = new BufferedReader(new FileReader(urlfile));
            String url = reader.readLine();
            int modNum = 0;
            Map<String,Long> modMap = null;
            while(url != null)
            {
                //根据url字符串进行hash
                modNum = SDBMHash(url);
                //判断modNum是否存在于allUrlMap中
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
        //添加目录
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
            subFile = new File(subFilePath+"/" + modKey);
            try {
                //创建子文件
                fos = new FileOutputStream(subFile,false);
                PrintWriter out = new PrintWriter(new OutputStreamWriter(fos));
                //获取子文件写入的内容
                modMap = allUrlMap.get(modKey);
                urlKeys = modMap.keySet();
                for(String urlKey:urlKeys)
                {
                    //往子文件写入url及对应数量，用“，”分割
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

    //对url进行hash取模
    public static int SDBMHash(String url)
    {
        return (url.hashCode()&  0x7FFFFFFF) % 1000;
    }
}
