package pers.zhenfeng.oss.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pers.zhenfeng.core.base.BaseResult;
import pers.zhenfeng.core.constant.ResultCode;
import pers.zhenfeng.core.util.BaseResultUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Grow-Worm
 * @date 2018/11/05
 */
@Controller
@RequestMapping("recycle")
public class RecycleController {

    @Resource
    private AuthenticationManager authenticationManager;

    @RequestMapping("authentication")
    @ResponseBody
    public BaseResult<String> login(String username, String password, HttpServletRequest request) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return BaseResultUtil.fail("用户名或密码不能为空");
        }

        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authenticate = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        } catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();
            return BaseResultUtil.fail(ResultCode.UNAUTH.getCode(), "认证失败");
        }

        return BaseResultUtil.success();
    }

    @RequestMapping("login")
    public ModelAndView login() {
        return new ModelAndView("/static/index.html");
    }

    @RequestMapping("loginSuccess")
    @ResponseBody
    public BaseResult<String> loginSuccess() {
        return BaseResultUtil.fail(ResultCode.LOGIN.getCode(), "登陆成功");
    }

    @RequestMapping("loginFail")
    @ResponseBody
    public BaseResult<String> loginFail() {
        return BaseResultUtil.fail(ResultCode.LOGIN.getCode(), "登陆失败");
    }

    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("/static/index.html");
    }

}
