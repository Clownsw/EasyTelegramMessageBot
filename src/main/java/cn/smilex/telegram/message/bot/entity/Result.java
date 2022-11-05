package cn.smilex.telegram.message.bot.entity;

import cn.smilex.telegram.message.bot.config.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author smilex
 * @date 2022/11/5/22:19
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static Result<?> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return fromResultCode(ResultCode.SUCCESS, data);
    }

    public static Result<?> error() {
        return error(null);
    }

    public static <T> Result<T> error(T data) {
        return fromResultCode(ResultCode.UNKNOWN_ERROR, data);
    }

    public static <T> Result<T> fromResultCode(ResultCode resultCode) {
        return fromResultCode(resultCode, null);
    }

    public static <T> Result<T> fromResultCode(ResultCode resultCode, T data) {
        return new Result<>(resultCode.getCode(), resultCode.getMessage(), data);
    }
}
