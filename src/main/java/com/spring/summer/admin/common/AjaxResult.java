package com.spring.summer.admin.common;

import lombok.Data;

import java.util.HashMap;

/**
 * @Author CXB
 * @ClassName AjaxResult
 * @date 2022/8/13 16:55
 * @Description TODO
 */
@Data
public class AjaxResult extends HashMap<String,Object> {

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回状态
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA = "data";

    /**
     * 初始化一个新对象，是其他表示一个空消息
     */
    public AjaxResult(){

    }

    /**
     * 初始化一个新对象，AjaxResult对象
     * @param code
     * @param msg
     */
    public AjaxResult(int code,String msg){
        super.put(CODE_TAG,code);
        super.put(MSG_TAG,msg);
    }

    /**
     *
     * @param code
     * @param msg
     * @param data
     */
    public AjaxResult(int code,String msg,Object data){
        super.put(CODE_TAG,code);
        super.put(MSG_TAG,msg);
        super.put(DATA,data);


    }

    /**
     * 返回成功消息
     * @return
     */
    public static AjaxResult success(){
        return AjaxResult.success("操作成功");
    }

    /**
     * 返回成功数据
     * @param msg
     * @param data
     * @return
     */
    public static AjaxResult success(String msg,Object data){
        return new AjaxResult(200,msg,data);
    }

    /**
     * 返回成功数据
     * @param data
     * @return
     */
    public static AjaxResult success(Object data){
        return AjaxResult.success("操作成功",data);
    }

    /**
     * 返回自定义成功消息
     * @param msg
     * @return
     */
    public static AjaxResult success(String msg){
        return AjaxResult.success(msg,null);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static AjaxResult error() {
        return AjaxResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult error(String msg) {
        return AjaxResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static AjaxResult error(String msg, Object data) {
        return new AjaxResult(400, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static AjaxResult error(int code, String msg) {
        return new AjaxResult(code, msg, null);
    }
}
