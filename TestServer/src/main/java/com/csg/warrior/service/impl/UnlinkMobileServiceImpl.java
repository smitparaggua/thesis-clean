package com.csg.warrior.service.impl;

import com.csg.warrior.dao.UnlinkKeyDao;
import com.csg.warrior.dao.UserDao;
import com.csg.warrior.domain.UnlinkKey;
import com.csg.warrior.domain.User;
import com.csg.warrior.service.UnlinkMobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("unlinkMobileService")
@Transactional
public class UnlinkMobileServiceImpl implements UnlinkMobileService {
	@Autowired UserDao userDao;
    @Autowired UnlinkKeyDao unlinkKeyDao;

	@Override
	public void unlinkMobile(String username, String website, String unlinkKey) {
		User queriedUser = userDao.getUser(username, website);
		userDao.delete(queriedUser);
	}

    @Override
    public String generateUnlinkUrl(String username, String website) {
        // check if link exists
        User user = userDao.getUser(username, website);
        if(user.getUnlinkKey() == null) {
            UnlinkKey unlinkKey = UnlinkKey.generateKey();
            unlinkKeyDao.save(unlinkKey);
            user.setUnlinkKey(unlinkKey);
            userDao.save(user);
        }
        return String.format("http://localhost:8080/unlink-mobile/%s/%s/%s", username, website, user.getUnlinkKeyString()); // TODO remove hardcoded value
    }
}
