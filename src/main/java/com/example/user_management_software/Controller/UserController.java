package com.example.user_management_software.Controller;

import com.example.user_management_software.Api.ApiResponse;
import com.example.user_management_software.Model.User;
import com.example.user_management_software.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //GET
    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }
    //ADD
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (userService.addUser(user)){
            return ResponseEntity.status(200).body(new ApiResponse("added"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("Not found"));
    }

    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id,@RequestBody @Valid User user,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean isUpdated = userService.updateUser(id, user);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("User UPDATED"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not Found"));
    }

    //DELETE
    @DeleteMapping("/del/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        if(userService.deleteUser(id)){
            return ResponseEntity.status(200).body(new ApiResponse("User Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not Found"));
    }

    //Check if username and password are correct
    @GetMapping("/get-username-password/{username}/{password}")
    public ResponseEntity checkUsernameAndPassword(@PathVariable String username, @PathVariable String password){
        return ResponseEntity.status(200).body(userService.findUserByUsernameAndPassword(username, password));
    }

    //Get user by email
    @GetMapping("/get-email/{email}")
    public ResponseEntity getByEmail(@PathVariable String email){
        return ResponseEntity.status(200).body(userService.getByEmail(email));
    }
    @GetMapping("/get-role/{role}")
    //Get All users with specific role
    public ResponseEntity getBySpecificRole(@PathVariable String role){
        return ResponseEntity.status(200).body(userService.byRole(role));
    }
    //Get All users with specific age or above
    @GetMapping("get-age/{age}")
    public ResponseEntity getBySpecificAgeOrAbove(@PathVariable Integer age){
        return ResponseEntity.status(200).body(userService.byAgeOrAbove(age));
    }
}
