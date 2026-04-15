package ceb.service.implement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceb.domain.entity.Users;
import ceb.repository.UsersRepository;
import ceb.service.service.UsersService;
@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    UsersRepository usersRepository;

    @Override
    public List<Users> findAlls() {c
        return usersRepository.findAlls();
    }

    @Override
    public Users getUsersByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));
    }


}

