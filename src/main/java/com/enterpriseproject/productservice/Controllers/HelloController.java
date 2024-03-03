package com.enterpriseproject.productservice.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//  This class will be having multiple methods that will be ...
//  serving HTTP requests at /hello
//  This class will be serving Rest (HTTP) APIs
//  localhost:8080/hello

@RestController
@RequestMapping("/hello")
public class HelloController {


    //  GET request /hello/say
    @GetMapping("/say/{name}/{times}")
    public String sayHello(@PathVariable("name") String name,
                           @PathVariable("times") int times) {
        String answer = "";

        for(int i=0; i<times; ++i) {
            answer += "Hello " + name;
            answer += "<br>";
        }
        return answer;
    }

    //  something in curly braces becomes a variable

}
