package cn.com.benjiamin.shopcommon.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@TableName("shop_order")
@Data
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "o_id", type = IdType.AUTO)
    private Integer oId;
    @TableField(value = "u_id")
    private Integer uId;
    private String username;
    @TableField(value = "p_id")
    private Integer pId;
    @TableField(value = "p_name")
    private String pName;
    @TableField(value = "p_price")
    private Double pPrice;
    private Integer stock;
    private Integer number;


}
