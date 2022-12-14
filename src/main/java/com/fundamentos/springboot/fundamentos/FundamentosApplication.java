package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.bean.mybean;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	private ComponentDependency componentDependency;
	private mybean mybean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;

	private UserRepository userRepository;
	private UserService userService;
	public FundamentosApplication(@Qualifier("componenttwoimplement") ComponentDependency componentDependency,
								  mybean mybean, MyBeanWithDependency myBeanWithDependency,
								  MyBeanWithProperties myBeanWithProperties, UserPojo userPojo,
								  UserRepository userRepository, UserService userService){
	this.componentDependency = componentDependency;
	this.mybean = mybean;
	this.myBeanWithDependency= myBeanWithDependency;
	this.myBeanWithProperties = myBeanWithProperties;
	this.userPojo = userPojo;
	this.userRepository = userRepository;
	this.userService = userService;
	}
	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}
   @Override
	public void run(String... args){

		saveUsersInDataBase();
		getInformationJpqlFromUser();
	   saveWithErrorTransaccional();
   }

   private void saveWithErrorTransaccional(){
		User test1 = new User("testTransaccional1","asfasd@gmail.com",LocalDate.now());
	   User test2 = new User("test2Transaccional1","as542fasd@gmail.com",LocalDate.now());
	   User test3 = new User("test3Transaccional1","asdd@gmail.com",LocalDate.now());
	   User test4 = new User("test4Transaccional1","asf78asd@gmail.com",LocalDate.now());
	   List<User> users = Arrays.asList(test1, test2, test3,test4);
	   try{
		   userService.saveTransaccional(users);
	   }catch (Exception e){
         LOGGER.error("exceccion con el metodo transaccional");
	   }

	   userService.getAllUsers().stream()
			   .forEach(user -> LOGGER.info("usuario transaccional: " + user));

   }
   private void getInformationJpqlFromUser(){
		/*LOGGER.info("metodo findyUserEmail: " +
				userRepository.findyUserEmail("1sd")
						.orElseThrow(() ->  new RuntimeException("no se encontro ususario")) );*/
		/*userRepository.findAndSort("j", Sort.by("id").descending())
				.stream().forEach(user -> LOGGER.info("Usuario con metodo sort" + user));*/
	   //userRepository.findByName("jorge").stream().forEach(User -> LOGGER.info("nombre encontrado:" + User));
	 /* LOGGER.info(userRepository.findByEmailAndName("123@gmail.com","gabriel")
			   .orElseThrow(()-> new RuntimeException("no se encontro papi")));*/
	  //userRepository.findByNameLike("%a%").stream().forEach(user -> LOGGER.info("usuarios encontrados: " +user));
	  /*userRepository.findByNameOrEmail(null,"nyutf@gmail.com").stream()
			  .forEach(user -> LOGGER.info("ususarios encontrados : " +user));*/
	   /*userRepository.findByBirthDateBetween(LocalDate.of(2021,1,1),
			   LocalDate.of(2022,1,1)).stream()
			   .forEach(user -> LOGGER.info("Usuarios de ese año: " + user));*/
	  /* userRepository.findByNameLikeOrderByIdDesc("%j%").stream()
			   .forEach(user-> LOGGER.info("usuarios encontrados : " + user));*/

	   LOGGER.info(userRepository.getAllByBirthDateAndEmail(LocalDate.of(2020,7,13),"ertr@gmail.com")
			   .orElseThrow(() -> new RuntimeException("no se encontro Gabo")));
   }
   private void saveUsersInDataBase(){
	   User user1 = new User("gabriel","123@gmail.com", LocalDate.of(2021,3,12));
	   User user2 = new User("jorge","abc@gmail.com", LocalDate.of(2020,10,7));
	   User user3 = new User("pepe","ertr@gmail.com", LocalDate.of(2020,7,13));
	   User user4 = new User("beymar","wer@gmail.com", LocalDate.of(2020,1,21));
	   User user5 = new User("jenrique","agf@gmail.com", LocalDate.of(2020,2,20));
	   User user6 = new User("jorge","hgn@gmail.com", LocalDate.of(2020,3,8));
	   User user7 = new User("nahomi","hyuj@gmail.com", LocalDate.of(2020,4,5));
	   User user8 = new User("jimena","nyutf@gmail.com", LocalDate.of(2020,5,22));
	   User user9 = new User("jsergio","wqre@gmail.com", LocalDate.of(2020,6,12));
	   User user10 = new User("wilson","fdgerg@gmail.com", LocalDate.of(2020,8,20));
	   List<User> list =Arrays.asList(user1, user2,user3,user6,user4,user5,user10,user7,user8,user9);
	   list.stream().forEach(userRepository::save);



   }
   private void ejemplo(){
	   componentDependency.saludar();
	   mybean.print();
	   myBeanWithDependency.printWithDependency();
	   System.out.println(myBeanWithProperties.function());
	   System.out.println("anio : "+userPojo.getAge() + " contrasenia : "+ userPojo.getPassword() + " correo : " + userPojo.getEmail());
   }
}
