package com.dmbb.securitytemplate.service.impl;

import com.dmbb.securitytemplate.exception.TmpException;
import com.dmbb.securitytemplate.model.dto.UserDTO;
import com.dmbb.securitytemplate.model.entity.User;
import com.dmbb.securitytemplate.model.entity.UserRole;
import com.dmbb.securitytemplate.model.enums.UserRoles;
import com.dmbb.securitytemplate.repository.UserRepository;
import com.dmbb.securitytemplate.repository.UserRoleRepository;
import com.dmbb.securitytemplate.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserDTO register(UserDTO dto) {
        validateUserDTO(dto);

        User user = userRepository.findByEmail(dto.getEmail());
        if (user != null)
            throw new TmpException("This email is occupied");

        user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());
        user.setActive(true);

        user = userRepository.save(user);

        UserRole roleUser = new UserRole(user, UserRoles.USER);
        userRoleRepository.save(roleUser);

        UserRole roleAdmin = new UserRole(user, UserRoles.ADMIN);
        userRoleRepository.save(roleAdmin);

        return user.toDTO();
    }

    private void validateUserDTO(UserDTO dto) {
        if (StringUtils.isEmpty(dto.getEmail()))
            throw new TmpException("Email is empty");

        if (StringUtils.isEmpty(dto.getPassword()))
            throw new TmpException("Password is empty");
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByEmail(s);
    }
}
