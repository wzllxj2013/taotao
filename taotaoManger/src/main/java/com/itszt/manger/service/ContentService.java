package com.itszt.manger.service;

import com.itszt.taotao.easyui.bean.EasyUITreeBean;
import com.itszt.taotao.pojo.TbContent;

import java.util.List;

public interface ContentService {

    public List<EasyUITreeBean> showTopNodes();
    public List<EasyUITreeBean> showLeafNodes(String parentID);

    public void addContent(TbContent tbContent);

    public List<TbContent> showBigAD();
}
