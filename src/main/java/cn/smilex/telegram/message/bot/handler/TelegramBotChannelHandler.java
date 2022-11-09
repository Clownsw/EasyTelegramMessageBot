package cn.smilex.telegram.message.bot.handler;

import cn.smilex.telegram.message.bot.handler.impl.AbstractTelegramMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author smilex
 * @date 2022/11/8/21:53
 * @since 1.0
 */
@Slf4j
public class TelegramBotChannelHandler extends TelegramLongPollingBot {
    private final String botName;
    private final String botKey;
    private final AbstractTelegramMessageHandler<Update> telegramMessageHandler;

    public TelegramBotChannelHandler(String botName, String botKey, AbstractTelegramMessageHandler<Update> telegramMessageHandler) {
        this.botName = botName;
        this.botKey = botKey;
        this.telegramMessageHandler = telegramMessageHandler;
    }

    @Override
    public String getBotUsername() {
        return this.botName;
    }

    @Override
    public String getBotToken() {
        return this.botKey;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            telegramMessageHandler.handle(update);
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
