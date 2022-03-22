package org.example.common.core.constants;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultData<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 成功
     */
    public static final int SUCCESS = Constants.SUCCESS;

    /**
     * 失败
     */
    public static final int FAIL = Constants.FAIL;

    private int code;

    private String msg;

    private T data;

    public static <T> ResultData<T> ok() {
        return restResult(null, SUCCESS, null);
    }

    public static <T> ResultData<T> ok(T data) {
        return restResult(data, SUCCESS, null);
    }

    public static <T> ResultData<T> ok(T data, String msg) {
        return restResult(data, SUCCESS, msg);
    }

    public static <T> ResultData<T> fail() {
        return restResult(null, FAIL, null);
    }

    public static <T> ResultData<T> fail(String msg) {
        return restResult(null, FAIL, msg);
    }

    public static <T> ResultData<T> fail(T data) {
        return restResult(data, FAIL, null);
    }

    public static <T> ResultData<T> fail(T data, String msg) {
        return restResult(data, FAIL, msg);
    }

    public static <T> ResultData<T> fail(int code, String msg) {
        return restResult(null, code, msg);
    }

    private static <T> ResultData<T> restResult(T data, int code, String msg) {
        ResultData<T> apiResult = new ResultData<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

}
