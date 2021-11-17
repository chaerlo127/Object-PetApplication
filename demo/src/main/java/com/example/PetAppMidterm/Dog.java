package com.example.PetAppMidterm;

public class Dog extends Pet implements Runnable{

    //Pet을 상속하하고, Serializable, Runnable을 작성한다
    //Pet: speak() method @override
    //Runnable: run() method @override

    
    @Override
    public void speak() {
        System.out.println("멍멍");
        
    }

    @Override
    public void run() {
        System.out.println("Dog run!!");
        
    }
    
}
