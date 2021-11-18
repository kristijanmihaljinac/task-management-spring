package com.kristijanmihaljinac.taskmanagementspring.domain;

import lombok.Data;

@Data
public class Priority {
    private Long id;
    private String code;
    private String name;
    private String cssClass;
    private String icon;

    private Priority(){}

    public static Priority Create(){
        Priority pr = new Priority();

        return pr;
    }
}
