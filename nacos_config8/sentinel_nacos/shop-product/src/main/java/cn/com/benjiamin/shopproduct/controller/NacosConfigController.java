package cn.com.benjiamin.shopproduct.controller;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * created   NacosConfigController  by benjiamin at 2022/6/4 / 10:47 /47
 */

@RestController
public class NacosConfigController {
    @Resource
    private ConfigurableApplicationContext applicationContext;
    @RequestMapping(value="/test-config1",method = RequestMethod.GET)
        public String testConfig1() {
       return     applicationContext.getEnvironment().getProperty("spring.ben.config.name");

            }
}
