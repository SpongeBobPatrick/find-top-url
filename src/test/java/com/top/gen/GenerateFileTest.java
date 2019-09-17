package com.top.gen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;

/**
 * @author zlm
 * @date 2019-09-17 22:23
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GenerateFileTest {
    @Test
    public void createFileTest()
    {
        try {
            CreateUrlFile.createRulFile(5,1024);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
