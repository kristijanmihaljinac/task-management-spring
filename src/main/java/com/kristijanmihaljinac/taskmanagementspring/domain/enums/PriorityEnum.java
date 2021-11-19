package com.kristijanmihaljinac.taskmanagementspring.domain.enums;

public enum PriorityEnum {
    LOW(Long.valueOf(1)), LOWEST(Long.valueOf(2)), MEDIUM(Long.valueOf(3)), HIGH(Long.valueOf(4)), HIGHEST(Long.valueOf(5));

    private final Long value;
    private PriorityEnum(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
