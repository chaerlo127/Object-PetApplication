package com.example.PetAppMidterm;

public interface ICart {
    //Cart가 @override하는 method 정의
    Pet add(Pet pet) throws Exception;
    Pet remove(Pet pet) throws Exception;    
}
