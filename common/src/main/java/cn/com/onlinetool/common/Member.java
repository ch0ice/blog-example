package cn.com.onlinetool.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author choice
 * @description: 测试netty序列化
 * @date 2018-12-26 19:43
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member implements Serializable {
    private String mid ;
    private String name ;
    private Integer age ;
    private Double salary ;
}
