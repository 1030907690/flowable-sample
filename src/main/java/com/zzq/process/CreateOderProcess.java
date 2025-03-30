package com.zzq.process;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateOderProcess implements JavaDelegate {

    private static final Logger log = LoggerFactory.getLogger(CreateOderProcess.class);

    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("订单创建成功 {}",delegateExecution.getVariable("order"));
    }
}
