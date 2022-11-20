package cn.smilex.telegram.message.bot.config;

import cn.smilex.telegram.message.bot.executor.VirtualThreadPerTaskExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;

/**
 * @author smilex
 * @date 2022/11/20/13:46
 * @since 1.0
 */
@Configuration
public class SpringBootConfiguration {

    private VirtualThreadPerTaskExecutor virtualThreadPerTaskExecutor;

    @Autowired
    public void setVirtualThreadPerTaskExecutor(VirtualThreadPerTaskExecutor virtualThreadPerTaskExecutor) {
        this.virtualThreadPerTaskExecutor = virtualThreadPerTaskExecutor;
    }

    @Bean(TaskExecutionAutoConfiguration.APPLICATION_TASK_EXECUTOR_BEAN_NAME)
    public AsyncTaskExecutor asyncTaskExecutor() {
        return new TaskExecutorAdapter(virtualThreadPerTaskExecutor);
    }

    @Bean
    public TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer() {
        return protocolHandler -> protocolHandler.setExecutor(virtualThreadPerTaskExecutor);
    }

}
