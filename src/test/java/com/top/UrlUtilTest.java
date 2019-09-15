package com.top;

import com.top.gen.UrlUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zlm
 * @date 2019-09-15 18:51
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest

public class UrlUtilTest {
    @Test
    public void getUrlTest()
    {
        int min = 5;
        int max = 1000;
        for(int i = 0;i<20;i++)
        {
            System.out.println(UrlUtil.getUrl(min,max));
        }
    }

}
