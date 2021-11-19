package com.kristijanmihaljinac.taskmanagementspring.repository;

import com.kristijanmihaljinac.taskmanagementspring.domain.Task;
import com.kristijanmihaljinac.taskmanagementspring.domain.User;

import java.util.Set;

public interface UserRepository {
    Set<User> findAll();
}
