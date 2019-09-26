package com.pig4cloud.pig.common.core.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author dell
 */
public abstract class BaseSwaggerConfig {

	private static final String ACCESS_TOKEN_URI ="http://localhost:9999/auth/oauth/token";
	//private static final String ACCESS_TOKEN_URI ="http://localhost:9999/auth/oauth/token";

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(apiInfo())
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.pig4cloud.pig"))
			.paths(PathSelectors.any())
			.build()
			.securityContexts(Collections.singletonList(securityContext()))
			.securitySchemes(Arrays.asList(securitySchema()));

	}

	@Bean
	SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder()
			.clientId("test")
			.clientSecret("test")
			.appName("allsale")
			.scopeSeparator(" ")
			.additionalQueryStringParams(null)
			.useBasicAuthenticationWithAccessCodeGrant(false)
			.build();
	}

	private OAuth securitySchema() {
		List<AuthorizationScope> authorizationScopeList = new ArrayList();
		authorizationScopeList.add(new AuthorizationScope("server", "accessEverything"));
		List<GrantType> grantTypes = new ArrayList();
		GrantType passwordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant(ACCESS_TOKEN_URI);
		grantTypes.add(passwordCredentialsGrant);

		return new OAuth("oauth2", authorizationScopeList, grantTypes);
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth())
			.build();
	}

	private List<SecurityReference> defaultAuth() {
		final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
		authorizationScopes[0] = new AuthorizationScope("read", "read all");
		authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
		authorizationScopes[2] = new AuthorizationScope("write", "write all");
		return Collections.singletonList(new SecurityReference("oauth2", authorizationScopes));
	}
	/**
	 * 创建该API的基本信息（这些基本信息会展现在文档页面中）
	 * @return ApiInfo
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title(" 微服务系统 API")
			.termsOfServiceUrl("")
			.description("微服务系统")
			.version("1.0")
			.build();
	}
}
