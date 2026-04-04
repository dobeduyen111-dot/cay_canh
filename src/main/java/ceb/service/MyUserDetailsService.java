package ceb.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ceb.model.Users;
import ceb.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Users appUser = repo.findByEmail(email);

        if (appUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return appUser; // RẤT QUAN TRỌNG
    }
}
