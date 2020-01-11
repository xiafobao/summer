package com.xia.demo.dao;

import com.xia.demo.entity.Blog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author fuli
 */
public interface BlogMapper {

  /**
   * 根据id查询数据
   * @param id 主键
   * @return
   */
  @Select("SELECT * FROM blog WHERE id = #{id}")
  Blog selectById(String id);

  /**
   * 根据title查询数据
   * @param title 标题
   * @return
   */
  Blog selectByTitle(@Param("title") String title);

}