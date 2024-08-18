package com.shop_center.hyper_market_ali.user;

import com.shop_center.hyper_market_ali.user.user_role.UserRole;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // public void saveCustomer(Customer customer) {
    // customerRepository.save(customer);
    // }

    @Transactional
    public void updateUser(Long userId, String userName, String password, UserRole userRole) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (userName != null) {
                user.setUserName(userName);
            }
            if (password != null) {
                user.setPassWord(password);
            }
            if (userRole != null) {
                user.setUserRole(userRole);
            }

            userRepository.save(user);
        } else {
            throw new EntityNotFoundException("User with id " + userId + " not found");
        }
    }

    public void deleteUserById(Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User with id " + userId + " not found");
        }

        userRepository.deleteById(userId);
    }

    public Optional<User> findUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User with id " + userId + " not found");
        }

        return userOptional;
    }

    public void addUser(User user) {
        Optional<User> userOptional = userRepository.findById(user.getUserId());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("email taken"); // TODO: this line of code is not correct to handle the exception and needs to get fixed
        }
        userRepository.save(user);
    }
}
