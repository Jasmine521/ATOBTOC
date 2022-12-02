package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.util.Date;
@Data
public class Token {
    private Integer id;

    private String openid;

    private String qcshopenid;

    private String qcshtoken;

    private String pid;

    private String name;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getQcshopenid() {
        return qcshopenid;
    }

    public void setQcshopenid(String qcshopenid) {
        this.qcshopenid = qcshopenid == null ? null : qcshopenid.trim();
    }

    public String getQcshtoken() {
        return qcshtoken;
    }

    public void setQcshtoken(String qcshtoken) {
        this.qcshtoken = qcshtoken == null ? null : qcshtoken.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}