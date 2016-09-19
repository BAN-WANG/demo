package service;

import base.BaseJunitTest;
import com.company.data.model.User;
import com.company.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class UserServiceTest extends BaseJunitTest {
    @Autowired
    private UserService userService;

    @Test
    public void  test(){
        User user = userService.qryUserById(111L);
        Assert.assertTrue(user != null);
    }

}
