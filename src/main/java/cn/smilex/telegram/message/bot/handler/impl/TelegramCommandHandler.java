package cn.smilex.telegram.message.bot.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author smilex
 * @date 2022/11/9/22:14
 * @since 1.0
 */
@Slf4j
@Component
public class TelegramCommandHandler extends SimpleAbstractTelegramMessageHandler<Update> {
    @Override
    public void handle(Update message) throws Exception {
        log.info("TelegramCommandHandler {}", message.getMessage().getText());
    }
}
