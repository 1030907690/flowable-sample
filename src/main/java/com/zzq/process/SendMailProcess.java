package com.zzq.process;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendMailProcess implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(SendMailProcess.class);

    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("发送审核邮件 {} ",delegateExecution.getVariable("order"));
    }
}
