package com.zhangfuqiang.zapi.entity;

import com.zhangfuqiang.zapi.entity.RequestEntityFieldDTO;
import com.zhangfuqiang.zapi.entity.RequestParamNameAndValue;
import com.zhangfuqiang.zapi.util.JavaBaseType;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author zhangfuqiang
 */
public class FieldAnalyzer {

    private int id = 0;

    private List<RequestEntityFieldDTO> row = new ArrayList<>();

    public List<RequestEntityFieldDTO> parseParam(Class clazz, String realName, int parentId) throws IllegalAccessException {
        RequestEntityFieldDTO requestEntityFieldDTO = new RequestEntityFieldDTO(++id, parentId, realName, clazz.getTypeName(), null);
        row.add(requestEntityFieldDTO);
        Field[] fields = clazz.getDeclaredFields();
        int pId = requestEntityFieldDTO.getId();
        if (parentId > 0){
            pId = parentId;
        }

        for (int i = 0, len = fields.length; i < len; i++) {
            parseFiled(fields[i], pId);
        }
        return row;
    }


    private <T> void parseFiled(Field field, int parentId) throws IllegalAccessException {
        field.setAccessible(true);
        String name = field.getType().getName();
        RequestEntityFieldDTO requestEntityFieldDTO = new RequestEntityFieldDTO(++id, parentId, field.getName(), field.getType().getTypeName(), null);
        row.add(requestEntityFieldDTO);
        if (JavaBaseType.getBaseTypeList().contains(name)){
            return;
        }

        try {
            Class<?> clazz = getClass(field);
            parseParam(clazz, field.getName(), requestEntityFieldDTO.getId());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private Class<?> getClass(Field field) throws ClassNotFoundException {

        if ("java.util.List".equals(field.getType().getName())){
            Type genericType = field.getGenericType();
            if (genericType instanceof ParameterizedType){
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                Type actualTypeArgument = parameterizedType.getActualTypeArguments()[0];
                return Class.forName(actualTypeArgument.getTypeName());
            }
        }
       return Class.forName(field.getType().getName());

    }


    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        System.out.println(String.class.getName());
    }
}
