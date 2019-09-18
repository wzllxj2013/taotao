package com.itszt.manger.dao;

import com.itszt.taotao.pojo.TbContent;
import com.itszt.taotao.pojo.TbContentCategory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentDao {
    @Select("select * from tb_content_category where parent_id=0")
    public List<TbContentCategory> queryTopCats();
//    @Select("select * from " +
//            "${table} where parent_id=0")
//    public List<T> queryTopCatsTest(@Param("table")String table);

    @Select("select * from tb_content_category where parent_id=#{parentID}")
    public List<TbContentCategory> queryChildCats(String parentID);
    @Insert("insert into tb_content values(NULL,#{categoryId},#{title},#{subTitle},#{titleDesc},#{url},#{pic},#{pic2},#{content},#{created},#{updated})")
    public void insertContent(TbContent tbContent);

    @Select("select * from tb_content where category_id=#{catId}")
    public List<TbContent> queryContentByCatID(String catId);
}
