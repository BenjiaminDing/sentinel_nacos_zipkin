package cn.com.benjiamin.shopproduct.service.impl;

import cn.com.benjiamin.shopcommon.model.Product;
import cn.com.benjiamin.shopcommon.model.TxLog;
import cn.com.benjiamin.shopproduct.dao.ProductDao;
import cn.com.benjiamin.shopproduct.dao.TxLogDao;
import cn.com.benjiamin.shopproduct.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ProductServiceImpl implements ProductService {


    @Resource
    private ProductDao productMapper;

    @Resource
    private TxLogDao txLogMapper;
    @Resource
    private ProductServiceImpl2 productServiceImpl2;


    @Override
    public Product findProductById(Integer pId) {

        Product product = productMapper.selectById(pId);

        return product;
    }


    //todey is 2022-10-24/15:33

    /**
     * 测试本地事务的使用@Transactional
     * transactionManager--如果有多个事务管理器需要指定事务管理器，不然
     * transactionManager = "propagation",propagation = Propagation.MANDATORY,rollbackFor ={}
     */

    @Transactional( )
    @Override
    public void modifyTwoFormDB() {
        // 准备修改一次 shop-product，在修改一次 shop-txlog
        Product product = new Product();

        product.setPName("华为鸿蒙");
        product.setPPrice(89.0);
        product.setStock(5);
        int s1 = productMapper.insert(product);
        System.out.println("插入数据结果：：" + s1);
        // 修改 第二张表日志表格
        TxLog txLog = new TxLog();
        txLog.setTxLogId(1);
        txLog.setContent("测试本地事务");

        Date date = new Date();
        txLog.setDate(date);

        txLogMapper.insert(txLog);

    }

    /**
     * propagation = Propagation.REQUIRED默认值
     *
     *
     */

    @Transactional(propagation = Propagation.REQUIRED)
    public void transModifyProduct1() {
        Product product = new Product();

        product.setPName("华为鸿蒙");
        product.setPPrice(89.0);
        product.setStock(5);
        int s1 = productMapper.insert(product);
        System.out.println("插入数据结果：：" + s1);

        this.transModifyTxLog1();
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void transModifyTxLog1() {
        System.out.println("开始修改第二张表格");
        // 修改 第二张表日志表格
        TxLog txLog = new TxLog();
        txLog.setTxLogId(100);
        txLog.setContent("测试本地事务");

        Date date = new Date();
        txLog.setDate(date);

        txLogMapper.insert(null);
    }

//02

    /**
     * propagation = Propagation.REQUIRES_NEW 会创建一个新的事务，
     * product2和ModifyTxLog2互不影响
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transModifyProduct2() {
        Product product = new Product();

        product.setPName("华为鸿蒙");
        product.setPPrice(89.0);
        product.setStock(5);
        int s1 = productMapper.insert(null);
        System.out.println("插入数据结果：：" + s1);

//        this.transModifyTxLog2();

        productServiceImpl2.modifyTxLog();

    }
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public void transModifyTxLog2() {
//        System.out.println("开始修改第二张表格");
//        // 修改 第二张表日志表格
//        TxLog txLog = new TxLog();
//        txLog.setTxLogId(100);
//        txLog.setContent("测试本地事务");
//
//        Date date = new Date();
//        txLog.setDate(date);
//
//        txLogMapper.insert(txLog);
//        throw  new RuntimeException("error");
//    }
}
