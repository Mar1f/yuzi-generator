package com.yupi.maker.meta;

/**
 * @description；
 * @author:mar1
 * @data:2024/05/24
 **/
public class MetaException extends RuntimeException {

    public MetaException(String message) {
        super(message);
    }

    public MetaException(String message, Throwable cause) {
        super(message, cause);
    }
}