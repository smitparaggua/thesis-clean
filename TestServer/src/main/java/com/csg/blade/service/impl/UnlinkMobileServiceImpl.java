package com.csg.blade.service.impl;

import com.csg.blade.dao.UnlinkKeyDao;
import com.csg.blade.dao.UserDao;
import com.csg.blade.domain.UnlinkKey;
import com.csg.blade.domain.User;
import com.csg.blade.service.UnlinkMobileService;
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
        User user = userDao.getUser(username, website);
        if(user.getUnlinkKey() == null) {
            UnlinkKey unlinkKey = UnlinkKey.generateKey();
            unlinkKeyDao.save(unlinkKey);
            user.setUnlinkKey(unlinkKey);
            userDao.save(user);
        }
        return String.format("http://%s/blade/unlink-mobile/%s/%s", website, username, user.getUnlinkKeyString());
    }
}
