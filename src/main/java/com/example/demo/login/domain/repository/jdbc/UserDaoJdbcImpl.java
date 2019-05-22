package com.example.demo.login.domain.repository.jdbc;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDaoJdbcImpl implements UserDao {

	private final JdbcTemplate jdbc;

	@Override
	public int count() throws DataAccessException {
		return jdbc.queryForObject("SELECT COUNT(*) FROM m_user", Integer.class);
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
				.append(" VALUES(?, ?, ?, ?, ?, ?, ?)");

		int rowNumber = jdbc.update(sb.toString(),
				user.getUserId(),
				user.getPassword(),
				user.getUserName(),
				user.getBirthday(),
				user.getAge(),
				user.isMarriage(),
				user.getRole());

		return rowNumber > 0;
	}

	@Override
	public User selectOne(String userId) throws DataAccessException {
		return null;
	}

	@Override
	public List<User> selectAll() throws DataAccessException {
		List<Map<String, Object>> result = jdbc.queryForList("SELECT * FROM m_user");
		return result
				.stream()
				.map(oneMap -> {
					User user = new User();
					user.setUserId((String) oneMap.get("user_id"));
					user.setPassword((String) oneMap.get("password"));
					user.setUserName((String) oneMap.get("user_name"));
					user.setBirthday((Date) oneMap.get("birthday"));
					user.setAge((Integer) oneMap.get("age"));
					user.setMarriage((Boolean) oneMap.get("marriage"));
					user.setRole((String) oneMap.get("role"));
					return user;
				}).collect(Collectors.toList());
	}

	@Override
	public int updateOne(User user) throws DataAccessException {
		return 0;
	}

	@Override
	public int deleteOne(String userId) throws DataAccessException {
		return 0;
	}

	@Override
	public void userCsvOut() throws DataAccessException {
	}

}
