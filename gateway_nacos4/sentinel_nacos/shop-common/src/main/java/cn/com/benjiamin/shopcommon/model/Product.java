package cn.com.benjiamin.shopcommon.model;

//import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sun.istack.internal.NotNull;
import lombok.Data;

import java.io.Serializable;

//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Entity;
//import javax.persistence.Id;

//
//@Entity(name = "shop_product")
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
@TableName("shop_product")
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("pId")
    @TableId(value = "p_id", type = IdType.AUTO)
    private Integer pId;

    @JsonProperty("pName")
    @TableField(value = "p_name")
    private String pName;
    @JsonProperty("pPrice")
    @TableField(value = "p_price")
    private Double pPrice;
    private Integer stock;
}
