package com.csg.warrior.raydotcom.service.impl;

import com.csg.warrior.core.BladeKeyStatus;
import com.csg.warrior.core.BladeService;
import com.csg.warrior.core.exception.BladeRequestException;
import com.csg.warrior.raydotcom.dao.UserDao;
import com.csg.warrior.raydotcom.domain.User;
import com.csg.warrior.raydotcom.service.EmailSenderService;
import com.csg.warrior.raydotcom.service.UserService;
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
    @Autowired private EmailSenderService emailSenderService;
    private String HOST_NAME = "ray.com";
    private BladeService bladeService = new BladeService("http://localhost:8080/testserver", HOST_NAME);

    @Override
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void setBladeService(BladeService bladeService) {
        this.bladeService = bladeService;
    }

    @Override
    public void signUp(User user) {
        userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(username);
        BladeKeyStatus keyStatus;
        // TODO handle problem if warrior server has errors (and user is not a warrior user)
        try {
            keyStatus = bladeService.getWarriorKeyStatus(username);
        } catch (BladeRequestException e) {
            e.printStackTrace();
            keyStatus = new BladeKeyStatus(false, false); // current solution, ignore warrior if not found
        }
        return toSpringSecurityUser(user, isUserWarriorLocked(keyStatus));
    }

    private boolean isUserWarriorLocked(BladeKeyStatus keyStatus) {
        if(keyStatus.isRegistered() == false) {
            return false;
        } else {
            return !keyStatus.isKeyValid();
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
            String url = bladeService.getUnlinkMobileUrl(user.getUsername());
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
