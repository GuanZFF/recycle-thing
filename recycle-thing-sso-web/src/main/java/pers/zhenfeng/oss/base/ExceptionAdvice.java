package pers.zhenfeng.oss.base;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.zhenfeng.api.base.LogUtil;
import pers.zhenfeng.api.service.CommonService;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.util.BaseResultUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Grow-Worm
 * @date 2018/10/12
 */
@ControllerAdvice(annotations = {Controller.class, RestController.class})
public class ExceptionAdvice {

    @Resource
    private CommonService commonService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = Throwable.class)
    public BaseResult<String> handle(Throwable throwable, HttpServletRequest request) {
        commonService.insertLog(LogUtil.error("exception", request.getRequestURL().toString(), JSON.toJSONString(throwable)));
        return BaseResultUtil.fail("调用出现异常");
    }

}
