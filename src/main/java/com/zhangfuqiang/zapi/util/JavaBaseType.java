package com.zhangfuqiang.zapi.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangfuqiang
 */
public class JavaBaseType {

    private static final String intType = Integer.class.getTypeName();
    private static final String boolType = Boolean.class.getTypeName();
    private static final String stringType = String.class.getTypeName();
    private static final String longType = Long.class.getTypeName();
    private static final String doubleType = Double.class.getTypeName();
    private static final String floatType = Float.class.getTypeName();
    private static final String charType = char.class.getTypeName();
    private static final String byteType = Byte.class.getTypeName();
    private static final String intBaseType = int.class.getTypeName();
    private static final String boolBaseType = boolean.class.getTypeName();
    private static final String longBaseType = long.class.getTypeName();
    private static final String doubleBaseType = double.class.getTypeName();
    private static final String floatBaseType = float.class.getTypeName();
    private static final String byteBaseType = byte.class.getTypeName();

    private  static final List<String> baseTypeList = new ArrayList<>();

     static {
         Add add = new Add();
         add.addToList(intType).addToList(boolType).addToList(stringType)
        .addToList(longType).addToList(doubleType).addToList(floatType)
        .addToList(charType).addToList(boolType).addToList(intBaseType)
         .addToList(boolBaseType).addToList(longBaseType).addToList(doubleBaseType)
         .addToList(floatBaseType).addToList(byteType).addToList(byteBaseType);
    }


    private static class Add{
        private Add addToList(String str){
            baseTypeList.add(str);
            return this;
        }
    }


    public static List<String> getBaseTypeList(){
        return baseTypeList;
    }
}
