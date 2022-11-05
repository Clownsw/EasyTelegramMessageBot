package cn.smilex.telegram.message.bot.config;

import cn.smilex.telegram.message.bot.api.TelegramBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author smilex
 * @date 2022/11/5/22:15
 * @since 1.0
 */
@Configuration
public class CommonBeanConfig {
    @Value("${telegram.bot.key}")
    private String key;

    @Bean
    public TelegramBot telegramBot() {
        return new TelegramBot(this.key);
    }
}
