package com.springsecurity.Asst.config;

import com.springsecurity.Asst.entity.Userinfo;
import com.springsecurity.Asst.repository.UserinfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserinfoRepository userinfoRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Userinfo> userInfo = userinfoRepository.findByName(username);
        return userInfo.map(AuthProvider::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
}
