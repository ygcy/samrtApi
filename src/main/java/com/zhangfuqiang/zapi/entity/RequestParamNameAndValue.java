package com.zhangfuqiang.zapi.entity;

/**
 * @author zhangfuqiang
 */
public class RequestParamNameAndValue {

    private String requestTypeName;

    private String requestRealName;

    public RequestParamNameAndValue(String requestTypeName, String requestRealName) {
        this.requestTypeName = requestTypeName;
        this.requestRealName = requestRealName;
    }

    public String getRequestTypeName() {
        return requestTypeName;
    }

    public void setRequestTypeName(String requestTypeName) {
        this.requestTypeName = requestTypeName;
    }

    public String getRequestRealName() {
        return requestRealName;
    }

    public void setRequestRealName(String requestRealName) {
        this.requestRealName = requestRealName;
    }
}
