package com.zzq.controller;

import com.zzq.domain.Order;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricActivityInstanceQuery;
import org.flowable.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

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
    public ResponseEntity<String> startFlow(String customer, Integer total) {
        Map<String, Object> map = new HashMap<>();
        map.put("order", new Order(customer, total));
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("flowable-sample", map);
        String processId = processInstance.getId();
        log.info("{} 流程实例ID:{} ", processInstance.getProcessDefinitionName(), processId);
        Task task = taskService.createTaskQuery().processInstanceId(processId).active().singleResult();
        taskService.complete(task.getId());
        return ResponseEntity.ok(processId);
    }


    @RequestMapping("/order_list")
    public String getOrderList() {
        List<Task> list = taskService.createTaskQuery().taskAssignee("manager").list();
        StringBuffer stringBuffer = new StringBuffer();
        list.stream().forEach(task -> stringBuffer.append(task.getId()
                + " : " + runtimeService.getVariable(task.getExecutionId(), "order") + "\n"));
        return stringBuffer.toString();
    }

    @PostMapping("/confirm/{taskId}")
    public ResponseEntity<String> confirm(@PathVariable String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        HashMap<String, Object> map = new HashMap<>();
        map.put("verified", true);
        taskService.complete(taskId, map);
        return ResponseEntity.ok("success");
    }

    @Resource
    private HistoryService historyService;


    @GetMapping(value = "/processDiagram/{processId}")
    public void genProcessDiagram(HttpServletResponse response, @PathVariable("processId") String processId) throws Exception{
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();

        if (null == pi) {
            return;
        }
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
        String instanceId = task.getProcessInstanceId();
        List<Execution> executions = runtimeService.createExecutionQuery().processInstanceId(instanceId).list();
        //得到正在执行的Activity的Id
        List<String> activityIds = new ArrayList<>();
        List<String> flows = new ArrayList<>();
        List<HistoricActivityInstance> historyList = historyService.createHistoricActivityInstanceQuery().processInstanceId(processId).orderByHistoricActivityInstanceStartTime().asc().list();
        for (HistoricActivityInstance historicActivityInstance : historyList) {
            String activityId = historicActivityInstance.getActivityId();
            if("sequenceFlow".equals(historicActivityInstance.getActivityType())){
                flows.add(activityId);
            }
        }

        for (Execution exe : executions) {
            List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
            activityIds.addAll(ids);
        }
        // 获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
        ProcessEngineConfiguration engConf = processEngine.getProcessEngineConfiguration();
        ProcessDiagramGenerator diagramGenerator = engConf.getProcessDiagramGenerator();
        String format = "png";

        InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows, engConf.getActivityFontName(), engConf.getLabelFontName(), engConf.getAnnotationFontName(), engConf.getClassLoader(), 1.0, false);
//        OutputStream out = null;
//        byte[] buf = new byte[1024];
//        int legth = 0;
//        try {
//            out = response.getOutputStream();
//            while ((legth = in.read(buf)) != -1) {
//                out.write(buf, 0, legth);
//            }
//        } finally {
//            if (in != null) {
//                in.close();
//            }
//            if (out != null) {
//                out.close();
//            }
//        }



            IOUtils.copy(in, response.getOutputStream());
    }



}
