package cn.smilex.telegram.message.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EasyTelegramMessageBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyTelegramMessageBotApplication.class, args);
    }

}
