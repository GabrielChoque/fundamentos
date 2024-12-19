package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	private UserRepository userRepository;
	private UserService userService;
	public FundamentosApplication(UserRepository userRepository, UserService userService){

	this.userRepository = userRepository;
	this.userService = userService;
	}
	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}
   	@Override
	public void run(String... args){
		saveUsersInDataBase();
	    saveWithErrorTransaccional();
   	}

   private void saveWithErrorTransaccional(){
	   LOGGER.info("********* CARGANDO USUARIOS POR TRANSACCION *************");

	   User userOne = createUser("Roxana","roxana@gmail.com",LocalDate.now());
	   User userTwo = createUser("Wilson","wilson@gmail.com",LocalDate.now());
	   User userThree = createUser("Edith","edith@gmail.com",LocalDate.now());
	   List<User> users = Arrays.asList(userOne, userTwo, userThree);

	   try{
		   	userService.saveListUser(users);
	   } catch (Exception e) {
         LOGGER.error("Ocurrio un error al cargar usuario " + e.getMessage());
	   }
	  		 userService.getAllUsers().stream()
			   .forEach(user -> LOGGER.info("Usuario Creado : " + user));
   }
   private void saveUsersInDataBase(){

	   LOGGER.info("********* CARGANDO USUARIOS *************");
	   User userOne  = createUser("Gabriel","gabriel@gmail.com", LocalDate.of(1999,5,12));
	   User usertwo  = createUser("Jorge","jorge@outlook.com", LocalDate.of(1997,12,8));
	   User userThree = createUser("Dorcas","dorcas@servicios.com", LocalDate.of(2000,8,26));
	   User userFour = createUser("Abigail","abigail@edu.bo.com", LocalDate.of(1996,11,27));
	   User userFive = createUser ("Mario","mario@mteps.com", LocalDate.of(2024,2,17));
	   List<User> list =Arrays.asList(userOne, usertwo, userThree, userFour, userFive);
	   list.stream().forEach(userRepository::save);
   }
   private User createUser(String name, String email, LocalDate birthDay){
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setBirthDate(birthDay);
		return user;
   }
}
