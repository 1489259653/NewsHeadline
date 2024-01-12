package com.lzy.headline.controller;

import com.lzy.headline.common.Result;
import com.lzy.headline.common.ResultCodeEnum;
import com.lzy.headline.pojo.NewsUser;
import com.lzy.headline.service.NewsUserService;
import com.lzy.headline.service.impl.NewsUserServiceImpl;
import com.lzy.headline.util.JwtUtil;
import com.lzy.headline.util.MD5Util;
import com.lzy.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class NewsUserController extends BaseController{
    private NewsUserService userService = new NewsUserServiceImpl();


    protected void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");
        Result result = Result.build(null,ResultCodeEnum.NOTLOGIN);
        if(token !=null){
            if(!JwtUtil.isExpiration(token)){
                result =Result.ok(null);
            }
        }
        WebUtil.writeJson(resp,result);
    }


    /**
     * 校验用户名是否被占用的接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户名
        String username = req.getParameter("username");
        //根据用户名查找用户信息 找到了 返回505，找不到返回200
        NewsUser newsUser = userService.findByUsername(username);
        Result result = Result.ok(null);
        if(newsUser!=null){
            result =Result.build(null,ResultCodeEnum.USERNAME_ERROR);
        }
        WebUtil.writeJson(resp,result);
    }

    /**
     *  注册用户接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //接收JSON 信息
        NewsUser registUser = WebUtil.readJson(req, NewsUser.class);
        //调用服务层将用户存入数据
       Integer rows= userService.registUser(registUser);
            //根据存入是否成功处理响应值
        Result result = Result.ok(null);
        if(rows ==0)
        {
            result=Result.build(null,ResultCodeEnum.USERNAME_USED);

        }
        WebUtil.writeJson(resp,result);
    }

    /**
     * 处理登录表单提交的业务接口的实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收用户名密码
        NewsUser paramUser = WebUtil.readJson(req, NewsUser.class);

        //调用服务层方法，实现登录
        NewsUser loginUser = userService.findByUsername(paramUser.getUsername());
        Result result =null;
        if(null!=loginUser)
            if (MD5Util.encrypt(paramUser.getUserPwd()).equalsIgnoreCase(loginUser.getUserPwd())) {
                Map data = new HashMap();
                data.put("token",JwtUtil.createToken(loginUser.getUid().longValue()));
                result = Result.ok(data);
            }else
                result=Result.build(null,ResultCodeEnum.PASSWORD_ERROR);


        else
            result = Result.build(null, ResultCodeEnum.USERNAME_ERROR);

        // 向客户端响应数据 验证登录信息
        WebUtil.writeJson(resp,result);
    }

    /**
     * 根据token 口令 获得用户信息的接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求中的token
        String token = req.getHeader("token");
        //校验token
        Result result = Result.build(null,ResultCodeEnum.NOTLOGIN);
        if(null!=token &&(!"".equals(token))){
            if(!JwtUtil.isExpiration(token)){
                Integer userId= JwtUtil.getUserId(token).intValue();
                NewsUser newsUser=userService.findByUid(userId);
                if(null!=newsUser){
                    Map data = new HashMap();
                    newsUser.setUserPwd("");
                    data.put("loginUser",newsUser);
                    result = Result.ok(data);
                }

            }
        }
        WebUtil.writeJson(resp,result);

    }
}