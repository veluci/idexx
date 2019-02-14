package idexx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan("idexx")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public RestTemplate customRestTemplate (){
        RestTemplate customRestTemplate = new RestTemplate ();

        HttpMessageConverter<?> myConverter = customRestTemplate.getMessageConverters().stream()
            .filter(MappingJackson2HttpMessageConverter.class::isInstance)
            .findFirst()
            .orElse(null);

        List<MediaType> myMediaTypes = new ArrayList<MediaType>();
        myMediaTypes.addAll (myConverter.getSupportedMediaTypes ());
        myMediaTypes.add (MediaType.parseMediaType ("text/javascript; charset=utf-8"));
        ((MappingJackson2HttpMessageConverter) myConverter).setSupportedMediaTypes (myMediaTypes);
        return customRestTemplate;

    }

}
