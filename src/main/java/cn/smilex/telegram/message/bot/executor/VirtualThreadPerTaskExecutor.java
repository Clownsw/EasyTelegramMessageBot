package cn.smilex.telegram.message.bot.executor;

import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * @author smilex
 * @date 2022/11/20/13:47
 * @since 1.0
 */
@SuppressWarnings("all")
@Component
public class VirtualThreadPerTaskExecutor implements Executor {
    @Override
    public void execute(Runnable task) {
        Thread.startVirtualThread(task);
    }
}
