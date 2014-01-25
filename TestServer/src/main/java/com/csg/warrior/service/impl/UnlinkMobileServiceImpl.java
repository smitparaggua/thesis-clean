package com.csg.warrior.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.warrior.dao.UserDao;
import com.csg.warrior.domain.User;
import com.csg.warrior.service.UnlinkMobileService;

@Service
@Transactional
public class UnlinkMobileServiceImpl implements UnlinkMobileService {
	@Autowired UserDao userDao;
	
	@Override
	public void unlinkMobile(String username, String website) {
		User queriedUser = userDao.getUser(username, website);
		userDao.delete(queriedUser);
	}
}
