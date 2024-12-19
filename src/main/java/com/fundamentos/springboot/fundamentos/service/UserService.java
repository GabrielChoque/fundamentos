package com.fundamentos.springboot.fundamentos.service;

import com.fundamentos.springboot.fundamentos.dto.UserDto;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final Log LOG = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
       this.userRepository = userRepository;
    }
    @Transactional
    public void saveListUser (List<User> users){
        users.stream().peek(user -> LOG.info("insertado : "+ user))
                .forEach(user -> userRepository.save(user));
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    public void delete (Long id){
        userRepository.deleteById(id);
    }

    public User update(UserDto newUser, Long id) {
      User user =  userRepository.findById(id).get();
      user.setName(newUser.getName());
      user.setEmail(newUser.getEmail());
      user.setBirthDate(newUser.getBirthDate());
      return userRepository.save(user);
    }
}
