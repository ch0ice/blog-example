package cn.com.onlinetool.codec.serializable.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 测试用对象
 *
 * @author choice
 * @date create in 2020/4/4 00:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerializableTestPojo implements Serializable {

    private static final long serialVersionUID = -8453430256964863716L;

    private String name;
    private int age;

}
