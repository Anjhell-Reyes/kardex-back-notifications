package com.kardex.domain.model;

public class Type {
    private Long typeId;
    private String type;

    public Type(Long typeId, String type) {
        this.typeId = typeId;
        this.type = type;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
