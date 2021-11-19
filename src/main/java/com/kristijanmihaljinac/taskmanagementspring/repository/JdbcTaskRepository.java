package com.kristijanmihaljinac.taskmanagementspring.repository;

import com.kristijanmihaljinac.taskmanagementspring.domain.Priority;
import com.kristijanmihaljinac.taskmanagementspring.domain.Status;
import com.kristijanmihaljinac.taskmanagementspring.domain.Task;
import com.kristijanmihaljinac.taskmanagementspring.domain.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
public class JdbcTaskRepository implements TaskRepository{

    private static final String SELECT_ALL = "SELECT * FROM v_tasks";

    private static final String TABLE_NAME = "tasks";
    private static final String GENERATED_KEY_COLUMN = "id";
    private static final String UPDATE = """
            UPDATE tasks 
            SET subject = ?,
                description = ?,
                user_assigned_to = ?,
                user_assigned_by = ?,
                status_id = ?, 
                priority_id = ?, 
                deadline = ?
            WHERE id = ?
            """;
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
    public Optional<Task> findById(Long id) {
        try{
            return Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + " WHERE id = ?", this::mapRowToTask, id)
            );
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Optional<Task> save(Task task) {
        try {
            return Optional.of(saveTaskDetails(task));
        } catch (DuplicateKeyException e){
            return Optional.empty();
        }
    }

    @Override
    public Set<Task> findByUserAssignedTo(String userAssignedToUsername) {
        try{
            return Set.copyOf(
                    jdbc.query(SELECT_ALL + " WHERE assigned_to_username = ?", this::mapRowToTask, userAssignedToUsername)
            );
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Set<Task> findByUserAssignedToAndStatusId(String userAssignedToUsername, Long statusId) {
        try{
            return Set.copyOf(
                    jdbc.query(
                            SELECT_ALL + " WHERE assigned_to_username = ? AND status_id = ?",
                            this::mapRowToTask,
                            userAssignedToUsername,
                            statusId
                    )
            );
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Set<Task> findByStatusId(Long statusId) {
        try{
            return Set.copyOf(
                    jdbc.query(
                            SELECT_ALL + " WHERE status_id = ?",
                            this::mapRowToTask,
                            statusId
                    )
            );
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        jdbc.update("DELETE FROM tasks WHERE id = ?", id);
    }

    private Task mapRowToTask(ResultSet rs, int rowNum) throws SQLException {
        return Task.Create(
                rs.getLong("id"),
                rs.getString("subject"),
                rs.getString("description"),
                User.Create(
                        rs.getString("assigned_to_username"),
                        rs.getString("assigned_to_first_name"),
                        rs.getString("assigned_to_last_name")
                ),
                User.Create(
                        rs.getString("assigned_by_username"),
                        rs.getString("assigned_by_first_name"),
                        rs.getString("assigned_by_last_name")
                ),
                Status.Create(
                        rs.getLong("status_id"),
                        rs.getString("status_code"),
                        rs.getString("status_name"),
                        rs.getString("status_css_class")
                ),
                Priority.Create(
                        rs.getLong("priority_id"),
                        rs.getString("priority_code"),
                        rs.getString("priority_name"),
                        rs.getString("priority_css_class"),
                        rs.getString("priority_icon")
                ),
                rs.getTimestamp("deadline").toLocalDateTime()
        );
    }

    private Task saveTaskDetails(Task task){

        Map<String, Object> values = new HashMap<>();
        if (task.getId() == 0 || task.getId() == null){
            values.put("subject", task.getSubject());
            values.put("description", task.getDescription());
            values.put("user_assigned_to", task.getAssignedTo().getUsername());
            values.put("user_assigned_by", task.getAssignedBy().getUsername());
            values.put("status_id", task.getStatus().getId());
            values.put("priority_id", task.getPriority().getId());
            values.put("deadline", task.getDeadline());

            task.setId(jdbcInsert.executeAndReturnKey(values).longValue());


        }
        else
        {
            jdbc.update(
                    UPDATE,
                    task.getSubject(),
                    task.getDescription(),
                    task.getAssignedTo().getUsername(),
                    task.getAssignedBy().getUsername(),
                    task.getStatus().getId(),
                    task.getPriority().getId(),
                    task.getDeadline());

        }

        return task;
    }
}
