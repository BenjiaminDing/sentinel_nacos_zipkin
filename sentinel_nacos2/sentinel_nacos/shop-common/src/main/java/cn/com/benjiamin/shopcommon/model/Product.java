package cn.com.benjiamin.shopcommon.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    @TableId(value = "p_id", type = IdType.AUTO)
    private Integer pId;
    @TableField(value = "p_name")
    private String pName;
    @TableField(value = "p_price")
    private Double pPrice;
    private Integer stock;
}
