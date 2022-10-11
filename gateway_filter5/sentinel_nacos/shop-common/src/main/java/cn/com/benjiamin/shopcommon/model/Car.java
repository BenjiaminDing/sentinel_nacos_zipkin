package cn.com.benjiamin.shopcommon.model;

import lombok.Data;

/**
 * created at 2021/9/15 16:37  sentinel_nacos
 * 该类用于测试postMan请求实体类入参请求，不参数项目逻辑
 */
@Data
public class Car {

    private String  carNo;
    private String  address;
    private String  company;
    private String  eEngineNo;
    private Integer  weight;
}
