package com.kristijanmihaljinac.taskmanagementspring.domain;

import lombok.Data;

@Data
public class User {
    private String username;
    private String firstName;
    private String lastName;

    private User(){}


    public static User Create(
            String username,
            String firstName,
            String lastName
    ){
        if(username.isEmpty() || username == null)
            return null;

        User usr = new User();
        usr.setUsername(username);
        usr.setFirstName(firstName);
        usr.setLastName(lastName);

        return usr;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }
}
