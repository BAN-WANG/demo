package com.company.api.iapi;

import com.company.api.dto.UserDTO;

import java.util.Date;

/**
 * Created by wsyi on 2016/11/29.
 */
public interface UserApi {
    UserDTO qryById(Long id);
    Boolean add(String name,Date birthday) throws Exception;
}
