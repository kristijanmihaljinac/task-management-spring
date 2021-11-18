package com.kristijanmihaljinac.taskmanagementspring.domain;

import lombok.Data;

@Data
public class Status {
    private Long id;
    private String code;
    private String name;
    private String cssClass;
    private Boolean isDefault;
}
