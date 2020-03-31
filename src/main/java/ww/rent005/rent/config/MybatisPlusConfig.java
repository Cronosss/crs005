package ww.rent005.rent.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatisplus的配置类
 * @ClassName: MybatisPlusConfig
 * @Author: cronos
 * @Date: 2020/1/30 11:38
 * @Version: 1.0
 **/
@Configuration
@ConditionalOnClass(value= {PaginationInterceptor.class})
public class MybatisPlusConfig {
	
	@Bean
	public PaginationInterceptor  paginationInterceptor() {
		return new PaginationInterceptor();
	}

}
