package com.chinameyer.questionnaire.bean;

import lombok.Data;

@Data
public class ApiResult {
    /**
     * 如果是成功，则code为200
     */
    private int code;
    /**
     * 对错误的具体解释
     */
    private String msg;
    /**
     * 返回的结果包装在value中，value可以是单个对象
     */
    private Object data;


    public ApiResult(int code, String msg, Object data1 ){
        this.code = code;
        this.msg = msg;
        this.data = data1;
    }

    public ApiResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
