package com.kristijanmihaljinac.taskmanagementspring.domain;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class User {
    private String username;
    private String firstName;
    private String lastName;

    private User(){}


    public static User create(
            @Nullable String username,
            @Nullable String firstName,
            @Nullable String lastName
    ){
        if(username == null || username.isEmpty() )
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

    public static User create(
            String username
    ){
        if(username == null || username.isEmpty())
            return null;

        User st = new User();
        st.setUsername(username);

        return st;
    }
}
