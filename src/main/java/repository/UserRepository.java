package repository;

import entity.User;

import java.util.Optional;

public class UserRepository {

    private static UserRepository userRepository;
    private UserRepository(){}

    public  static  UserRepository getInstance(){
        if (userRepository==null){
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    public  void  saveUser(User user){

    }

    public Optional<User>getUserByEmail(String email){



        return Optional.empty();
    }


}
