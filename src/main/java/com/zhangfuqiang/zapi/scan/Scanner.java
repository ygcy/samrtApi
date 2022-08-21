package com.zhangfuqiang.zapi.scan;

import com.zhangfuqiang.zapi.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * @author zhangfuqiang
 */
public class Scanner {

    private String basePackage;

    private Set<File> fileSet = new HashSet<>();

    public Scanner(String basePackage){
        this.basePackage = basePackage;
    }

    public List<String> scan(){
        Enumeration<URL> resources = getUrlEnumeration();

        if (resources == null){
            throw new RuntimeException("基础包下扫描异常");
        }

        List<String> pathList = new ArrayList();
        while (resources.hasMoreElements()){
            String path = resources.nextElement().getPath();
            pathList.add(path);
        }

        for (String realPath : pathList) {
            File f = new File(realPath);
            if (!f.exists()){
                System.out.println(f.getName() + "不存在");
            }
            lookAllFile(f);
        }

        // 过滤所有的class文件
        return getAllClass();
    }

    private List<String> getAllClass() {
        List<String> classList = new ArrayList<>();
        for (File file : fileSet) {
            if (file.getPath().endsWith(".class")){
                classList.add(file.getPath());
            }
        }

        return classList;
    }

    private Enumeration<URL> getUrlEnumeration() {
        Enumeration<URL> resources = null;
        try {
            resources = Thread.currentThread().getContextClassLoader().getResources(basePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resources;
    }

    private String basePath(){
        String basePath = "com/zhangfuqiang/api";
        if (StringUtils.isEmpty(this.basePackage)){
          throw new RuntimeException("请配置基础包路径");
        }

        // 相对路径
        String absPath = this.basePackage.replace(".", "/");
        return absPath;
    }


    private void lookAllFile(File file){
        if (!file.exists()){
            return;
        }

        File[] files = file.listFiles();
        if (isEmpty(files)){
            return;
        }

        for (File f : files) {
            fileSet.add(f);
            lookAllFile(f);
        }

    }

    public List<Class> listClassFileName() throws ClassNotFoundException {
        List<Class> clazzList = new ArrayList<>();
        List<String> listClassFile = scan();
        for (String classFilePath : listClassFile) {
            String className = getClassName(basePackage, classFilePath);
            Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
            clazzList.add(clazz);
        }
        return clazzList;
    }

    private String getClassName(String basePackage, String classFileName) {
        String relClassName = classFileName.replace("\\", ".").replace("/", ".");
        int start = relClassName.indexOf(basePackage);
        int pointIndex = relClassName.lastIndexOf(".");
        String className = relClassName.substring(start, pointIndex);
        return className;
    }

    private boolean isEmpty(File[] files) {
        return files == null || files.length == 0;
    }
}
