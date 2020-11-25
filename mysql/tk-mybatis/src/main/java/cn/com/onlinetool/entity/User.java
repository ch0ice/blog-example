package cn.com.onlinetool.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private Integer id;

    private String username;

    private String password;

}