package com.itszt.manger.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itszt.taotao.easyui.bean.EasyUIPageDatasBean;
import com.itszt.taotao.easyui.bean.GoodsMsg;
import com.itszt.taotao.manager.dao.GoodsDao;
import com.itszt.taotao.manager.service.inter.GoodsService;
import com.itszt.taotao.pojo.TbItem;
import com.itszt.taotao.pojo.TbItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsDao goodsDao;

    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Override
    public List<TbItem> getAllGoods() {

        List<TbItem> tbItems = goodsDao.queryAllItems();

        return tbItems;
    }

    @Override
    public EasyUIPageDatasBean<TbItem> getPageGoods(Integer pageNow, Integer pageCapacity) {
        PageHelper.startPage(pageNow,pageCapacity );


        List<TbItem> tbItems = goodsDao.queryAllItems();

        PageInfo<TbItem> pageInfo=new PageInfo<>(tbItems);

        long total = pageInfo.getTotal();
        List<TbItem> list = pageInfo.getList();

        EasyUIPageDatasBean<TbItem> easyUIPageDatasBean=new EasyUIPageDatasBean<>();
        easyUIPageDatasBean.setRows(list);
        easyUIPageDatasBean.setTotal(total);

        return easyUIPageDatasBean;
    }

    @Override
    @Transactional
    public boolean addGoods(TbItem item, String desc) throws Exception {

        item.setStatus( 0);
        item.setUpdated(new Date());
        item.setCreated(new Date());

        goodsDao.insertTbItem(item);

        TbItemDesc tbItemDesc=new TbItemDesc();
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setUpdated(new Date());
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setItemId(item.getId());

        goodsDao.insertTbItemDesc(tbItemDesc);


        GoodsMsg goodsMsg=new GoodsMsg();
        goodsMsg.setOpt("add");
        goodsMsg.setTbItemNew(item);

        kafkaTemplate.send("TbItemTopic", JSON.toJSONString(goodsMsg));

        return true;
    }

    @Override
    public TbItem getGoodsDetail(Long goodsid) {
        return goodsDao.queryItemById(goodsid);
    }

    @Override
    public TbItemDesc getGoodsDesc(Long goodsid) {
        return goodsDao.queryItemDescById(goodsid);
    }


}
