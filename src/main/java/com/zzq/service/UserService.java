package com.zzq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzq.domain.User;

import java.util.List;

public interface UserService extends IService<User> {

    List<User> userList();
}
