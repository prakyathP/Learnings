package com.in28minutes.rest.services.restfulwebservices.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

//This controller would expose the REST API
@RestController //We can expose a REST API from here
public class HelloWorldController {

    private MessageSource messageSource;
    public HelloWorldController(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    //We want to give a specific URL to the REST API
    // Return a piece of text when the URL is entered
    //@RequestMapping(method = RequestMethod.GET, path = "/hello-world")//We want to map this method to a GET request on the URL mentioned in the path

    //---OR---use below method

    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello world");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")//hello-world and path-variable are constants, name is a variable
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello world, %s",name));
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(){
        Locale locale = LocaleContextHolder.getLocale(); //We want to get locale from the request header via a Utility method LocaleContextHolder.getLocale()
        return messageSource.getMessage("good.morning.message",null,"Default Message",locale);
        //return "Hello World V2";
        /*en - English
          nl - Dutch (Goedemorgen)
          fr - French (Bonjour)
          de - Deutsch (Guten Morgen)
         */
    }
}
