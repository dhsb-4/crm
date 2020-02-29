package com.t248.crm.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.t248.crm.entity.Right;
import com.t248.crm.service.user.RightService;
import com.t248.crm.service.user.RoleService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;


import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * shiro的配置类
 */
@Configuration
public class ShiroConfig {


    //注入redis参数
//    @Value("${spring.redis.host}")
//    private String host;
//
//    @Value("@{spring.redis.port}")
//    private int port;
//
//    @Value("{spring.redis.password}")
//    private String password;
//
//    @Value("{spring.redis.timeout}")
//    private int timeout;


    @Resource
    private RoleService roleService;

    @Resource
    private RightService rightService;

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        System.out.println("ShiroConfiguration.shiroFilter():配置权限控制规则");
        //必须设置SecurityManager
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //必须设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //如果不设置默认会自动寻找Web工程跟目录下的“login.jsp”页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //登录成功后要跳转的连接
        shiroFilterFactoryBean.setSuccessUrl("/main");
        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        //添加shiro内置过滤器，实现权限相关的url拦截
        /**
         * 常见过滤器
         * anon：无需认证（登录）访问
         * authc：必须认证才可访问
         * user：如果使用Remember Me的功能，可以直接访问
         * perms：该资源必须得到资源权限才可访问
         * role：该资源必须得到角色才可以访问
         *
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //配置不会被拦截的连接 顺序判断
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/localcss/**", "anon");
        filterChainDefinitionMap.put("/dologin", "anon");
        //配置退出 过滤器，其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");

        //配置测试权限
       /* filterChainDefinitionMap.put("/user/list","perms[用户管理]");
        filterChainDefinitionMap.put("/user/add**","perms[用户管理]");
        filterChainDefinitionMap.put("/role/list","perms[用户管理]");*/

        List<Right> rights = rightService.findAllRights();  //获取所有权限控制
        for (Right right : rights) {
            if (!right.getRightType().equals("Folder") && !right.getRightType().equals("Button")) {
                System.out.println("过滤器拦截url：" + right.getRightUrl() + ",以及对于需要访问的权限：" + right.getRightText());
                filterChainDefinitionMap.put(right.getRightUrl(), "perms[" + right.getRightText() + "]");
            }
        }

        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        //告诉realm，使用credentialsMatcher加密算法类来验证密文
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        //启动缓存及设置缓存名称、
        myShiroRealm.setCachingEnabled(true);
        myShiroRealm.setAuthorizationCachingEnabled(true);
        myShiroRealm.setAuthorizationCacheName("authorizationCache");
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置reaml
        securityManager.setRealm(myShiroRealm());
        //自定义缓存实现，使用redis
        securityManager.setCacheManager(cacheManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        return redisManager;
    }


    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        //缓存名称
        redisCacheManager.setPrincipalIdFieldName("usrName");
        //缓存时间
        redisCacheManager.setExpire(1800);
        return redisCacheManager;
    }

    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        System.out.println("hashedCredentialsMatcher........................");
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //使用md5算法进行加密
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //设置散列次数，意为加密几次
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }
}
