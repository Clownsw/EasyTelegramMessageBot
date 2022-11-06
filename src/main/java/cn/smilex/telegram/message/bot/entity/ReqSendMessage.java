package cn.smilex.telegram.message.bot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author smilex
 * @date 2022/11/5/22:17
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqSendMessage {
    private Integer chatId;
    private String message;
    private Integer parseMode;
}
