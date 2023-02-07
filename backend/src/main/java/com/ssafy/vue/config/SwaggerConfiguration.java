package com.ssafy.vue.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration // 스프링 실행시 설정파일
@EnableSwagger2 // Swagger2를 사용
@SuppressWarnings("unchecked") // warning 제거
public class SwaggerConfiguration {

//	Swagger-UI 2.x 확인
//	http://localhost[:8080]/{your-app-root}/swagger-ui.html
//	Swagger-UI 3.x 확인
//	http://localhost[:8080]/{your-app-root}/swagger-ui/index.html
//	http://localhost:9999/vue/swagger-ui.html
	
	/**
	 * swagger 2.9.2 버전으로 올리고 jwt를 자동으로 넣을 수 있는 기능을 넣으면서
	 * swagger NumberFormatException이 엄청나게 뜰텐데
	 * 무시해도 되는 exception이라고 하니 그냥 써도 됩니다.
	 * 
	 * 출처 : https://www.dariawan.com/tutorials/rest/numberformatexception-for-input-string-in-swagger/
	 */

	private String version = "V1";
	private String title = "강승권, 강원경, 서철원, 임민수 공통 작업 API " + version;

	private ApiInfo apiInfo() {
		String descript = "SSAFY Vuejs API Reference for Developers<br>";
		descript += "<img src=\"http://localhost:9999/vue/static/assets/img/ssafy_logo.png\">";
		return new ApiInfoBuilder().title(title).description(descript)
//				.termsOfServiceUrl("https://edu.ssafy.com")
				.contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@ssafy.com")).license("SSAFY License")
				.licenseUrl("ssafy@ssafy.com").version("1.0").build();
	}

	@Bean
	public Docket allApi() {
		return getDocket("전체", Predicates.or(PathSelectors.regex("/*.*")));
	}
	
	// API마다 구분짓기 위한 설정.
	@Bean
	public Docket userApi() {
		return getDocket("회원", Predicates.or(PathSelectors.regex("/user.*")));
	}

	@Bean
	public Docket boardApi() {
		return getDocket("게시판", Predicates.or(PathSelectors.regex("/board.*")));
	}

	@Bean
	public Docket aptApi() {
		return getDocket("아파트", Predicates.or(PathSelectors.regex("/map.*")));
	}
	
	@Bean
	public Docket favoritesApi() {
		return getDocket("즐겨찾기", Predicates.or(PathSelectors.regex("/favorites.*")));
	}
	
	@Bean
	public Docket covidHospitalApi() {
		return getDocket("코로나 선별 진료소", Predicates.or(PathSelectors.regex("/hospital.*")));
	}
	
	@Bean
	public Docket houseDealApi() {
		return getDocket("아파트 거래내역", Predicates.or(PathSelectors.regex("/deal.*")));
	}
	
	@Bean
	public Docket coodeApi() {
		return getDocket("지역코드", Predicates.or(PathSelectors.regex("/code.*")));
	}

	public Docket getDocket(String groupName, Predicate<String> predicate) {
//		List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
//		responseMessages.add(new ResponseMessageBuilder().code(200).message("OK !!!").build());
//		responseMessages.add(new ResponseMessageBuilder().code(500).message("서버 문제 발생 !!!").responseModel(new ModelRef("Error")).build());
//		responseMessages.add(new ResponseMessageBuilder().code(404).message("페이지를 찾을 수 없습니다 !!!").build());
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName(groupName)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.vue.controller"))
				.paths(predicate)
				.apis(RequestHandlerSelectors.any())
				.build()
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()));
//				.useDefaultResponseMessages(false)
//				.globalResponseMessage(RequestMethod.GET,responseMessages);
	}

	// swagger ui 설정.
	@Bean
	public UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().displayRequestDuration(true).validatorUrl("").build();
	}
	
	private ApiKey apiKey() {
		return new ApiKey("JWT","access-token","header");
	}
	
	private SecurityContext securityContext() {
        return springfox
                .documentation
                .spi.service
                .contexts
                .SecurityContext
                .builder()
                .securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
    }
	
	List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }
}
