package ccom.itszt.common;


import com.itszt.pojo.TbItem;

import java.io.Serializable;

public class GoodsMsg implements Serializable {
    private String opt;
    private TbItem tbItemOld;
    private TbItem tbItemNew;

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public TbItem getTbItemOld() {
        return tbItemOld;
    }

    public void setTbItemOld(TbItem tbItemOld) {
        this.tbItemOld = tbItemOld;
    }

    public TbItem getTbItemNew() {
        return tbItemNew;
    }

    public void setTbItemNew(TbItem tbItemNew) {
        this.tbItemNew = tbItemNew;
    }
}
