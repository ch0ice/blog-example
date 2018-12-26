package cn.com.onlinetool.serialization.marshalling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author choice
 * @description:
 * @date 2018-12-26 21:36
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarshallingMember implements Serializable {
    private String mid ;
    private String name ;
    private Integer age ;
    private Double salary ;
}
