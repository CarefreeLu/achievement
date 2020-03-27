package com.nuonuo.trade.constant;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 功能描述：TODO
 *
 * @author Jianhui Lu
 * @createtime 2019/8/5 16:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
@EnableConfigurationProperties({PlatformConfig.class})
@ComponentScan(basePackages = "com.nuonuo.trade.constant")
public class PlatformConfigTest
{
    @Autowired
    private PlatformConfig platformConfig;

    @Test
    public void getProperty()
    {
        System.out.println(JSONObject.toJSONString(platformConfig));
    }
}