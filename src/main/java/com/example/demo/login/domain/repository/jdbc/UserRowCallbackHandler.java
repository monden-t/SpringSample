package com.example.demo.login.domain.repository.jdbc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;

public class UserRowCallbackHandler implements RowCallbackHandler {

	@Override
	public void processRow(ResultSet rs) throws SQLException {
		try {
			File file = new File("sample.csv");
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			// RowCallbackHandlerの参照元であるextractDataがwhile(rs.next())で回しているので、
			// ループ後に閉じるものがないならここでループを回す必要はない。
			do {
				StringBuilder sb = new StringBuilder();
				sb.append(rs.getString("user_id")).append(",");
				sb.append(rs.getString("password")).append(",");
				sb.append(rs.getString("user_name")).append(",");
				sb.append(rs.getDate("birthday")).append(",");
				sb.append(rs.getInt("age")).append(",");
				sb.append(rs.getBoolean("marriage")).append(",");
				sb.append(rs.getString("role"));
				bw.write(sb.toString());
				bw.newLine();
			} while (rs.next());
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
