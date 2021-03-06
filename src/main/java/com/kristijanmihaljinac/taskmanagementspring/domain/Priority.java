package com.kristijanmihaljinac.taskmanagementspring.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Data
@Entity
@Table(name = "priorities")
@JsonSerialize
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String cssClass;
    private String icon;

    public Priority(){}

    public static Priority create(
            @Nullable Long id,
            @Nullable String code,
            @Nullable String name,
            @Nullable String cssClass,
            @Nullable String icon
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

    public static Priority create(
            Long id
    ){
        if(id == null || id == 0)
            return null;

        Priority pr = new Priority();
        pr.setId(id);

        return pr;
    }
}
