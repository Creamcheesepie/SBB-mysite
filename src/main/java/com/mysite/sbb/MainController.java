package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/sbb")
    @ResponseBody
    public String index(){
        return "<h1>안녕하세요 sbb에 온 것을 환영합니다.</h1>";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello sbb";
    }
}
