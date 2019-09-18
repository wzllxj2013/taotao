package com.itszt.manger.service;

import com.itszt.taotao.easyui.bean.EasyUIPageDatasBean;
import com.itszt.taotao.pojo.TbItem;
import com.itszt.taotao.pojo.TbItemDesc;

import java.util.List;

//商品业务
public interface GoodsService {

    public List<TbItem> getAllGoods();
    public EasyUIPageDatasBean<TbItem> getPageGoods(Integer pageNow, Integer pageCapacity);

    public  boolean addGoods(TbItem item, String desc) throws Exception;

    public TbItem getGoodsDetail(Long goodsid);
    public TbItemDesc getGoodsDesc(Long goodsid);
}
