package com.student.config;

import com.student.gzip.GzipResponseFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<GzipResponseFilter> gzipFilter() {
        FilterRegistrationBean<GzipResponseFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new GzipResponseFilter());
        registrationBean.addUrlPatterns("/api/compress/*");
        return registrationBean;
    }
}
