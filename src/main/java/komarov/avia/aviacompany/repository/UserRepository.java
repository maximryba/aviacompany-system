package komarov.avia.aviacompany.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import komarov.avia.aviacompany.entity.Role;
import komarov.avia.aviacompany.entity.User;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {
	
	private final JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        user.setRole(Role.valueOf(rs.getString("role")));
        return user;
    };
    
    public List<User> findAll() {
        String sql = "select * from users";

        return jdbcTemplate.query(sql, this.userRowMapper);
    }

	public Optional<User> findByUsername(String username) {
		String sql = "select * from users where username = ?";
		return Optional.ofNullable(jdbcTemplate.queryForObject(sql, this.userRowMapper, username));
	}
	public boolean existsByUsername(String username) {
	    String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
	    Integer count = this.jdbcTemplate.queryForObject(sql, new Object[]{username}, Integer.class);
	    return count != null && count > 0;
	}
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = this.jdbcTemplate.queryForObject(sql, new Object[]{email}, Integer.class);
        return count != null && count > 0;
    }
    
    public Optional<User> findById(int id) {
    	String sql = "select * from users where id = ?";
    	return Optional.ofNullable(this.jdbcTemplate.queryForObject(sql, this.userRowMapper, id));
    }
    
    public void deleteById(int id) {
    	String sql = "delete from users where id = ?";
    	this.jdbcTemplate.update(sql, id);
    }
    
    public User save(User user) {
    	String sql = "insert into users (username, password, email, phone, role) values (?, ?, ?, ?, ?)";
    	this.jdbcTemplate.update(sql, user.getUsername(),
    			user.getPassword(),
    			user.getEmail(),
    			user.getPhone(),
    			Role.USER.name());
    	String searchSql = "select * from users where email = ?";
    	return this.jdbcTemplate.queryForObject(searchSql, this.userRowMapper, user.getEmail());
    }
    
    public User update(User user, int id) {
    	String sql = "update users set username = ?, password = ?, email = ? where id = ?";
    	int userId = this.jdbcTemplate.update(sql, 
    			user.getUsername(),
    			user.getPassword(),
    			user.getEmail());
    	return this.jdbcTemplate.queryForObject(sql, this.userRowMapper, userId);
    }
}