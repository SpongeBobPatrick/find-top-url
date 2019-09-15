package com.top.gen;

import java.io.FileNotFoundException;

/**
 * @author zlm
 * @date 2019-09-15 18:29
 * @description The entry function to generate data
 */

public class GenerateData {
    public static void main(String[] args) {
        try {
            CreateUrlFile.createRulFile(5,1024);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
