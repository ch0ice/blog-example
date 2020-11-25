package cn.com.onlinetool.vector;

/**
 * @author choice
 * @description: 向量操作异常类
 * @date 2018/8/2 下午8:37
 *
 */
public class VectorViolationException extends RuntimeException{
    public VectorViolationException(String err){
        super(err);
    }
}
