package cn.com.onlinetool.common;

/**
 * @author choice
 * @description:
 * @date 2018/10/21 0:43
 */
public enum CommonMessageEnum {


    //操作成功
    SUCCESS(100200, 200,"操作成功"),
    //请求参数错误
    PARAM_ERROR(100400,  400,"请求参数错误!"),
    //服务器内部错误
    SERVER_ERROR(100500, 500,"服务器内部错误!"),
    //请求参数错误
    IMPORT_ERROR(100600,  400,"信息导入错误!"),
    ;

    /** 业务代码 */
    private int code;
    /** httpCode */
    private int httpCode;
    /** 返回消息 */
    private String message;

    CommonMessageEnum(int code, int httpCode, String message) {
        this.code = code;
        this.message = message;
        this.httpCode = httpCode;
    }

    public static String getMessage(int code) {
        for (CommonMessageEnum returnMessageEnum : values()) {
            if (returnMessageEnum.code == code) {
                return returnMessageEnum.message;
            }
        }
        throw new IllegalArgumentException("No Constants match ! code : " + code);
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

    public int httpCode() {
        return httpCode;
    }
}
