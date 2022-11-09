package cn.smilex.telegram.message.bot.handler;

/**
 * @author smilex
 * @date 2022/11/9/21:46
 * @since 1.0
 */
public interface TelegramMessageHandler<T> {
    void handle(T message) throws Exception;
}
