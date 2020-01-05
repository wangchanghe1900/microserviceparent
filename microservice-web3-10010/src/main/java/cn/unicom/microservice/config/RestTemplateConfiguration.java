package cn.unicom.microservice.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootConfiguration
@ConditionalOnClass(value= RestTemplate.class)
public class RestTemplateConfiguration {
	
	@Bean
	@ConditionalOnMissingBean
/*	@LoadBalanced*/
	public RestTemplate restTemplate() {
        return new RestTemplate();
	}

}