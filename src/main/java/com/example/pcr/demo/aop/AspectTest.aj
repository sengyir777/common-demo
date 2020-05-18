package com.example.pcr.demo.aop;

public aspect AspectTest {
    pointcut HelloWorldPointCut() : call(* main (int));


    before() : HelloWorldPointCut(){
        System.out.println("Entering : " + thisJoinPoint.getSourceLocation());
    }
}
