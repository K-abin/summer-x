package com.spring.summer.common.utils;

/**
 * @Author CXB
 * @ClassName HttpStatus
 * @date 2022/8/20 21:19
 * @Description 返回状态码
 */

public class HttpStatus {

    /**
     * 操作成功
     */
    public static final int SUCCESS = 200;

    /**
     * 参数泪飙错误(缺少，格式不匹配)
     */
    public static final int BAD_REQUEST = 400;

    /**
     * 未授权
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * 访问受限，授权过期
     */
    public static final int forbidden = 403;

    /**
     * 资源，服务未找到
     */
    public static final int NOT_FOUND = 404;

    /**
     * 系统内部错误
     */
    public static final int ERROR = 500;

    /**
     * 接口未实现
     */
    public static final int NOT_IMPLEMENTED = 501;
}
