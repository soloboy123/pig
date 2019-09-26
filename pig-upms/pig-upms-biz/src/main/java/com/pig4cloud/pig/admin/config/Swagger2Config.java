package com.pig4cloud.pig.admin.config;

import com.pig4cloud.pig.common.core.config.BaseSwaggerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author dell
 */
@Configuration
@EnableSwagger2
@Profile({"dev","test"})
public class Swagger2Config extends BaseSwaggerConfig {

}
