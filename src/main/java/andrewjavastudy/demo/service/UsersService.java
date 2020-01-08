package andrewjavastudy.demo.service;

import andrewjavastudy.demo.mapper.UsersMapper;
import andrewjavastudy.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersMapper usersMapper;


    public void createOrUpdate(Users users) {
        Users dbUsers = usersMapper.findByAccountId(users.getAccountId());
        if (dbUsers == null) {
            //插入处理
            users.setGmtCreate(System.currentTimeMillis());
            users.setGmtModified(users.getGmtCreate());
            usersMapper.insert(users);
        } else {//更新
            dbUsers.setGmtModified(users.getGmtCreate());
            dbUsers.setToken(users.getToken());
            dbUsers.setAvatarUrl(users.getAvatarUrl());
            dbUsers.setName(users.getName());//这个地方一要注意是dbuser 是原有的user对象
            usersMapper.update(dbUsers);
        }
    }
}
