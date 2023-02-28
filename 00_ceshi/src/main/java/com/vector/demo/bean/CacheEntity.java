package com.vector.demo.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wlz
 * @date 2023/2/28 9:43
 */
@Data
public class CacheEntity implements Serializable {
    private static final long serialVersionUID = -107853226360392750L;
    /**
     * 值
     */
    private Object value;

    /**
     * 保存的时间戳
     */
    private long gmtModify;

    /**
     * 过期时间
     */
    private int expire;

    public CacheEntity(Object value, long gmtModify, int expire) {
        super();
        this.value = value;
        this.gmtModify = gmtModify;
        this.expire = expire;
    }

}