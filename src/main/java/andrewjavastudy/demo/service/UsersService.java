package andrewjavastudy.demo.service;

import andrewjavastudy.demo.mapper.UsersMapper;
import andrewjavastudy.demo.model.Users;
import andrewjavastudy.demo.model.UsersExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersMapper usersMapper;


    public void createOrUpdate(Users users) {
        UsersExample usersExample=new UsersExample();
        usersExample.createCriteria().andAccountIdEqualTo(users.getAccountId());
        List<Users> usersList=usersMapper.selectByExample(usersExample);
        if (usersList.size() == 0) {
            //插入处理
            users.setGmtCreate(System.currentTimeMillis());
            users.setGmtModified(users.getGmtCreate());
            usersMapper.insert(users);
        } else {//更新
            //Users dbUsers=usersList.get(0);
            Users dbUsers=new Users();
            dbUsers.setGmtModified(users.getGmtCreate());
            dbUsers.setToken(users.getToken());
            dbUsers.setAvatarUrl(users.getAvatarUrl());
            dbUsers.setName(users.getName());//这个地方一要注意是dbuser 是原有的user对象
            UsersExample example=new UsersExample();
            example.createCriteria().andIdEqualTo(usersList.get(0).getId());
            usersMapper.updateByExampleSelective(dbUsers,example);
            //usersMapper.update(dbUsers);
        }
    }
}
