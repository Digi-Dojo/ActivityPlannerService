package it.unibz.digidojo.activityplannerservice.domain.usecase.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import it.unibz.digidojo.activityplannerservice.domain.repository.UserRepository;
import it.unibz.digidojo.activityplannerservice.domain.model.user.User;

@Service
public class SearchUser {
    UserRepository userRepository;

    @Autowired
    public SearchUser(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new IllegalArgumentException("User with id '" + id + "' does not exist");
        return user.get();
    }

    public List<User> getAll(){
        List<User> users = userRepository.findAll();
        System.out.println("\nUserList size: " + users.size());
        return users;
    }
}