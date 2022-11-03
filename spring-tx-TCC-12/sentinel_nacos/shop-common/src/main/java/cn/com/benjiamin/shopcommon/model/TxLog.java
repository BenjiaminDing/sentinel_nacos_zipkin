package cn.com.benjiamin.shopcommon.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * created     by benjiamin at 2022-10-23 / 12:30 /30
 */

@TableName("shop_txlog")
@Data
public class TxLog implements Serializable {

    @TableId(value = "tx_logId", type = IdType.AUTO)
    private Integer txLogId;
    @TableField(value = "content")
    private String content;
    @TableField(value = "date")
    private Date date;
}
