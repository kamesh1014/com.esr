package com.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	@Qualifier("jdbcthinkService")
	@Autowired
	JdbcTemplate jdbcTemplate;

	public User validdateUser(String userName, String password) {

	//	String query = "select * from registration where username= " + userName + " and password =" + password;
		String sql = "select * from registration where username='" + userName + "' and password='" + password
        + "'";

		List<User> users = jdbcTemplate.query(sql, new UserMapper());
		System.out.println(users);
		System.out.println("size"+users.size());

		return users.size() > 0 ? users.get(0) : null;

	}

	class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int arg1) throws SQLException {
			User user = new User();

			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setFirstname(rs.getString("firstname"));
			user.setLastname(rs.getString("lastname"));
			user.setDob(rs.getString("dob"));
			user.setAddress(rs.getString("address"));
			System.out.println("getting phone numbver");
			String phoneNmu = rs.getString("phone");
			user.setPhone(rs.getString("phone"));
            user.setEmail(rs.getString("email"));
			return user;
		}

	}

}
