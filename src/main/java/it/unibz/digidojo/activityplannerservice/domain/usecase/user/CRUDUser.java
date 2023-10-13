package it.unibz.digidojo.activityplannerservice.domain.usecase.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import it.unibz.digidojo.activityplannerservice.domain.repository.UserRepository;
import it.unibz.digidojo.activityplannerservice.domain.model.user.User;

@Service
public class CRUDUser {
    private final UserRepository userRepository;
    private final SearchUser searchUser;

    @Autowired
    public CRUDUser(UserRepository userRepository, SearchUser searchUSer) {
        this.userRepository = userRepository;
        this.searchUser = searchUSer;
    }

    public User createUser(String name){
        return userRepository.save(new User(name));
    }

    public User updateUser(Long userID,String name){
        User toBeModified = searchUser.findById(userID);
        toBeModified.setName(name);
        return userRepository.save(toBeModified);
    }

    public boolean deleteUser(Long id){
        Optional<User> toBeRemoved = userRepository.findById(id);
        if(toBeRemoved.isEmpty()) throw new IllegalArgumentException("User with id '" + id + "' does not exist");
        userRepository.delete(toBeRemoved.get());
        try{
            userRepository.findById(id);
        }catch (Exception e){
            System.out.println("User successfully deleted");
            return true;
        }
        System.out.println("Process unsuccessful, User has not been deleted");
        return false;
    }
}