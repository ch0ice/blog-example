package cn.com.onlinetool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test1DTO {
    private Integer userid;

    private String username;

    private String password;

    private Date createTime;

    private Date createTime1;

    private Date createTime2;

}