package cn.com.benjiamin.shopproduct.service.impl;

import cn.com.benjiamin.shopcommon.model.Product;
import cn.com.benjiamin.shopcommon.model.TxLog;
import cn.com.benjiamin.shopproduct.dao.ProductDao;
import cn.com.benjiamin.shopproduct.dao.TxLogDao;
import cn.com.benjiamin.shopproduct.service.ProductService;
import cn.com.benjiamin.shopproduct.service.ProductService2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ProductServiceImpl2 implements ProductService2 {


    @Resource
    private ProductDao productMapper;

    @Resource
    private TxLogDao txLogMapper;


    /**
     * 标志REQUIRES_NEW的内部事务的异常，会影响外部事务的回滚
     */

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void modifyTxLog() {
        System.out.println("开始修改第二张表格");
        // 修改 第二张表日志表格
        TxLog txLog = new TxLog();
        txLog.setTxLogId(100);
        txLog.setContent("测试本地事务");

        Date date = new Date();
        txLog.setDate(date);

        txLogMapper.insert(txLog);

    }
}
