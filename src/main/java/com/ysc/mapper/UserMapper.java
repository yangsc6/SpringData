package com.ysc.mapper;

import com.ysc.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yangsc
 * @date 2020/11/4
 * @description：
 */
/*表示是mybatis的一个mapper接口*/
@Mapper
@Repository
public interface UserMapper {
    /*查找所有用户*/
    List<User> getUserList();
    /*根据用户id查找用户*/
    User getUserById(int id);
    /*增加一个用户*/
    void addUser
    /*删除一个用户*/
    /*修改一个用户*/
}
