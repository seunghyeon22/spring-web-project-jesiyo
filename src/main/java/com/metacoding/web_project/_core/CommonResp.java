package com.metacoding.web_project._core;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CommonResp<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> CommonResp<T> success(T data) {
        return new CommonResp<>(true, "성공", data);
    }

    public static <T> CommonResp<T> fail(String message) {
        return new CommonResp<>(false, message, null);
    }
}
