package com.zhangfuqiang.zapi.app;


import com.zhangfuqiang.zapi.entity.RequestEntity;
import com.zhangfuqiang.zapi.entity.RequestEntityFieldDTO;
import com.zhangfuqiang.zapi.entity.RequestParamNameAndValue;
import com.zhangfuqiang.zapi.entity.TestEntity;
import com.zhangfuqiang.zapi.scan.Scanner;
import com.zhangfuqiang.zapi.entity.FieldAnalyzer;
import com.zhangfuqiang.zapi.util.GsonUtil;
import com.zhangfuqiang.zapi.util.JavaBaseType;
import org.springframework.core.DefaultParameterNameDiscoverer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangfuqiang
 */
public class TestApp {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        start();
    }

    public static void start() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        String basePackage = "com.zfq";
        Scanner scanner = new Scanner(basePackage);
        List<Class> classes = scanner.listClassFileName();

        List<RequestEntity> requestEntityList = new ArrayList<>();

        Class annotationClazz = null;
        for (Class clazz : classes) {
            Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();
            for (Annotation annotation : declaredAnnotations) {
                annotationClazz = annotation.annotationType();
                if ("Controller".equals(annotationClazz.getSimpleName())){
                    requestEntityList.addAll(resolveThisClass(clazz));
                }
            }
        }

        for (RequestEntity requestEntity : requestEntityList) {
            System.out.println(requestEntity.getMethodName().getName());
            System.out.println(requestEntity.getReturnType().getTypeName());

            List<RequestParamNameAndValue> requestParamNameAndValues = requestEntity.buildRequestParamNameAndValue();
            for (RequestParamNameAndValue requestParamNameAndValue : requestParamNameAndValues) {
                if (!JavaBaseType.getBaseTypeList().contains(requestParamNameAndValue.getRequestTypeName())){
                    System.out.println(requestParamNameAndValue.getRequestRealName() +"-=--->"+ requestParamNameAndValue.getRequestTypeName());
                    Class<?> aClass = Class.forName(requestParamNameAndValue.getRequestTypeName());
                    FieldAnalyzer fieldAnalyzer = new FieldAnalyzer();
                    List<RequestEntityFieldDTO> requestEntityFieldDTOS = fieldAnalyzer.parseParam(aClass, requestParamNameAndValue.getRequestRealName(), 0);
                    System.out.println(GsonUtil.toJson(requestEntityFieldDTOS));
                }
            }
        }
    }

    private static List<RequestEntity> resolveThisClass(Class clazz) {
        List<RequestEntity> requestEntityList = new ArrayList<>();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods) {
           if (checkMethodAnnotation(m.getDeclaredAnnotations())){
               requestEntityList.add(buildRequestEntity(m));
           }
        }
        return requestEntityList;
    }

    private static boolean checkMethodAnnotation(Annotation[] declaredAnnotations) {
        if (declaredAnnotations == null || declaredAnnotations.length == 0){
            return false;
        }

        Class methodAnnotationClazz = null;
        for (Annotation annotation : declaredAnnotations) {
            methodAnnotationClazz = annotation.annotationType();
            if ("ResponseBody".equals(methodAnnotationClazz.getSimpleName())){
                return true;
            }
        }

        return false;
    }

    private static RequestEntity buildRequestEntity(Method method) {
        DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(method);
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        Type genericReturnType = method.getGenericReturnType();
        return new RequestEntity(method, genericReturnType, genericParameterTypes, parameterNames);
    }

    private List<Scanner> get(List<TestEntity> list){
        return null;
    }
}
