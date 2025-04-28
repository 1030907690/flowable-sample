package com.zzq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzq.domain.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> userList();
}
