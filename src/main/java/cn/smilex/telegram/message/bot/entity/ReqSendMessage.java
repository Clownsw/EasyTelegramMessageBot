package cn.smilex.telegram.message.bot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * @author smilex
 * @date 2022/11/5/22:17
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqSendMessage {
    private Long chatId;
    private String text;
    private String parseMode;

    public SendMessage intoSendMessage() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(this.chatId);
        sendMessage.setText(this.text);
        sendMessage.setParseMode(this.parseMode);
        return sendMessage;
    }
}
