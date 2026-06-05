package service;

import dto.UserDTO;
import entity.User;
import enums.Status;
import enums.UserRole;
import repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

public class AuthService {

    private  static AuthService authService;
    private  AuthService(){

    }
    public  static  AuthService getAuthService(){
        if (authService == null){
            authService= new AuthService();
        }
        return authService;
    }


    UserRepository userRepository = UserRepository.getInstance();


    public boolean registration(UserDTO dto) {

      Optional<User>test = userRepository.getUserByEmail(dto.email());

        if (test.isPresent()) return false;

        User user = new User(UUID.randomUUID().toString() , dto.fullName(), dto.email(), dto.password(), Status.ACTIVE , UserRole.USER);
        userRepository.saveUser(user);

        return false;
    }
}
