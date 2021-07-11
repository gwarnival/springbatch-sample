package com.kenny.batch.part3;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.retry.support.RetryTemplateBuilder;

public class RetryProcessor implements ItemProcessor<String, String> {

    private final RetryTemplate retryTemplate;

    public RetryProcessor() {
        this.retryTemplate = new RetryTemplateBuilder()
                .maxAttempts(3)
                .retryOn(NotFoundNameException.class)
                .build();
    }

    @Override
    public String process(String text) throws Exception {
        return this.retryTemplate.execute(context -> {
            //Retry Callback
            if (text.equals("")) {
                return text;
            }
            throw new NotFoundNameException();
        }, context -> {
            //Recover Callback
                return null;
        });
    }
}
