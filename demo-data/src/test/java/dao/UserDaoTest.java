package dao;

import base.BaseJunitTest;
import com.company.data.dao.UserDao;
import com.company.data.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoTest extends BaseJunitTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void test(){
        User u  = userDao.qryById(111L);
        Assert.assertTrue(u != null);
    }

}
