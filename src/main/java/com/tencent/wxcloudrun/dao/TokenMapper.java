package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.Token;
import com.tencent.wxcloudrun.entity.TokenExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TokenMapper {
    int countByExample(TokenExample example);

    int deleteByExample(TokenExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Token record);

    int insertSelective(Token record);

    List<Token> selectByExample(TokenExample example);

    Token selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Token record, @Param("example") TokenExample example);

    int updateByExample(@Param("record") Token record, @Param("example") TokenExample example);

    int updateByPrimaryKeySelective(Token record);

    int updateByPrimaryKey(Token record);
}