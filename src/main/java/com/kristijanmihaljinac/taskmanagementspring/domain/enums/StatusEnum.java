package com.kristijanmihaljinac.taskmanagementspring.domain.enums;

public enum StatusEnum {
    TODO(Long.valueOf(1)), IN_PROGRESS(Long.valueOf(2)), DONE(Long.valueOf(3));

    private final Long value;
    private StatusEnum(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
