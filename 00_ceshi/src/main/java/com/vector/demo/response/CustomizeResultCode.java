package com.vector.demo.response;

public interface CustomizeResultCode {
    /**
     * 获取错误状态码
     * @return
     */
    Integer getCode();

    /**
     * 获取错误信息
     * @return
     */
    String getMessage();
}
