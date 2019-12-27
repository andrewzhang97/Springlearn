package andrewjavastudy.demo.mapper;

import andrewjavastudy.demo.model.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface UsersMapper {
    @Insert("insert into users(name,account_id,token,gmt_create,gmt_modified) values(#{name},#{account_id},#{token},#{gmtCreate},#{gmtModified})")
    void insert(Users users);

    @Select("select * from users where token=#{token}")
    Users findByToken(@Param("token") String token);// if it is a class we don't need @param()

}
