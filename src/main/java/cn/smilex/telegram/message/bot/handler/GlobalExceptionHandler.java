package cn.smilex.telegram.message.bot.handler;

import cn.smilex.telegram.message.bot.config.ResultCode;
import cn.smilex.telegram.message.bot.entity.Result;
import cn.smilex.telegram.message.bot.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author smilex
 * @date 2022/11/5/22:19
 * @since 1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String error(Exception e) {
        log.error("", e);
        return CommonUtil.toJsonStr(Result.fromResultCode(ResultCode.UNKNOWN_ERROR));
    }
}
