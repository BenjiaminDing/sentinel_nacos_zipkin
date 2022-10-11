package cn.com.benjiamin.shoporder.config;


import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * created at 2021/9/15 16:37  sentinel_nacos
 */
//异常处理页面
@Component
public class ExceptionHandlerPage implements UrlBlockHandler {
//BlockException 异常接口,包含Sentinel的五个异常

    // FlowException 限流异常
// DegradeException 降级异常
// ParamFlowException 参数限流异常
// AuthorityException 授权异常
// SystemBlockException 系统负载异
    @Override
    public void blocked(HttpServletRequest request, HttpServletResponse
            response, BlockException e) throws IOException {


        response.setContentType("application/json;charset=utf-8");

        Result data = null;

        if (e instanceof FlowException) {
            data = new Result(-1, "接口被限流了...");
        } else if (e instanceof DegradeException) {
            data = new Result(-2, "接口被降级了...");
        } else if (e instanceof ParamFlowException) {
            data = new Result(410, "参数限流异常...");
        }
        response.getWriter().write(JSON.toJSONString(data));


    }
}


////内部类
@Data
@AllArgsConstructor
@NoArgsConstructor
class Result {


    private int code;
    private String message;


}



