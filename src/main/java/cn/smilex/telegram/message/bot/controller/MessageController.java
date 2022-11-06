package cn.smilex.telegram.message.bot.controller;

import cn.smilex.telegram.message.bot.config.CommonBeanConfig;
import cn.smilex.telegram.message.bot.entity.ReqSendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smilex
 * @date 2022/11/5/22:02
 * @since 1.0
 */
@RequestMapping("/telegram/bot/message")
@RestController
public class MessageController {

    private CommonBeanConfig commonBeanConfig;

    @Autowired
    public void setCommonBeanConfig(CommonBeanConfig commonBeanConfig) {
        this.commonBeanConfig = commonBeanConfig;
    }

    /**
     * 发送消息
     *
     * @param message 消息
     */
    @PostMapping("/send")
    public void send(@RequestBody ReqSendMessage message) {
        Thread.ofVirtual()
                .start(() -> commonBeanConfig.telegramBot()
                        .sendMessage(message));
    }
}
