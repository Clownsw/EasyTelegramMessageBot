package cn.smilex.telegram.message.bot.config;

import cn.smilex.telegram.message.bot.handler.TelegramBotChannelHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * @author smilex
 * @date 2022/11/5/22:15
 * @since 1.0
 */
@Configuration
public class CommonBeanConfig {
    @Value("${telegram.bot.key}")
    private String name;

    @Value("${telegram.bot.key}")
    private String key;

    @Bean
    public TelegramBotChannelHandler telegramBotChannelHandler() {
        return new TelegramBotChannelHandler(name, key);
    }

    @Bean
    public TelegramBotsApi telegramBot(TelegramBotChannelHandler telegramBotChannelHandler) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(telegramBotChannelHandler);
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
