package com.example.PetAppMidterm;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

 
@Component
@Aspect//@After를 사용할 수 있게 해줌

//Application에서 이 class를 통해 page view를 게산할 수 있게 해줌
public class Homeadvice {
    static int pageView = 0;
    public static int getPageView(){
        return ++pageView;//0 이상으로 증가하지 않아 ++를 통해 증가하게 해줌
    }

    @After("execution(* com.example.PetAppMidterm.Application.*.*(..))")//pageview가 보이도록 하는 path 작성, path가 틀리면 error 발생
    public void after(){
        System.out.println("--- page view : " + (++pageView));
    }
   
}
