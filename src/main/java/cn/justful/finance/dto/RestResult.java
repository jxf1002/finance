package cn.justful.finance.dto;

public class RestResult<T> {
    private static final String DEFAULT_SUCCESS_MESSAGE = "success";
    private static final String DEFAULT_ERROR_MESSAGE = "error";
    private int code;
    private String message;
    private T data;

    private RestResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static <T> RestResult<T> success() {
        return success(DEFAULT_SUCCESS_MESSAGE, null);
    }

    public static <T> RestResult<T> success(String message) {
        return success(message, null);
    }

    public static <T> RestResult<T> success(T data) {
        return success(DEFAULT_SUCCESS_MESSAGE, data);
    }

    public static <T> RestResult<T> success(String message, T data) {
        return new RestResult<>(0, message, data);
    }

    public static <T> RestResult<T> error(int code) {
        return error(code, DEFAULT_ERROR_MESSAGE, null);
    }

    public static <T> RestResult<T> error(int code, String message) {
        return error(code, message, null);
    }

    public static <T> RestResult<T> error(int code, String message, T data) {
        return new RestResult<>(code, message, data);
    }
}
