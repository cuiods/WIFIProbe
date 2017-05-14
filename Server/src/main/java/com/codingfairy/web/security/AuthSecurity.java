package com.codingfairy.web.security;

import com.codingfairy.data.dao.UserDao;
import com.codingfairy.data.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User role identify service
 */
@Component
public class AuthSecurity implements UserDetailsService{

    @Resource
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userDao.findByName(s);
        if (userEntity == null) throw new UsernameNotFoundException("Cannot find user: "+s);
        List<GrantedAuthority> authorities = Collections.emptyList();
        authorities.add(new SimpleGrantedAuthority(userEntity.getRole().toString().toUpperCase()));
        return new ServerUser(userEntity.getUsername(),userEntity.getPassword(),true,
                true,true,true,authorities);
    }
}
