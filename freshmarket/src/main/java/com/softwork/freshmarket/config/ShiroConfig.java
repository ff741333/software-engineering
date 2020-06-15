package com.softwork.freshmarket.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    /**
     * 注入自定义的 Realm
     *
     * @return MyRealm
     */
    @Bean
    public MyRealm myAuthRealm() {
        MyRealm myRealm = new MyRealm();
        logger.info("====myRealm注册完成=====");
        return myRealm;
    }

    /**
     * 注入安全管理器
     *
     * @return SecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        // 将自定义 Realm 加进来
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myAuthRealm());
        logger.info("====securityManager注册完成====");
        return securityManager;
    }

    /**
     * 注入 Shiro 过滤器
     *
     * @param defaultWebSecurityManager 安全管理器
     * @return ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        // 定义 shiroFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 设置自定义的 securityManager
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        // 设置默认登录的 URL，身份认证失败会访问该 URL
        shiroFilterFactoryBean.setLoginUrl("/testLogin.html");
        // 设置成功之后要跳转的链接
        //shiroFilterFactoryBean.setSuccessUrl("/logout");
        // 设置未授权界面，权限认证失败会访问该 URL
        shiroFilterFactoryBean.setUnauthorizedUrl("/testLogin.html");

        // LinkedHashMap 是有序的，进行顺序拦截器配置
        LinkedHashMap<String, String> filterChainMap = new LinkedHashMap<>();

        // 配置 logout 过滤器与druid冲突
        filterChainMap.put("/quit", "logout");

        // 配置可以匿名访问的地址，可以根据实际情况自己添加，放行一些静态资源等，anon 表示放行
        filterChainMap.put("/css/**", "anon");
        filterChainMap.put("/images/**", "anon");
        filterChainMap.put("/js/**", "anon");
        filterChainMap.put("/layui/**", "anon");
        filterChainMap.put("/druid/**", "anon");

        filterChainMap.put("/loginServlet", "anon");
        filterChainMap.put("/regServlet", "anon");
        filterChainMap.put("/checkUser","anon");

        filterChainMap.put("/testLogin.html","anon");
        filterChainMap.put("/testRegister.html","anon");




        filterChainMap.put("/page/**", "roles[customer]");
        filterChainMap.put("/pageforsupplier/**", "roles[supplier]");


        filterChainMap.put("/**","authc");

        // 设置 shiroFilterFactoryBean 的 FilterChainDefinitionMap
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        logger.info("====shiroFilterFactoryBean注册完成====");
        return shiroFilterFactoryBean;
    }
}

