package cn.com.onlinetool.dto;

import java.util.Date;

public class Test1DTO {
    private Integer userid;

    private String username;

    private String password;

    private Date createTime;

    private Date createTime1;

    private Date createTime2;

    public Test1DTO(Integer userid, String username, String password, Date createTime, Date createTime1, Date createTime2) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.createTime = createTime;
        this.createTime1 = createTime1;
        this.createTime2 = createTime2;
    }

    public Integer getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getCreateTime1() {
        return createTime1;
    }

    public Date getCreateTime2() {
        return createTime2;
    }
}