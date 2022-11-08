package cn.smilex.telegram.message.bot.service;

import cn.smilex.telegram.message.bot.entity.ReqSendMessage;

/**
 * @author smilex
 * @date 2022/11/8/22:01
 * @since 1.0
 */
public interface MessageService {
    void sendMessage(ReqSendMessage message);
}
