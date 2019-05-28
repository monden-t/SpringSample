package com.example.demo.login.domain.repository.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSqlUtils {

	public static final String INSERT_SQL = "INSERT INTO m_user ("
			+ " user_id, "
			+ " password, "
			+ " user_name, "
			+ " birthday, "
			+ " age, "
			+ " marriage, "
			+ " role) "
			+ " VALUES ( "
			+ " #{userId}, "
			+ " #{password}, "
			+ " #{userName}, "
			+ " #{birthday}, "
			+ " #{age}, "
			+ " #{marriage}, "
			+ " #{role}) ";

	public static final String SELECT_ALL_SQL = "SELECT user_id AS userId, "
			+ " password, "
			+ " user_naem AS userName, "
			+ " birthday, "
			+ " age, "
			+ " marriage, "
			+ " role "
			+ "  FROM m_user ";

	public static final String SELECT_ONE_SQL = SELECT_ALL_SQL
			+ " WHERE user_id = #{userId} ";

	public static final String UPDATE_SQL = "UPDATE m_user SET"
			+ " password = #{password},"
			+ " user_name = #{userName},"
			+ " birthday = #{birthday},"
			+ " age = #{age},"
			+ " marriage = #{marriage}"
			+ " WHERE user_id = #{userId} ";

	public static final String DELETE_SQL = "DELETE FROM m_user WHERE user_id = #{userId}";

}
