package com.lzy.headline.service.impl;

import com.lzy.headline.dao.NewsHeadLineDao;
import com.lzy.headline.dao.impl.NewsHeadlineDaoImpl;
import com.lzy.headline.pojo.NewsHeadline;
import com.lzy.headline.pojo.vo.HeadlineDetailVo;
import com.lzy.headline.pojo.vo.HeadlinePageVo;
import com.lzy.headline.pojo.vo.HeadlineQueryVo;
import com.lzy.headline.service.NewsHeadlineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsHeadlineServiceImpl  implements NewsHeadlineService {
    private NewsHeadLineDao headLineDao = new NewsHeadlineDaoImpl();
    @Override
    public Map findPage(HeadlineQueryVo headlineQueryVo) {
        int pageNum = headlineQueryVo.getPageNum();
        int pageSize = headlineQueryVo.getPageSize();
        List<HeadlinePageVo> pageData = headLineDao.findPageList(headlineQueryVo);
        int totalSize = headLineDao.findPageCout(headlineQueryVo);
        int  totalPage = totalSize % pageSize==0?totalSize/pageSize:totalSize/pageSize+1;
        Map pageInfo = new HashMap();
        pageInfo.put("pageNum",pageNum);
        pageInfo.put("pageSize",pageSize);
        pageInfo.put("totalSize",totalSize);
        pageInfo.put("totalPage",totalPage);
        pageInfo.put("pageData",pageData);
        return pageInfo;
    }

    @Override
    public HeadlineDetailVo findHeadLineDetail(int hid) {
        //修改该头条的浏览量 +1
        headLineDao.increPageViews(hid);
        //查询头条的详情
        return headLineDao.findHeadlineDetail(hid);
    }

    @Override
    public int addNewsHeadline(NewsHeadline newsHeadline) {
        return headLineDao.addNewsHeadline(newsHeadline);
    }

    @Override
    public NewsHeadline findByHid(Integer hid) {
        return headLineDao.findByHid(hid);
    }

    @Override
    public int update(NewsHeadline newsHeadline) {
        return headLineDao.update(newsHeadline);
    }

    /**
     *
     * @param hid
     * @return
     */
    @Override
    public int removeByHid(Integer hid) {
        return headLineDao.removeByHid(hid);
    }
}
