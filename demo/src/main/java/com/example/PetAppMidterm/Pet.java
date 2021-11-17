package com.example.PetAppMidterm;

import java.util.ArrayList;
import java.util.List;

public abstract class Pet {
    //Cat, Dog class가 @override 하는 abstract(추상) class
    
    abstract public void speak();//추상 method

    List<Listener> listeners = new ArrayList<Listener>();
    public void addListener(Listener listener)
    {
        this.listeners.add(listener);
    }    


    //private를 작성한 이유는 이 class외의 다른 class에서 energy 변경을 하지 않도록 하기 위함이다.
    private int energy = 0;
    
    //Getter & Setter를 통해서 다른 class와 연결한다. 그 이유는 위 주석의 설명으로 대체한다.
    public int getEnergy() {
        return energy;
    }

    //오류 발생시 IllegalArgumentException 실행
    protected void setEnergy(int energy) {
        if(Math.abs(this.energy - energy) <3){
            this.energy = energy;
        }
        else {
            throw new IllegalArgumentException("Energy change is too big");
        }
    }

    //energy 1 증가
    public void eat(){
        energy++;
        check();
    }


    //energy 2 증가
    public void sleep(){
        energy = energy +2;
        check();
       
    }

    //eat, sleep과 class Cat의 grooming의 method가 동일한 하나의 코드를 사용하고 있기 때문에
    //refactoring을 통해서 check() method를 새로 생성
    public void check(){
        if(listeners!=null){
            for(int i=0; i<listeners.size();i++){
                listeners.get(i).energyChanged(energy);
            }
        }
    }

    //모든 Class는 toString method를 가지고 있다.
    //toString을 통해 문자열 더하기 했을 때, 자연스럽게 이 부분이 호출됨을 알 수 있다.
    //Application의 listPets를 할 때 사용하게 됨.
    @Override
    public String toString(){
        return "<a href='./" + this.getClass().getSimpleName().toLowerCase()+"'"+">" +this.getClass().getSimpleName() + "</a>";
    }


}
