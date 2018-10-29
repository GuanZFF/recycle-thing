package pers.zhenfeng.service.base;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BaseResultUtil;
import pers.zhenfeng.service.utils.LogUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Grow-Worm
 * @date 2018/10/12
 */
@ControllerAdvice(annotations = {Controller.class, RestController.class})
public class ExceptionAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = Throwable.class)
    public BaseResult<String> handle(Throwable throwable, HttpServletRequest request) {
        LogUtil.error("service exception", request.getRequestURL().toString(), JSON.toJSONString(throwable));
        return BaseResultUtil.fail("服务出错");
    }

}
