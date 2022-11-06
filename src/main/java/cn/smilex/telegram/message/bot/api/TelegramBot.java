package cn.smilex.telegram.message.bot.api;

import cn.smilex.req.HttpRequest;
import cn.smilex.req.HttpResponse;
import cn.smilex.req.Requests;
import cn.smilex.telegram.message.bot.entity.ReqSendMessage;
import cn.smilex.telegram.message.bot.util.CommonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static cn.smilex.req.Requests.requests;
import static cn.smilex.telegram.message.bot.util.CommonUtil.OBJECT_MAPPER;

/**
 * @author smilex
 * @date 2022/11/5/22:08
 * @since 1.0
 */
@Slf4j
@Getter
public class TelegramBot {

    private static final int MESSAGE_LEN = 4096;

    private final String key;
    private final String baseApiUrl;

    public TelegramBot(String key) {
        this.key = key;
        this.baseApiUrl = String.format("https://api.telegram.org/bot%s/", key);
    }

    public HttpRequest createSendMessageRequest(Integer chatId, String message) {
        return createSendMessageRequest(
                chatId,
                message,
                null
        );
    }

    private HttpRequest createSendMessageRequest(ReqSendMessage reqSendMessage) {
        return createSendMessageRequest(
                reqSendMessage.getChatId(),
                reqSendMessage.getMessage(),
                reqSendMessage.getParseMode()
        );
    }

    public HttpRequest createSendMessageRequest(Integer chatId, String message, Integer messageType) {

        HttpRequest httpRequest = HttpRequest.build()
                .setUrl(this.baseApiUrl + "sendMessage")
                .setMethod(Requests.REQUEST_METHOD.POST)
                .addParam("chat_id", String.valueOf(chatId))
                .addParam("text", message);

        String parseMode;
        if ((parseMode = CommonUtil.parseTelegramBotMessageType(messageType)) != null) {
            httpRequest.addParam("parse_mode", parseMode);
        }

        return httpRequest;
    }

    public List<Boolean> sendMessage(ReqSendMessage reqSendMessage) {
        List<Boolean> result = new ArrayList<>();

        final String message = reqSendMessage.getMessage();
        final Integer chatId = reqSendMessage.getChatId();

        if (message.length() <= MESSAGE_LEN) {
            result.add(sendMessage(createSendMessageRequest(reqSendMessage)));
        } else {
            final int len = message.length();
            final int count = len / MESSAGE_LEN;
            int n = 0;
            for (int i = 0; i < count; i++) {
                result.add(sendMessage(createSendMessageRequest(chatId, message.substring(n, (n += MESSAGE_LEN)))));
            }

            if (len % MESSAGE_LEN != 0) {
                result.add(sendMessage(createSendMessageRequest(chatId, message.substring(n))));
            }
        }

        return result;
    }

    public boolean sendMessage(HttpRequest httpRequest) {
        try {
            HttpResponse response = requests.request(httpRequest);
            JsonNode root = OBJECT_MAPPER.readTree(response.getBody());
            return root.get("ok").asBoolean();
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
    }
}
