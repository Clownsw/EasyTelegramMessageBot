package cn.smilex.telegram.message.bot.util;

import cn.smilex.telegram.message.bot.api.TelegramBotMessageType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

/**
 * @author smilex
 * @date 2022/11/5/22:13
 * @since 1.0
 */
public final class CommonUtil {
    public static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
    }

    @SneakyThrows
    public static <T> String toJsonStr(T t) {
        return OBJECT_MAPPER.writeValueAsString(t);
    }

    public static String parseTelegramBotMessageType(Integer value) {
        if (value == null) {
            return null;
        }

        TelegramBotMessageType[] values = TelegramBotMessageType.values();
        for (TelegramBotMessageType telegramBotMessageType : values) {
            if (value.equals(telegramBotMessageType.getValue())) {
                return telegramBotMessageType.name();
            }
        }

        return null;
    }
}
