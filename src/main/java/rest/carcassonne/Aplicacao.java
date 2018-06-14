package rest.carcassonne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
 
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
 
@Configuration // Define a classe como classe de configuração
@EnableAutoConfiguration // Habilita a autoconfiguração
@EnableSwagger //Habilita o Swagger
@ComponentScan(basePackages = {"rest.carcassonne"}) //Escaneia todos os pacotes com o padrão br.com.erudio
public class Aplicacao {
     
    //Injeta uma instancia de SpringSwaggerConfig
    @Autowired
    private SpringSwaggerConfig swaggerConfig;
     
    public static void main(String[] args) {
           //Troque esta linha SpringApplication.run(Application.class, args); pela linha abaixo
           new SpringApplicationBuilder(Aplicacao.class).web(true).run(args);
    }
     
    @Bean
    public SwaggerSpringMvcPlugin groupOnePlugin() {
       return new SwaggerSpringMvcPlugin(swaggerConfig)
            //Adiciona as configurações do Swagger ao SwaggerSpringMvcPlugin 
           .apiInfo(apiInfo()) //Adiciona as propriedades de configuração
           .includePatterns("/partida.*?") //Habilita o Swagger para os nossos 2 endpoints
           .swaggerGroup("admin");
    }
     
    private ApiInfo apiInfo() {
       ApiInfo apiInfo = new ApiInfo(null, null, null, null, null, null);
       return apiInfo;
    }
}