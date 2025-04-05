package com.zzq.config;

import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.context.annotation.Configuration;

/**
 * FlowableConfig
 *
 * @Description: 解决Diagram生成的流程图文字显示为”口口口“ 这是因为本地没有默认的字体，安装字体或者修改配置解决
 * @Author: zzq
 * @Date 2025/4/5 15:16
 * @since 1.0.0
 */
@Configuration
public class FlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration.setActivityFontName("宋体");
        springProcessEngineConfiguration.setLabelFontName("宋体");
        springProcessEngineConfiguration.setAnnotationFontName("宋体");
    }
}