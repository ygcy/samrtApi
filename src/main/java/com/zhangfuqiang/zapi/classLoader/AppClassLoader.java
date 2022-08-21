package com.zhangfuqiang.zapi.classLoader;

import java.io.*;

/**
 * @author zhangfuqiang
 */
public class AppClassLoader extends ClassLoader{

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File(name);
        byte[] buffer = new byte[1024];
        try(
             InputStream is =  new BufferedInputStream(new FileInputStream(file));
             ByteArrayOutputStream io = new ByteArrayOutputStream();
          ) {
            int len = 0;
            while ((len = is.read(buffer)) != -1){
                io.write(buffer, 0, len);
            }
            byte[] bytes = io.toByteArray();
            return super.defineClass(name,bytes, 0, bytes.length);
        }catch (Exception e){
            System.out.println("加载"+name+"类异常"+e);
        }
      return super.findClass(name);
    }
}
