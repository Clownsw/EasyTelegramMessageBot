package cn.smilex.telegram.message.bot.config;

import cn.smilex.telegram.message.bot.handler.TelegramBotChannelHandler;
import cn.smilex.telegram.message.bot.handler.impl.AbstractTelegramMessageHandler;
import cn.smilex.telegram.message.bot.handler.impl.TelegramChannelHandler;
import cn.smilex.telegram.message.bot.handler.impl.TelegramCommandHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * @author smilex
 * @date 2022/11/5/22:15
 * @since 1.0
 */
@Slf4j
@Configuration
public class CommonBeanConfig {
    @Value("${telegram.bot.name}")
    private String name;

    @Value("${telegram.bot.key}")
    private String key;

    @Bean
    public TelegramBotChannelHandler telegramBotChannelHandler(
            TelegramCommandHandler telegramCommandHandler,
            TelegramChannelHandler telegramChannelHandler
    ) {
        return new TelegramBotChannelHandler(name, key, new AbstractTelegramMessageHandler<>() {
            @Override
            public void init() {
                addToLast(telegramCommandHandler);
                addToLast(telegramChannelHandler);
            }

            @Override
            public void handle(Update message) {
                Node<Update> next = this.getFirst().getNext();

                while (true) {
                    if (next != null) {
                        try {
                            next.getHandler()
                                    .handle(message);
                        } catch (Exception e) {
                            log.error("", e);
                        }

                        if (next.getNext() != null) {
                            next = next.getNext();
                            continue;
                        }
                    }

                    break;
                }
            }
        });
    }

    @Bean
    public TelegramBotsApi telegramBot(TelegramBotChannelHandler telegramBotChannelHandler) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

        try {
            telegramBotsApi.registerBot(telegramBotChannelHandler);
        } catch (TelegramApiException e) {
            log.error("", e);
        }

        return telegramBotsApi;
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(6);
        threadPoolTaskExecutor.setMaxPoolSize(6);
        return threadPoolTaskExecutor;
    }
}
