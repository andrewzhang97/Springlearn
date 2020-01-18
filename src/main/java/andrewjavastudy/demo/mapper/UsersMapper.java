package andrewjavastudy.demo.mapper;

import andrewjavastudy.demo.model.Users;
import andrewjavastudy.demo.model.UsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UsersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    long countByExample(UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    int deleteByExample(UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    int insert(Users record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    int insertSelective(Users record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    List<Users> selectByExampleWithRowbounds(UsersExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    List<Users> selectByExample(UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    Users selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    int updateByPrimaryKeySelective(Users record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Sun Jan 19 01:14:02 HKT 2020
     */
    int updateByPrimaryKey(Users record);
}