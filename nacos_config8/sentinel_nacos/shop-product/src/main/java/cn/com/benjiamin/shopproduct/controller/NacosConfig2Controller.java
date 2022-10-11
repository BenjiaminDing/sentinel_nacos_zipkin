package cn.com.benjiamin.shopproduct.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * created   NacosConfigController  by benjiamin at 2022/6/4 / 10:47 /47
 */

@RestController
@RefreshScope
public class NacosConfig2Controller {

    @Value("${spring.ben.config.name}")
    private String benConfigName;

    @Value("${env}")
    private String env;

    @RequestMapping(value = "/test-config2", method = RequestMethod.GET)
    public String testConfig2() {
        return benConfigName;
    }

    /**
     *  通过改变    profiles:
     *     active: dev
     *        active: test
     *        抓取不同环境的配置信息，
     * @return
     */
    @RequestMapping(value = "/test-config3", method = RequestMethod.GET)
    public String testConfig3() {
        return env;
    }

}
