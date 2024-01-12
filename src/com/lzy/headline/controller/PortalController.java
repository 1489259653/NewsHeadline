package com.lzy.headline.controller;

import com.lzy.headline.common.Result;
import com.lzy.headline.pojo.NewsType;
import com.lzy.headline.pojo.vo.HeadlineDetailVo;
import com.lzy.headline.pojo.vo.HeadlineQueryVo;
import com.lzy.headline.service.NewsHeadlineService;
import com.lzy.headline.service.NewsTypeService;
import com.lzy.headline.service.impl.NewsHeadlineServiceImpl;
import com.lzy.headline.service.impl.NewsTypeServiceImpl;
import com.lzy.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/portal/*")
public class PortalController extends BaseController{
    private NewsHeadlineService headlineService=new NewsHeadlineServiceImpl();
    private NewsTypeServiceImpl newsTypeService=new NewsTypeServiceImpl();


    protected void showHeadlineDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收 查询头条的hid
        int hid = Integer.parseInt(req.getParameter("hid"));

        //调用服务层 完成查询任务

        HeadlineDetailVo headlineDetailVo =headlineService.findHeadLineDetail(hid);

        //将查询的信息响应给客户端
        Map data = new HashMap();
        data.put("headline",headlineDetailVo);
        WebUtil.writeJson(resp,Result.ok(data));
    }

    /**
     * 分页查询接口信息的实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findNewsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //接收请求中的参数
        HeadlineQueryVo headlineQueryVo = WebUtil.readJson(req, HeadlineQueryVo.class);
        //将参数传递给服务层 ,进行分页查询
        /**
         * pageInfo:[{}],
         * pageNum:1,
         * pageSize:1,
         * totalPage:1,
         * totalSize:1,
         */
       Map pageInfo= headlineService.findPage(headlineQueryVo);
       Map data = new HashMap();
       data.put("pageInfo",pageInfo);

            //将分页查询的结果转换为json 响应给客户端
        WebUtil.writeJson(resp,Result.ok(data));
    }

    /**
     * 查询所有新闻类型
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findAllTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<NewsType> newsTypeList =newsTypeService.findAll();
        WebUtil.writeJson(resp,Result.ok(newsTypeList));
    }
}