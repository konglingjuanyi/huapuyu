package com.anders.ssh.mybatis.dao;

import com.anders.ssh.mybatis.bo.User;
import com.anders.ssh.mybatis.bo.UserCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByCriteria(UserCriteria example);

    int deleteByCriteria(UserCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByCriteria(UserCriteria example);

    User selectByPrimaryKey(Integer id);

    int updateByCriteriaSelective(@Param("record") User record, @Param("example") UserCriteria example);

    int updateByCriteria(@Param("record") User record, @Param("example") UserCriteria example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}