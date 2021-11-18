package com.kristijanmihaljinac.taskmanagementspring.domain;

import lombok.Data;

@Data
public class User {
    private String username;
    private String firstName;
    private String lastName;
    private Boolean enabled;
}
