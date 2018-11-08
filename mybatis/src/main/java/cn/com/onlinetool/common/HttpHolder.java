package cn.com.onlinetool.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author choice
 * @description:
 * @date 2018/10/21 0:50
 */
public class HttpHolder {

    private static final ThreadLocal<HttpServletResponse> responseHolder = new ThreadLocal<>();
    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();

    public static void setResponse(HttpServletResponse response){
        responseHolder.set(response);
    }

    public static HttpServletResponse getResponse(){
        return responseHolder.get();
    }

    public static void setRequest(HttpServletRequest request){
        requestHolder.set(request);
    }

    public static HttpServletRequest getRequest(){
            return requestHolder.get();
        }
}
