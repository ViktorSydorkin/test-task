package com.testtask.repository.inter;

import com.testtask.entity.User;

public interface UserRepo {
    User findByEmail(String email);
    User create(User user);
}
