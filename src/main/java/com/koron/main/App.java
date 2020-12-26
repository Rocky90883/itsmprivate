package com.koron.main;

import com.koron.common.PersonResolver;
import com.koron.common.SSOFilter;
import com.koron.common.authentication.AuthInterceptor;
import com.koron.common.stub.ConfigCenter;
import com.koron.common.web.KoronListener;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.MultipartConfigElement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

/**
 * 该注解指定项目为springboot，由此类当作程序入口 自动装配 web 依赖的环境
 **/
@SpringBootApplication
@EnableSwagger2
@ComponentScan(value = {"org.swan", "com.koron", "com.koron.main"})
public class App extends WebMvcConfigurationSupport {

    public final static String PREVIEW = "/files/";

    public static void main(String[] args) throws IOException {
        SpringApplication app = new SpringApplication(App.class);
        if (System.getenv("config_center_url") != null)
            app.setDefaultProperties(ConfigCenter.getProperties(System.getenv("tarant"), System.getenv("app")));
        Banner b = (environment, sourceClass, printStream) -> {
            BufferedReader reader = new BufferedReader(new InputStreamReader(App.class.getClassLoader().getResourceAsStream("logo.txt")));
            String tmp = null;
            try {
                while ((tmp = reader.readLine()) != null)
                    printStream.println(tmp);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            printStream.print("App version is:" + AnsiOutput.toString(AnsiColor.RED, environment.getProperty("app.version")) + "  ");
            String version = SpringBootVersion.getVersion();
            version = (version == null ? "" : " (v" + version + ")");
            printStream.println(AnsiOutput.toString(AnsiColor.GREEN, "Spring Boot:" + version));
        };
        app.setBanner(b);
        app.run(args);
    }


    @Bean
    public AuthInterceptor getAccessInterceptor(){
        System.out.println("注入了AccessInterceptor");
        return new AuthInterceptor();
    }

//    @SuppressWarnings({ "rawtypes", "unchecked" })
//    @Bean
//    public ServletListenerRegistrationBean listenerRegist() {
//        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
//        srb.setListener(new KoronListener());
//        System.out.println("KoronListener启动");
//        return srb;
//    }

    /**
     * 注册过滤器
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FilterRegistrationBean filterRegist() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new SSOFilter());
//        frBean.setOrder(1);//多个过滤器时指定过滤器的执行顺序
        frBean.addUrlPatterns("/cas.htm");
        System.out.println("注入了SSOFilter");
        return frBean;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截路径
        registry.addInterceptor(getAccessInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/","/error","/favicon.ico")
                .excludePathPatterns("/swagger-resources/**","/webjars/**", "/v2/**","/swagger-ui.html/**")
                .excludePathPatterns("/cas.htm")
                .excludePathPatterns("/umacas.htm")
                .excludePathPatterns("/login.htm")
//                .excludePathPatterns("/login/login.htm")
                .excludePathPatterns("/static/**","/vue/**")
                .excludePathPatterns("/fonts/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/**/*.html")
                .excludePathPatterns("/**/*.js")
                .excludePathPatterns("/**/*.css");
        super.addInterceptors(registry);
    }

    /**
     * 上传附件容量限制
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("102400KB");
        factory.setMaxRequestSize("112400KB");
        return factory.createMultipartConfig();
    }



    /*
     * 装载session
     * (non-Javadoc)
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#addArgumentResolvers(java.util.List)
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new PersonResolver());
    }


    /**
     * 解决浏览器中文乱码问题
     *
     * @return
     */
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setSuffix(".html");
        return resolver;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        // 文件访问路径
//        registry.addResourceHandler(PREVIEW + "**").addResourceLocations("file:" + ResourceComponent.fileUploadPath);
        super.addResourceHandlers(registry);
    }

}