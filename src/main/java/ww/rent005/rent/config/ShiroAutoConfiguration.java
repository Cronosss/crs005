package ww.rent005.rent.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import lombok.Data;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import ww.rent005.rent.entity.DatabaseRealm;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ShiroAutoConfiguration
 * @Author: cronos
 * @Date: 2020/1/30 11:40
 * @Version: 1.0
 **/
@Configuration
@ConditionalOnWebApplication(type = Type.SERVLET)
@ConditionalOnClass(value = { SecurityManager.class })
@ConfigurationProperties(prefix = "shiro")
@Data
public class ShiroAutoConfiguration {

	private static final String SHIRO_DIALECT = "shiroDialect";
	private static final String SHIRO_FILTER = "shiroFilter";
	//采用MD5加密方式
	private String hashAlgorithmName = "md5";
	//散列次数为3
	private int hashIterations = 3;
	//登录地址
	private String loginUrl = "/index.html";
	//anon表示此地址不需要任何权限即可访问 anon可以被匿名访问
	private String[] anonUrls;
	//退出
	private String logOutUrl;
	//所有的请求(除去配置的静态资源请求或请求地址为anon的请求)都要通过登录验证,如果未登录则跳到/login
	private String[] authcUlrs;

	/**
	 * 声明凭证匹配器
	 */
	@Bean("credentialsMatcher")
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName(hashAlgorithmName);
		credentialsMatcher.setHashIterations(hashIterations);
		return credentialsMatcher;
	}

	/**
	 * 声明databaseRealm
	 */
	@Bean("databaseRealm")
	public DatabaseRealm databaseRealm(CredentialsMatcher credentialsMatcher) {
		DatabaseRealm databaseRealm = new DatabaseRealm();
		// 注入凭证匹配器
		databaseRealm.setCredentialsMatcher(credentialsMatcher);
		return databaseRealm;
	}

	/**
	 * 配置SecurityManager
	 */
	@Bean("securityManager")
	public SecurityManager securityManager(DatabaseRealm databaseRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 注入databaseRealm
		securityManager.setRealm(databaseRealm);
		return securityManager;
	}

	/**
	 * 配置shiro的过滤器工厂类
	 */
	@Bean(SHIRO_FILTER)
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
		//调用权限管理器
		factoryBean.setSecurityManager(securityManager);
		//配置登录请求地址
		factoryBean.setLoginUrl(loginUrl);
		Map<String, String> filterChainDefinitionMap = new HashMap<>();
		//设置放行的路径
		if (anonUrls != null && anonUrls.length > 0) {
			for (String anon : anonUrls) {
				filterChainDefinitionMap.put(anon, "anon");
			}
		}
		//设置退出的路径
		if (null != logOutUrl) {
			filterChainDefinitionMap.put(logOutUrl, "logout");
		}
		//设置拦截的路径
		if (authcUlrs != null && authcUlrs.length > 0) {
			for (String authc : authcUlrs) {
				filterChainDefinitionMap.put(authc, "authc");
			}
		}
		Map<String, Filter> filters=new HashMap<>();
//		filters.put("authc", new ShiroLoginFilter());
		//配置过滤器
		factoryBean.setFilters(filters);
		factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return factoryBean;
	}

	/**
	 * 注册shiro的委托过滤器，相当于之前在web.xml里面配置的
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<DelegatingFilterProxy> delegatingFilterProxy() {
		FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBean = new FilterRegistrationBean<DelegatingFilterProxy>();
		DelegatingFilterProxy proxy = new DelegatingFilterProxy();
		proxy.setTargetFilterLifecycle(true);
		proxy.setTargetBeanName(SHIRO_FILTER);
		filterRegistrationBean.setFilter(proxy);
		return filterRegistrationBean;
	}

	/**
	 * 启用shiro注解
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * 启用shiro注解
	 * @return
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}

	/**
	 * 这里是为了能在html页面引用shiro标签，上面两个函数必须添加，不然会报错
	 * 
	 * @return
	 */
	@Bean(name = SHIRO_DIALECT)
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}
}
