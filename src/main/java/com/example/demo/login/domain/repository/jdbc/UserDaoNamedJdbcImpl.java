package com.example.demo.login.domain.repository.jdbc;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

import lombok.RequiredArgsConstructor;

@Repository("UserDaoNamedJdbcImpl")
@RequiredArgsConstructor
public class UserDaoNamedJdbcImpl implements UserDao {

	private final NamedParameterJdbcTemplate jdbc;
	private final static UserRowCallbackHandler rowClallbackhandler = new UserRowCallbackHandler();

	@Override
	public int count() throws DataAccessException {
		SqlParameterSource paramSource = new MapSqlParameterSource();
		return jdbc.queryForObject("SELECT COUNT(*) FROM m_user", paramSource, Integer.class);
	}

	@Override
	public boolean insertOne(User user) throws DataAccessException {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO m_user (")
				.append(" user_id, ")
				.append(" password, ")
				.append(" user_name, ")
				.append(" birthday, ")
				.append(" age, ")
				.append(" marriage, ")
				.append(" role) ")
				.append(" VALUES(:userId,")
				.append(" :password, ")
				.append(" :userName, ")
				.append(" :birthday, ")
				.append(" :age, ")
				.append(" :marriage, ")
				.append(" :role) ");

		SqlParameterSource paramSource = new MapSqlParameterSource()
				.addValue("userId", user.getUserId())
				.addValue("password", user.getPassword())
				.addValue("userName", user.getUserName())
				.addValue("birthday", user.getBirthday())
				.addValue("age", user.getAge())
				.addValue("marriage", user.isMarriage())
				.addValue("role", user.getRole());

		return jdbc.update(sb.toString(), paramSource) > 0;
	}

	@Override
	public User selectOne(String userId) throws DataAccessException {
		SqlParameterSource paramSource = new MapSqlParameterSource()
				.addValue("userId", userId);
		Map<String, Object> result = jdbc.queryForMap("SELECT * FROM m_user WHERE user_id = :userId", paramSource);
		return createOneUser(result);
	}

	@Override
	public List<User> selectAll() throws DataAccessException {
		SqlParameterSource paramSource = new MapSqlParameterSource();
		List<Map<String, Object>> result = jdbc.queryForList("SELECT * FROM m_user", paramSource);
		return result
				.stream()
				.map(rowMap -> {
					return createOneUser(rowMap);
				}).collect(Collectors.toList());
	}

	@Override
	public boolean updateOne(User user) throws DataAccessException {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE m_user SET")
				.append(" user_name = :userId, ")
				.append(" birthday = :birthday, ")
				.append(" age = :age, ")
				.append(" marriage = :marriage ")
				.append(" WHERE user_id = :userId");

		SqlParameterSource paramSource = new MapSqlParameterSource()
				.addValue("userId", user.getUserId())
				.addValue("userName", user.getUserName())
				.addValue("birthday", user.getBirthday())
				.addValue("age", user.getAge())
				.addValue("marriage", user.isMarriage());

		// 例外処理テスト用
//		throw new DataAccessException("トランザクションテスト") {
//		};

		return jdbc.update(sb.toString(), paramSource) > 0;
	}

	@Override
	public boolean deleteOne(String userId) throws DataAccessException {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM m_user WHERE user_id = :userId");

		SqlParameterSource paramSource = new MapSqlParameterSource()
				.addValue("userId", userId);

		return jdbc.update(sb.toString(), paramSource) > 0;
	}

	@Override
	public void userCsvOut() throws DataAccessException {
		jdbc.query("SELECT * FROM m_user ", rowClallbackhandler);
	}

	private User createOneUser(Map<String, Object> oneRecord) {
		User user = new User();
		user.setUserId((String) oneRecord.get("user_id"));
		user.setPassword((String) oneRecord.get("password"));
		user.setUserName((String) oneRecord.get("user_name"));
		user.setBirthday((Date) oneRecord.get("birthday"));
		user.setAge((Integer) oneRecord.get("age"));
		user.setMarriage((Boolean) oneRecord.get("marriage"));
		user.setRole((String) oneRecord.get("role"));
		return user;
	}

}
