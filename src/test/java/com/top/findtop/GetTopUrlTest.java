package com.top.findtop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zlm
 * @date 2019-09-17 22:29
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GetTopUrlTest {
    /**
     * split url files into multiple sub files
     */
    @Test
    public void splitUrlFileTest()
    {
        long startTime = System.currentTimeMillis();
        SplitUrlFile.splitUrlFile();
        long endTime = System.currentTimeMillis();
        System.out.println("Milliseconds of splitUrlFileTest: " + (endTime - startTime));
    }

    /**
     * merge the occurrences of identical urls in sub files
     */
    @Test
    public void mergeSubFileTest()
    {
        long startTime = System.currentTimeMillis();
        SplitUrlFile.mergeSubFile();
        long endTime = System.currentTimeMillis();
        System.out.println("Milliseconds of mergeSubFileTest: " + (endTime - startTime));
    }

    /**
     * get top 100 urls and their occurrences using minHeap
     */
    @Test
    public void getTopUrlTest()
    {
        long startTime = System.currentTimeMillis();
        GetTopUrl.getTopUrl(100);
        long endTime = System.currentTimeMillis();
        System.out.println("Milliseconds of getTopUrlTest: " + (endTime - startTime));
    }
}
