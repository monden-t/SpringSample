package com.example.demo.login.domain.service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Transactional
@Service
//@RequiredArgsConstructor
public class UserService {

	// lombokのConstructorとQualifierは同時に使えないので明示的に書く
	public UserService(@Qualifier("UserDaoNamedJdbcImpl") UserDao dao) {
		super();
		this.dao = dao;
	}

	private final UserDao dao;

	public boolean insert(User user) {
		return dao.insertOne(user);
	}

	public int count() {
		return dao.count();
	}

	public List<User> selectAll() {
		return dao.selectAll();
	}

	public User selectOne(String userId) {
		return dao.selectOne(userId);
	}

	public boolean updateOne(User user) {
		return dao.updateOne(user);
	}

	public boolean deleteOne(String userId) {
		return dao.deleteOne(userId);
	}

	public void userCsvOut() throws DataAccessException {
		dao.userCsvOut();
	}

	public byte[] getFile(String fileName) throws IOException {
		Path path = FileSystems.getDefault().getPath(fileName);
		return Files.readAllBytes(path);
	}
}
