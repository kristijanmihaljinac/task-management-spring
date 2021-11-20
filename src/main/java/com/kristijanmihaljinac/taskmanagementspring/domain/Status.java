package com.kristijanmihaljinac.taskmanagementspring.domain;

import lombok.Data;

@Data
public class Status {
    private Long id;
    private String code;
    private String name;
    private String cssClass;

    private Status(){};

    public static Status create(
            Long id,
            String code,
            String name,
            String cssClass
    ){
        if(id == null || id == 0)
            return null;

        Status st = new Status();
        st.setId(id);
        st.setCode(code);
        st.setName(name);
        st.setCssClass(cssClass);

        return st;
    }

    public static Status create(
            Long id
    ){
        if(id == null || id == 0)
            return null;

        Status st = new Status();
        st.setId(id);

        return st;
    }
}
