package andrewjavastudy.demo.mapper;

import andrewjavastudy.demo.model.Questions;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionsMapper {
    @Insert("insert into questions(id,title,description,gmt_create,gmt_modified,creator_id,tags) values(#{id},#{title},#{description},#{gmtCreate},#{gmtModified},#{creatorId},#{tags})")
    public void create(Questions question);

    @Select("select * from questions limit #{offset},#{size}")
    List<Questions> list(@Param(value = "offset") Integer offset,@Param(value="size") Integer size);

    @Select("select count(1) from questions")
    Integer count();

    @Select("select * from questions where creator_id=#{userId} limit #{offset},#{size}")
    List<Questions> listByUserId(@Param(value="userId") Integer userId,@Param(value = "offset") Integer offset,@Param(value="size") Integer size);

    @Select("select count(1) from questions where creator_id=#{userId}")
    Integer countByUserId(@Param(value="userId") Integer userId);

    @Select("select * from questions where id=#{id}")
    Questions getById(@Param("id") Integer id);

    @Update("update questions set title=#{title},description=#{description},gmt_modified=#{gmtModified},tags=#{tags} where id=#{id}")
    void update(Questions questions);
}
