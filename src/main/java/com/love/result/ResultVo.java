package com.love.result;

import lombok.Data;

@Data
public class ResultVo<T> {

    private String code;

    private String message;

    private T date;
}
