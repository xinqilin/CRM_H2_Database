package com.bill.crm.dao;

import com.bill.crm.entity.User;
import com.bill.crm.repository.UserRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDaoImpl {


    private final UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initUser() {
        List<User> users = new ArrayList<>();
        User superUser = User.builder().username("superuser").password("bill").role("superuser".toUpperCase()).build();
        User manager = User.builder().username("manager").password("bill").role("manager".toUpperCase()).build();
        User operator = User.builder().username("operator").password("bill").role("operator".toUpperCase()).build();

        users.add(superUser);
        users.add(manager);
        users.add(operator);

        userRepository.saveAll(users);
    }

    public Optional<User> getByUsername(String username) {
        if (Strings.isEmpty(username)) return null;
        return userRepository.findByUsername(username);
    }
}
