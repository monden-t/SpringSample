package com.example.demo.login.domain.repository.jdbc;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDaoJdbcImpl implements UserDao {

	private final JdbcTemplate jdbc;

//	private final static RowMapper<User> rowMapper = new UserRowMapper();
	private final static RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
	private final static UserResultSetExtractor resultSetExtractor = new UserResultSetExtractor();
	private final static UserRowCallbackHandler rowClallbackhandler = new UserRowCallbackHandler();

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
//		Map<String, Object> result = jdbc.queryForMap("SELECT * FROM m_user WHERE user_id = ?", userId);
//		return createOneUser(result);
		return jdbc.queryForObject("SELECT * FROM m_user WHERE user_id = ?", rowMapper, userId);
	}

	@Override
	public List<User> selectAll() throws DataAccessException {
//		List<Map<String, Object>> result = jdbc.queryForList("SELECT * FROM m_user");
//		return result
//				.stream()
//				.map(rowMap -> {
//					return createOneUser(rowMap);
//				}).collect(Collectors.toList());
//		return jdbc.query("SELECT * FROM m_user ", rowMapper);
		return jdbc.query("SELECT * FROM m_user ", resultSetExtractor);
	}

	@Override
	public boolean updateOne(User user) throws DataAccessException {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE m_user SET")
				.append(" user_name = ?, ")
				.append(" birthday = ?, ")
				.append(" age = ?, ")
				.append(" marriage = ? ")
				.append(" WHERE user_id = ?");

		int rowNumber = jdbc.update(sb.toString(),
				user.getUserName(),
				user.getBirthday(),
				user.getAge(),
				user.isMarriage(),
				user.getUserId());

		return rowNumber > 0;
	}

	@Override
	public boolean deleteOne(String userId) throws DataAccessException {
		return jdbc.update("DELETE FROM m_user WHERE user_id = ?", userId) > 0;
	}

	@Override
	public void userCsvOut() throws DataAccessException {
		jdbc.query("SELECT * FROM m_user ", rowClallbackhandler);
	}

//	private User createOneUser(Map<String, Object> oneRecord) {
//		User user = new User();
//		user.setUserId((String) oneRecord.get("user_id"));
//		user.setPassword((String) oneRecord.get("password"));
//		user.setUserName((String) oneRecord.get("user_name"));
//		user.setBirthday((Date) oneRecord.get("birthday"));
//		user.setAge((Integer) oneRecord.get("age"));
//		user.setMarriage((Boolean) oneRecord.get("marriage"));
//		user.setRole((String) oneRecord.get("role"));
//		return user;
//	}
}
