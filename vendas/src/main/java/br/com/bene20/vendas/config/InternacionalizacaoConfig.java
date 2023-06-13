package br.com.bene20.vendas.config;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class InternacionalizacaoConfig {
    
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages"); // Abrirá o arquivo messages.properties
        messageSource.setDefaultLocale(Locale.getDefault()); //Caso o locale do usuáriop seja en (english), por exemplo, o arquivo carregado seria o messages_en.properties
        messageSource.setDefaultEncoding("ISO-8859-1");
        return messageSource;
    }
    
    /***
     * Método responsável por fazer a interpolação das strings, substituindo as 
     * chaves pelos respectivos valores do arquivo properties correspondente.
     * @return 
     */
    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
