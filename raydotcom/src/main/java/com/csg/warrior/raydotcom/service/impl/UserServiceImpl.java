package com.csg.warrior.raydotcom.service.impl;

import com.csg.warrior.raydotcom.dao.UserDao;
import com.csg.warrior.raydotcom.domain.User;
import com.csg.warrior.WarriorKeyStatus;
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

    @Override
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void setWarriorService(WarriorService warriorService) {
        this.warriorService = warriorService;
    }

    @Override
    public void signUp(User user) {
        userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(username);
        WarriorKeyStatus keyStatus= warriorService.getWarriorKeyStatus(username, "ray.com");
        return toSpringSecurityUser(user, isUserWarriorLocked(keyStatus));
    }

    private boolean isUserWarriorLocked(WarriorKeyStatus keyStatus) {
        if(keyStatus.isRegisteredInWarrior() == false) {
            return false;
        } else {
            return !keyStatus.isWarriorKeyValid();
        }
    }

    private UserDetails toSpringSecurityUser(User user, boolean isWarriorLocked) {
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

	@Override
	public User getUser(String username, String password) {
		User user = userDao.getUserByUsername(username);
		if(user.getPassword().equals(password)) {
			return user;
		}
		else return null;
	}
}
