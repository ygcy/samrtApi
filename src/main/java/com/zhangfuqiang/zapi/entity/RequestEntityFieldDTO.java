package com.zhangfuqiang.zapi.entity;

/**
 * @author zhangfuqiang
 */
public class RequestEntityFieldDTO<T> {

    private Integer id;

    private Integer parentId;

    private String fieldName;

    private String fieldType;

    private T fieldValue;

    public RequestEntityFieldDTO() {
    }

    public RequestEntityFieldDTO(Integer id, Integer parentId, String fieldName, String fieldType, T fieldValue) {
        this.id = id;
        this.parentId = parentId;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.fieldValue = fieldValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public T getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(T fieldValue) {
        this.fieldValue = fieldValue;
    }
}
