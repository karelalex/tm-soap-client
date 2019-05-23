package ru.karelin.tmsoapclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.karelin.tmwebspring.soap.*;

import javax.xml.ws.BindingProvider;

@Configuration
@ComponentScan(value = "ru.karelin.tmsoapclient")
public class MainConfig {

    @Bean
    public LoginEndpoint loginEndpoint(){
        LoginEndpoint endpoint = new LoginEndpointService().getLoginEndpointPort();
        ((BindingProvider)endpoint).getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);
        return endpoint;
    }

  /* @Bean
    public UserEndpoint userEndpoint(){
        return new UserEndpointService().getUserEndpointPort();
    }*/

    @Bean
    public ProjectEndpoint projectEndpoint(){
        ProjectEndpoint endpoint = new ProjectEndpointService().getProjectEndpointPort();
        ((BindingProvider)endpoint).getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);
        return endpoint;
    }

    @Bean
    public TaskEndpoint taskEndpoint(){
        TaskEndpoint endpoint = new TaskEndpointService().getTaskEndpointPort();
        ((BindingProvider)endpoint).getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);
        return endpoint;
    }
}
