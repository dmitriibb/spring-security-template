package com.dmbb.securitytemplate.service;

import com.dmbb.securitytemplate.model.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    UserDTO register(UserDTO dto);

}
