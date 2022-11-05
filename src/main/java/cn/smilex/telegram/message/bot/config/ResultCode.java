package cn.smilex.telegram.message.bot.config;

import lombok.Getter;

/**
 * @author smilex
 * @date 2022/11/5/22:20
 * @since 1.0
 */
@Getter
public enum ResultCode {
    SUCCESS(200, "成功"),

    UNKNOWN_ERROR(500, "未知错误!");

    final Integer code;
    final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
