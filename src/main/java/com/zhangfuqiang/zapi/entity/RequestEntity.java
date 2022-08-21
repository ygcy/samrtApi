package com.zhangfuqiang.zapi.entity;

import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangfuqiang
 */
public class RequestEntity {

    /**
     * 请求方法名称
     */
    private Method methodName;

    /**
     * 方法返回类型
     */
    private Type returnType;

    /**
     *  请求参数类型
     */
    private Type[] genericParameterTypes;

    /**
     * 真实的形参名称
     */
    private String[] realParameterNames;

    public RequestEntity() {
    }

    public RequestEntity(Method methodName, Type returnType, Type[] genericParameterTypes, String[] realParameterNames) {
        this.methodName = methodName;
        this.returnType = returnType;
        this.genericParameterTypes = genericParameterTypes;
        this.realParameterNames = realParameterNames;
    }

    public List<RequestParamNameAndValue> buildRequestParamNameAndValue(){
        List<RequestParamNameAndValue> row = new ArrayList<>();
        if (realParameterNames == null || realParameterNames.length == 0){
            return row;
        }
        for (int i = 0; i < realParameterNames.length; i++) {
            row.add(new RequestParamNameAndValue(genericParameterTypes[i].getTypeName(), realParameterNames[i]));
        }
        return row;
    }

    public Method getMethodName() {
        return methodName;
    }

    public void setMethodName(Method methodName) {
        this.methodName = methodName;
    }

    public Type getReturnType() {
        return returnType;
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }

    public Type[] getGenericParameterTypes() {
        return genericParameterTypes;
    }

    public void setGenericParameterTypes(Type[] genericParameterTypes) {
        this.genericParameterTypes = genericParameterTypes;
    }

    public String[] getRealParameterNames() {
        return realParameterNames;
    }

    public void setRealParameterNames(String[] realParameterNames) {
        this.realParameterNames = realParameterNames;
    }
}
