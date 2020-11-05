package com.ysc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @author Yangsc
 * @date 2020/11/4
 * @description：
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    /*后台监控 web.xml*/
    @Bean
    public ServletRegistrationBean  statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        /*后台需要有人登陆，账号密码配置*/
        HashMap<String, String> initParameters = new HashMap<>();

        /*登陆key是固定的*/
        initParameters.put("loginUsername", "admin");
        initParameters.put("loginPassword", "123456");

        /*允许谁可以访问*/
//        initParameters.put("allow", "localhost"); //只允许本机登录
//        initParameters.put("allow", "");  //为空或者为null的时候表示允许所有人登录
        initParameters.put("allow", "");
//        initParameters.put("ysc", "115.236.87.2"); //表示禁止此ip访问
        bean.setInitParameters(initParameters);
        return bean;
    }

    /*Filter*/
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        /*可以过滤的请求*/
        HashMap<String, String> initParameters = new HashMap<>();

        /*这些东西不进行统计*/
        initParameters.put("exclusions","*.js,*.css,/druid/*");

        bean.setInitParameters(initParameters);
        return bean;
    }
}
