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

    public static Priority Create(
            Long id,
            String code,
            String name,
            String cssClass,
            String icon
    ){
        if(id == null || id == 0)
            return null;

        Priority pr = new Priority();
        pr.setId(id);
        pr.setCode(code);
        pr.setName(name);
        pr.setCssClass(cssClass);
        pr.setIcon(icon);
        return pr;
    }
}
