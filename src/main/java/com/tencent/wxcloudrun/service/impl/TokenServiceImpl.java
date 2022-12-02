package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.TokenMapper;
import com.tencent.wxcloudrun.model.Token;
import com.tencent.wxcloudrun.entity.TokenExample;
import com.tencent.wxcloudrun.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenServiceImpl implements TokenService {
    final TokenMapper tokenMapper;

    public TokenServiceImpl(@Autowired TokenMapper tokenMapper) {
        this.tokenMapper = tokenMapper;
    }

    @Override
    public List<Token> getToken(Integer id) {
        TokenExample tokenExample= new TokenExample();
        List<Token> tokens = tokenMapper.selectByExample(tokenExample);
        return tokens;
    }
}
