package com.top.controller;

import com.top.findtop.GetTopUrl;
import com.top.findtop.SplitUrlFile;
import com.top.gen.CreateUrlFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

/**
 * @author zlm
 * @date 2019-09-16 16:15
 * @description Control layer accesses API
 */
@RestController
@RequestMapping("/topUrl")
public class TopUrlController {
    /**
     * create 100GB url files API
     * @throws FileNotFoundException
     */
    @GetMapping("/createFile")
    public void createFile() throws FileNotFoundException {
        CreateUrlFile.createRulFile(5, 1000);
    }

    /**
     * find top 100 urls API
     */
    @GetMapping("/fintTopUrl")
    public void findTopUrl(){
        long startTime = System.currentTimeMillis();
        //split url files into multiple sub files
        SplitUrlFile.splitUrlFile();
        //get top 100 urls and their occurrences using minHeap
        GetTopUrl.getTopUrl(100);
        long endTime = System.currentTimeMillis();
        System.out.println("Milliseconds of find top 100 urls: " + (endTime - startTime));
    }
}
