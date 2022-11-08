package cn.smilex.telegram.message.bot.controller;

import cn.smilex.telegram.message.bot.entity.ReqSendMessage;
import cn.smilex.telegram.message.bot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * @author smilex
 * @date 2022/11/5/22:02
 * @since 1.0
 */
@RequestMapping("/telegram/bot/message")
@RestController
public class MessageController {

    private MessageService messageService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 发送消息
     *
     * @param message 消息
     */
    @PostMapping("/send")
    public void send(@RequestBody ReqSendMessage message) {
        messageService.sendMessage(message);
    }
}
