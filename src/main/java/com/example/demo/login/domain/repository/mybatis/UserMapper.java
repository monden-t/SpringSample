package com.example.demo.login.domain.repository.mybatis;

import static com.example.demo.login.domain.repository.common.UserSqlUtils.DELETE_SQL;
import static com.example.demo.login.domain.repository.common.UserSqlUtils.INSERT_SQL;
import static com.example.demo.login.domain.repository.common.UserSqlUtils.SELECT_ALL_SQL;
import static com.example.demo.login.domain.repository.common.UserSqlUtils.SELECT_ONE_SQL;
import static com.example.demo.login.domain.repository.common.UserSqlUtils.UPDATE_SQL;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.login.domain.model.User;

@Mapper
public interface UserMapper {

	@Insert(INSERT_SQL)
	public boolean insert(User user);

	@Select(SELECT_ONE_SQL)
	public User selectOne(String userId);

	@Select(SELECT_ALL_SQL)
	public List<User> selectAll();

	@Update(UPDATE_SQL)
	public boolean updateOne(User user);

	@Delete(DELETE_SQL)
	public boolean deleteOne(String userId);

}
