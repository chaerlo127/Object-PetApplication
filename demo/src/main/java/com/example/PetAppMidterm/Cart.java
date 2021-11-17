package com.example.PetAppMidterm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

//스프링 빈이 자동으로 저장되는 명령어로서, 이것이 없으면 에러가 발생한다.
//필자도, 이를 작성하지 않아 에러가 발생하였었다.
@Component
public class Cart implements ICart{//ICart의 interface로 부터 method인 add, remove를 받아옴

    //ArrayList를 통해 pet의 값을 받아옴
    List<Pet> pets = new ArrayList<Pet>();


    //Application Class를 통해 받아온 Pet pet을 add한다.
    @Override
    public Pet add(Pet pet) throws Exception {
        pets.add(pet);
        return pet;
    }

    //Application Class를 통해 받아온 Pet pet을 remove한다.
    @Override
    public Pet remove(Pet pet) throws Exception {
        pets.remove(pet);
        return pet;
    }

    //toString method를 통해 화면에 보이게 될 입양 목록을 작성하고, Application에 return 한다.
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("입양목록:<br>");
        pets.stream().forEach(pet -> buffer.append("<li>"+pet.toString()));

        return buffer.toString();
    }

    

    
    
}
