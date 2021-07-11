package com.kenny.batch.part6;

import org.springframework.batch.integration.async.AsyncItemProcessor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.core.task.TaskExecutor;

public class AsyncUserConfiguration {

    private final TaskExecutor taskExecutor;

    public AsyncUserConfiguration(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }


    private AsyncItemProcessor<String, String> asyncItemProcessor() {

        ItemProcessor<String,String> itemProcessor = user -> {
            if (!user.equals("")){
                return user;
            }
            return null;
        };

        AsyncItemProcessor<String, String> asyncItemProcessor = new AsyncItemProcessor<>();
        asyncItemProcessor.setDelegate(itemProcessor);
        asyncItemProcessor.setTaskExecutor(taskExecutor);
        return null;

    }

}
