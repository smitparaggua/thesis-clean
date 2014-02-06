package com.csg.warrior.raydotcom.service.impl;

import com.csg.warrior.core.WarriorKeyStatus;
import com.csg.warrior.raydotcom.WarriorConfig;
import com.csg.warrior.raydotcom.dao.UserDao;
import com.csg.warrior.raydotcom.domain.User;
import com.csg.warrior.raydotcom.exception.WarriorRequestException;
import com.csg.warrior.raydotcom.service.EmailSenderService;
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
    @Autowired private EmailSenderService emailSenderService;
    private WarriorConfig warriorConfig = new WarriorConfig();

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
        WarriorKeyStatus keyStatus;
        // TODO handle problem if warrior server has errors (and user is not a warrior user)
        try {
            keyStatus = warriorService.getWarriorKeyStatus(username, "ray.com");
        } catch (WarriorRequestException e) {
            e.printStackTrace();
            keyStatus = new WarriorKeyStatus(false, false);
        }
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
    public void unlinkMobileOf(User user) {
        user = getUser(user.getUsername(), user.getPassword());
        if(user != null) {
            String url = warriorService.getUnlinkMobileUrl(user.getUsername(), warriorConfig.getHostWebsite());
            emailSenderService.sendMail(user.getEmail(), "Mobile Key Unlink", url);   // TODO CHANGE THIS LINE
        } else {
            throw new IllegalArgumentException("Invalid username or password");
        }
    }

	@Override
	public User getUser(String username, String password) {
		User user = userDao.getUserByUsername(username);
		if(user != null && user.getPassword().equals(password)) {
			return user;
		}
		else return null;
	}
}
