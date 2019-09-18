package com.itszt.manger.service.impl;

import com.alibaba.fastjson.JSON;
import com.itszt.taotao.easyui.bean.EasyUITreeBean;
import com.itszt.taotao.manager.dao.ContentDao;
import com.itszt.taotao.manager.service.inter.ContentService;
import com.itszt.taotao.pojo.TbContent;
import com.itszt.taotao.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ContentServiceImpl implements ContentService {
    @Resource
    private ContentDao contentDao;
    @Resource
    private RedisTemplate redisTemplate;
    @Value("${big_ad_key}")
    private String bigAdKey;
    @Value("${big_ad_cid}")
    private String bigADCid;

    @Override
    public List<EasyUITreeBean> showTopNodes() {

        List<TbContentCategory> tbItemCats = contentDao.queryTopCats();
        List<EasyUITreeBean> easyUITreeBeans = convertItemCat(tbItemCats);

        return easyUITreeBeans;
    }

    @Override
    public List<EasyUITreeBean> showLeafNodes(String parentID) {

        List<TbContentCategory> tbItemCats = contentDao.queryChildCats(parentID);

        List<EasyUITreeBean> easyUITreeBeans = convertItemCat(tbItemCats);
        return easyUITreeBeans;
    }

    @Override
    public void addContent(TbContent tbContent) {
        contentDao.insertContent(tbContent);
    }

    @Override
    public List<TbContent> showBigAD() {

        ValueOperations valueOperations = redisTemplate.opsForValue();
        //  先从缓存查
        Object o = valueOperations.get(bigAdKey);
        List<TbContent> tbContents=null;
        if (o==null) {
            //缓存没有
            tbContents= contentDao.queryContentByCatID(bigADCid);

            valueOperations.set(bigAdKey, JSON.toJSONString(tbContents));
            //设置超时时间
            redisTemplate.expire(bigAdKey, 1, TimeUnit.HOURS );
        }else {
            //缓存有

            String json=String.valueOf(o);
             tbContents = JSON.parseArray(json, TbContent.class);
            return tbContents;

        }
        return tbContents;
    }

    private List<EasyUITreeBean> convertItemCat(List<TbContentCategory> tbItemCats){
        List<EasyUITreeBean> easyUITreeBeans=new ArrayList<>();
        for (TbContentCategory tbItemCat : tbItemCats) {
            EasyUITreeBean easyUITreeBean=new EasyUITreeBean();
            easyUITreeBeans.add(easyUITreeBean);
            easyUITreeBean.setId(tbItemCat.getId());
            easyUITreeBean.setText(tbItemCat.getName());
            if (!tbItemCat.getIsParent()) {
                easyUITreeBean.setState(EasyUITreeBean.STATE_OPEN);

            }
        }

        return easyUITreeBeans;
    }
}
