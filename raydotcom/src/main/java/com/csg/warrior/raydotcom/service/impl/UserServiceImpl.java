package com.csg.warrior.raydotcom.service.impl;

import com.csg.warrior.raydotcom.dao.UserDao;
import com.csg.warrior.raydotcom.domain.User;
import com.csg.warrior.raydotcom.service.UserService;
import com.csg.warrior.raydotcom.service.WarriorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired private UserDao userDao;
    @Autowired private WarriorService warriorService;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setWarriorService(WarriorService warriorService) {
        this.warriorService = warriorService;
    }

    @Override
    public void save(User user) {
        // TODO add register to Warrior System
        userDao.save(user);
    }

    @Override
    public void signUpToWarrior(User user) {
        // TODO implement sign up to warrior
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(username);
        boolean isUserWarriorLocked = warriorService.isUserLocked(username);
        return toSpringSecurityUser(user, isUserWarriorLocked);
    }

    public UserDetails toSpringSecurityUser(User user, boolean isWarriorLocked) {
        boolean accountEnabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                accountEnabled,
                accountNonExpired,
                credentialsNonExpired,
                !isWarriorLocked,
                authorities
        );
    }
}
