package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.Token;

import java.util.List;

public interface TokenService {
    List<Token> getToken(Integer id);
}
