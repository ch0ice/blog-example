package cn.com.onlinetool.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author choice
 * @description: 公共返回对象
 * @date 2018/10/21 0:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {

    @JsonIgnore
    private int httpCode;
    private int code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public CommonResult<T> setResult(CommonMessageEnum messageEnum) {
        this.code = messageEnum.code();
        this.message = messageEnum.message();
        this.httpCode = messageEnum.httpCode();
        if(HttpHolder.getResponse() != null){
            HttpHolder.getResponse().setStatus(httpCode);
        }
        return this;
    }


}