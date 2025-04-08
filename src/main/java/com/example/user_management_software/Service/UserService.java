package com.example.user_management_software.Service;

import com.example.user_management_software.Model.User;
import com.example.user_management_software.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //GET
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //ADD
    public boolean addUser(User user){
         userRepository.save(user);
         return true;
    }

    //UPDATE
    public boolean updateUser(Integer id , User user){
        User oldUser = userRepository.findUserById(id);
        if(oldUser == null){
            return false;
        }
        oldUser.setName(user.getName());
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        oldUser.setRole(user.getRole());
        oldUser.setAge(user.getAge());
        userRepository.save(oldUser);
        return true;
    }
    //DELETE
    public boolean deleteUser(Integer id){
        User delUser = userRepository.findUserById(id);
        if(delUser == null){
            return false;
        }
        userRepository.delete(delUser);
        return true;
    }
    //Check if username and password are correct
    public List<User> findUserByUsernameAndPassword(String username,String password){
        return userRepository.findUserByUsernameAndPassword(username, password);
    }
    //Get user by email
    public User getByEmail(String email){
        return userRepository.byemail(email);
    }

    //Get All users with specific role
    public List<User> byRole(String role){
        return userRepository.bySpecificRole(role);
    }

    //Get All users with specific age or above
    public List<User> byAgeOrAbove(Integer age){
        return userRepository.bySpecificAgeOrAbove(age);
    }

}
