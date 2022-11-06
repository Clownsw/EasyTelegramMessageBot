package cn.smilex.telegram.message.bot.api;

import lombok.Getter;

/**
 * @author smilex
 * @date 2022/11/6/11:18
 * @since 1.0
 */
@Getter
public enum TelegramBotMessageType {
    MarkdownV2(0),
    HTML(1),
    Markdown(2);

    final int value;

    TelegramBotMessageType(int value) {
        this.value = value;
    }
}
