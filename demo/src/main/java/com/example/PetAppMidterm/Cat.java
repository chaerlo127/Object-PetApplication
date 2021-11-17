package com.example.PetAppMidterm;

import java.io.Serializable;

public class Cat extends Pet implements Groomable, Serializable, Runnable{//Pet을 상속하하고, Groomable, Serializable, Runnable을 작성한다
    //Groomable: 그루밍 하게 해주는 method @override
    //Pet: speak() method @override
    //Runnable: run() method @override

    private int energy = 0;
    //private를 작성한 이유는 이 class외의 다른 class에서 energy 변경을 하지 않도록 하기 위함이다.
    
    @Override
    public void speak() {
        if(getEnergy()<5){//getEnergy가 5보다 작으면 I'm hungry로 나오게 됨
            System.out.println("I'm hungry");
        }        
        else System.out.println("HI");
    }

    @Override
    public void run() {
        System.out.println("I'm running!");
    }

    @Override
    public String grooming() {
    
    energy++;
    setEnergy(energy);//setEnergy를 통해 energy의 값을 저장 -> private 때문에
    check();//refectoring한 pet의 method 사용

    return "야옹, 행복하다 집사야";
    }
    
}
