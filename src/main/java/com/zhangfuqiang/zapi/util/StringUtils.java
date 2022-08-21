package com.zhangfuqiang.zapi.util;

import com.zhangfuqiang.zapi.annotation.Controller;
import com.zhangfuqiang.zapi.entity.TestEntity;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhangfuqiang
 */
@Controller
public class StringUtils {

    @ResponseBody
    public static boolean isEmpty(String str){
        return str == null || str.trim().length() == 0;
    }

    @ResponseBody
    public static Object isEmpty2(TestEntity testEntity){
        return testEntity;
    }
}
