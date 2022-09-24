package br.com.llduran.restwithspringbootudemy.config;

import br.com.llduran.restwithspringbootudemy.serialization.converter.YamlJackson2HttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer
{
	private static final MediaType MEDIA_TYPE_YML = MediaType.valueOf("application/x-yaml");

	public void extendMessageConverters(List<HttpMessageConverter<?>> converters)
	{
		converters.add(new YamlJackson2HttpMessageConverter());
	}

	public void addCorsMapping(CorsRegistry registry)
	{
		registry.addMapping("/**")
				.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer )
	{
		/*// Via QUERY PARM. http://localhost:8080/api/person/v1?mediaType=xml
		configurer.favorParameter(true)
				.parameterName("mediaType")
				.ignoreAcceptHeader(true)
				.defaultContentType(MediaType.APPLICATION_JSON)
				.mediaType("json", MediaType.APPLICATION_JSON)
				.mediaType("xml", MediaType.APPLICATION_XML)
				.mediaType("x-yaml", MEDIA_TYPE_YML);*/

		// Via HEADER PARM.
		configurer.favorParameter(false)
				.ignoreAcceptHeader(false)
				.defaultContentType(MediaType.APPLICATION_JSON)
				.mediaType("json", MediaType.APPLICATION_JSON)
				.mediaType("xml", MediaType.APPLICATION_XML)
				.mediaType("x-yaml", MEDIA_TYPE_YML);
	}
}