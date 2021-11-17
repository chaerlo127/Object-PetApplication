package com.example.PetAppMidterm;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController("pets")
@EnableAspectJAutoProxy

public class Application {

	static HashMap<String, Pet> pets = new HashMap<String, Pet>();
	
	//main method
	public static void main(String[] args) {
		pets.put(Dog.class.getSimpleName().toLowerCase(),  new Dog());
		pets.put(Cat.class.getSimpleName().toLowerCase(), new Cat());
		SpringApplication.run(Application.class, args);
	}


	//localhost:8080 생성 시, 첫 화면
	@RequestMapping(method = RequestMethod.GET, path="/")
	public String listPets(){
		final StringBuffer result = new StringBuffer();

		result.append("<h1> PET STORE </h1>");

		pets.values().forEach(pet -> {result.append("<li>"+pet.toString());});

		result.append("<p> 총 페이지뷰:" + Homeadvice.getPageView());
		return result.toString();
	}

	//Cat, Dog중 하나를 고르면, 그 내부에 나오는 화면
	//ex) localhost:8080/Cat   <- (URL)
	@RequestMapping(method = RequestMethod.GET, path="{petId}")
	public String showPet(@PathVariable(value = "petId") String petId){
		StringBuffer result = new StringBuffer();
		Pet thePet = pets.get(petId);

		result.append("<h1>" + petId + "</h1>");
		result.append("<br>에너지: " + thePet.getEnergy());
		
		result.append("<br>1, <a href='" + petId+"/feed'>먹이주기</a>");
		result.append("<br>2, <a href='" + petId+"/sleep'>재우기</a>");
		result.append("<br>3, <a href='" + petId+"/cart'>입양하기</a>");

		//Dog에는 없는 또 다른 interface를 implements하는 cat의 유일한 것이다. 
		if(thePet instanceof Groomable) 
		{
			result.append("<br>4, <a href='" + petId+"/groom'>그루밍하기</a>");
		}
		return result.toString();
	}


	@Autowired
	Cart cart;

	//입양하기 버튼을 누르면 화면이 나오게 된다. -> cart class에 @Service 추가하기.
	@RequestMapping(method = RequestMethod.GET, path="{petId}/cart")
	public String addToCart(@PathVariable(value = "petId") String petId) throws Exception{
		Pet thePet = pets.get(petId);
		
			cart.add(thePet);
			return "성공적으로 입양했습니다<br>"+ cart;
		
	}

	//밥 먹기를 누르면 나오게 된다
	//energy 1 씩 증가
	@RequestMapping(method = RequestMethod.GET, path="{petId}/feed")
	public String feed(@PathVariable(value = "petId") String petId){
		Pet thePet = pets.get(petId);

		thePet.eat();//energy 증가

		return "맛있는 거 먹었습니다.";

	}

	//잠자기 화면을 누르면 나오게 된다
	//energy 2 씩 증가
	@RequestMapping(method = RequestMethod.GET, path="{petId}/sleep")
	public String sleep(@PathVariable(value = "petId") String petId){


		Pet thePet = pets.get(petId);

		thePet.sleep();//energy 증가

		return "잘 잤습니다.";

	}

	//Pet으로 진행하게 되면, grooming method를 사용할 수 없기에 강제 타입 변환을 통해서 Pet -> Cat으로 이동하게 해야 한다.
	@RequestMapping(method = RequestMethod.GET, path="{petId}/groom")
	public String groom(@PathVariable(value = "petId") String petId){


		

		Cat cat = (Cat)pets.get(petId);//강제 타입 변환

		String i =cat.grooming();//return variable, energy 증가

		return i;// Cat method에 있는 return variable의 값을 받아서 화면에 나오게 한다.

	}




}
//
