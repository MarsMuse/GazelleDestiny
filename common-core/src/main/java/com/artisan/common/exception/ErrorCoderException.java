package com.artisan.common.exception;

import com.artisan.common.enums.ErrorCode;
import lombok.Data;

/**
 * 错误异常信息
 *
 * @author xz man
 * @since 1.8
 */
@Data
public class ErrorCoderException extends RuntimeException {
    /**
     * 错误编码
     */
    private final Integer errorCode;
    /**
     * 错误信息
     */
    private final String errorMessage;

    public ErrorCoderException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ErrorCoderException(ErrorCode errorCode) {
        super(errorCode.getDesc());
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getDesc();
    }
}
