package com.kristijanmihaljinac.taskmanagementspring.repository;

import com.kristijanmihaljinac.taskmanagementspring.domain.Priority;
import com.kristijanmihaljinac.taskmanagementspring.domain.Status;
import com.kristijanmihaljinac.taskmanagementspring.domain.Task;
import com.kristijanmihaljinac.taskmanagementspring.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

@Repository
public class JdbcUserRepository implements UserRepository{
    private static final String SELECT_ALL = "SELECT * FROM users";
    private static final String TABLE_NAME = "users";
    private final JdbcTemplate jdbc;

    public JdbcUserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Set<User> findAll() {
        return Set.copyOf(jdbc.query(SELECT_ALL, this::mapRowToUser));
    }

    private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        return User.create(
                rs.getString("username"),
                rs.getString("first_name"),
                rs.getString("last_name")
        );
    }
}
