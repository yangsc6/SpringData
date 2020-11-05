package com.ysc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Yangsc
 * @date 2020/11/4
 * @description：
 */
@RestController
public class JDBCController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /*查询数据库的所有信息*/
    /*没有实体类*/
    @RequestMapping("/user")
    public List<Map<String, Object>> userList(){
        String sql = "select * from mybatis.user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);

        return maps;
    }
    /*增删改都用update放方法*/
    /*增*/
    @GetMapping("/add")
    public String addUser(){
        String sql = "insert into mybatis.user(id, name, pwd) VALUES (6, 'xiaowang', 'xw123')";
        jdbcTemplate.update(sql);
        return "add successfully.";
    }

    /*删*/
    @GetMapping("/delete")
    public String deleteUser(){
        String sql = "delete from mybatis.user where id = 6";
        jdbcTemplate.update(sql);
        return "delete successfully.";
    }
    /*改*/
    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id){
        String sql = "update mybatis.user set name = ? , pwd= ? where id =" + id;
        Object[] objects = new Object[2];
        objects[0] = "小猫";
        objects[1] = "xm123";
        jdbcTemplate.update(sql, objects);
        return "update successfully.";
    }
}
