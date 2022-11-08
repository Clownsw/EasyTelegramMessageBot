package cn.smilex.telegram.message.bot.handler;

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

    public TelegramBotChannelHandler(String botName, String botKey) {
        this.botName = botName;
        this.botKey = botKey;
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
        log.info("{}", update.getMessage().getText());
    }
}
