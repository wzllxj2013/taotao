package com.itszt.manger.service;

import com.itszt.taotao.easyui.bean.EasyUITreeBean;

import java.util.List;

public interface ItemCatService {

    public List<EasyUITreeBean> showTopNodes();
    public List<EasyUITreeBean> showLeafNodes(String parentID);
}
