package andrewjavastudy.demo.mapper;

import andrewjavastudy.demo.model.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface UsersMapper {
    @Insert("insert into users(name,account_id,token,gmt_create,gmt_modified) values(#{name},#{account_id},#{token},#{gmtCreate},#{gmtModified})")
    void insert(Users users);

}
