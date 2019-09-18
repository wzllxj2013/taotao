package com.itszt.manger.dao;

import com.itszt.taotao.pojo.TbItem;
import com.itszt.taotao.pojo.TbItemDesc;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import java.util.List;

/*

 */
@Repository
public interface GoodsDao {
    @Select("select * from tb_item")
    public List<TbItem> queryAllItems();

    @Insert("insert into tb_item values(NULL,#{title},#{sellPoint},#{price},#{num},#{barcode},#{image},#{cid},#{status},#{created},#{updated})")
    @SelectKey(before = false,keyProperty = "id",keyColumn = "id",resultType = Long.class,statement = "select last_insert_id()")
    public void insertTbItem(TbItem tbItem);


    @Insert("insert into tb_item_desc values(#{itemId},#{itemDesc},#{created},#{updated})")
    public void insertTbItemDesc(TbItemDesc tbItemDesc);
    @Select("select * from tb_item where id=#{id}")
    public TbItem queryItemById(Long id);

    @Select("select * from tb_item_desc where item_id=#{id}")
    public TbItemDesc queryItemDescById(Long id);
}
