package life.majiang.community.service;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void createOrUpdate(User user) {
        User findUser = userMapper.findByAccountId(user.getAccountId());
        //判断是否需要新建用户
        if (findUser == null){
            //插入用户
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //更新
            findUser.setGmtModified(System.currentTimeMillis());
            findUser.setAvatarUrl(user.getAvatarUrl());
            findUser.setName(user.getName());
            findUser.setToken(user.getToken());
            findUser.setBio(user.getBio());
            userMapper.updateUser(findUser);
        }


    }
}
