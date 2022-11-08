package cn.smilex.telegram.message.bot.service.impl;

import cn.smilex.telegram.message.bot.entity.ReqSendMessage;
import cn.smilex.telegram.message.bot.handler.TelegramBotChannelHandler;
import cn.smilex.telegram.message.bot.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author smilex
 * @date 2022/11/8/22:01
 * @since 1.0
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {
    private TelegramBotChannelHandler telegramBotChannelHandler;

    @Autowired
    public void setChannelHandler(TelegramBotChannelHandler telegramBotChannelHandler) {
        this.telegramBotChannelHandler = telegramBotChannelHandler;
    }

    @Override
    public void sendMessage(ReqSendMessage message) {
        try {
            telegramBotChannelHandler.execute(message.intoSendMessage());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
