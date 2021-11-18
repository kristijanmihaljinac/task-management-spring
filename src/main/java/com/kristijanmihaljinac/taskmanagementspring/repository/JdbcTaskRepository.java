package com.kristijanmihaljinac.taskmanagementspring.repository;

import com.kristijanmihaljinac.taskmanagementspring.domain.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public class JdbcTaskRepository implements TaskRepository{

    private static final String SELECT_ALL = "SELECT * FROM v_tasks";

    private static final String TABLE_NAME = "tasks";
    private static final String GENERATED_KEY_COLUMN = "id";
    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert jdbcInsert;
    public JdbcTaskRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
        this.jdbcInsert = new SimpleJdbcInsert(jdbc)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(GENERATED_KEY_COLUMN);

    }
    @Override
    public Set<Task> findAll() {
        return Set.copyOf(jdbc.query(SELECT_ALL, this::mapRowToTask));
    }

    @Override
    public Optional<Task> save(Task task) {
        return Optional.empty();
    }

    @Override
    public Set<Task> findByUserAssignedBy(Long userAssignedById) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    private Task mapRowToTask(ResultSet rs, int rowNum) throws SQLException {
        return Task.Create();
    }
}
