package com.familytoto.familytotoProject.dao;

import java.lang.reflect.Member;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BoardMapper {

    @Select("SELECT * FROM BOARD WhERE board_no = #{no}")
    Member selectMember(long no);

}
