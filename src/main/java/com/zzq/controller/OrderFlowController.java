package com.zzq.controller;

import com.zzq.domain.Order;
import jakarta.annotation.Resource;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@RestController
@RequestMapping("/orderFlow")
public class OrderFlowController {

    private static final Logger log = LoggerFactory.getLogger(OrderFlowController.class);
    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private ProcessEngine processEngine;

    @PostMapping("/create_order")
    public ResponseEntity<String> startFlow(String customer,Integer total){
        Map<String,Object> map = new HashMap<>();
        map.put("order",new Order(customer,total));
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("flowable-sample",map);
        String processId = processInstance.getId();
        log.info("{} 流程实例ID:{} ",processInstance.getProcessDefinitionName(),processId);
        Task task = taskService.createTaskQuery().processInstanceId(processId).active().singleResult();
        taskService.complete(task.getId());
        return ResponseEntity.ok(processId);
    }


    @RequestMapping("/order_list")
    public String getOrderList(){
        List<Task> list = taskService.createTaskQuery().taskAssignee("manager").list();
        StringBuffer stringBuffer = new StringBuffer();
        list.stream().forEach(task->stringBuffer.append(task.getId()
        +" : " + runtimeService.getVariable(task.getExecutionId(),"order") +"\n"));
        return stringBuffer.toString();
    }
    @PostMapping("/confirm/{taskId}")
    public ResponseEntity<String>confirm(@PathVariable String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        HashMap<String, Object> map = new HashMap<>();
        map.put("verified", true);
        taskService.complete(taskId, map);
        return ResponseEntity.ok("success");
    }



















}
