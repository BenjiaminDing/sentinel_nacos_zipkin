package cn.com.benjiamin.shopcommon.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

//@Entity(name="shop_user")
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
@TableName("shop_user")
@Data
public class User   implements Serializable {
    private static final long serialVersionUID = 1L;

@TableId(value = "u_id", type = IdType.AUTO)
    private  Integer uId;

    private String username;
    private String password;
    private String telephone;
}
