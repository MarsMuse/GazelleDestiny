package com.artisan.agent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * 导出通过JDK动态代理生成的类
 *
 * @author zou yao
 * @since 2021/1/22 17:12
 */
public class ExportJdkProxyClass implements ClassFileTransformer {

    private final static String EXCLUDE_PKG_NAME_JAVA = "java";
    private final static String EXCLUDE_PKG_NAME_SUN = "sun";
    private final static String CONTAINS_CLASS_NAME = "$Proxy";
    private final static String DEF_CLASS_EXPORT_PATH = "/opt/jdk-proxy/";
    /**
     * 启动时配置导出路径
     */
    private final static String JVM_EXPORT_PROXY_PATH_PARAM = "jdkProxyExportPath";
    /**
     * 是否开启启动
     */
    private final static String JVM_EXPORT_PROXY_ENABLED_PARAM = "jdkProxyExportEnabled";

    /**
     * 导出路径
     */
    private volatile String classExportPath = DEF_CLASS_EXPORT_PATH;
    /**
     * 是否启动导出
     */
    private volatile boolean classExportEnabled = true;

    public ExportJdkProxyClass() {
        String jvmExportPath = System.getProperty(JVM_EXPORT_PROXY_PATH_PARAM);
        if (null != jvmExportPath) {
            classExportPath = jvmExportPath;
        }
        String jvmExportEnabled = System.getProperty(JVM_EXPORT_PROXY_ENABLED_PARAM);
        if (null != jvmExportEnabled) {
            classExportEnabled = Boolean.parseBoolean(jvmExportEnabled);
        }
    }

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ExportJdkProxyClass());
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (classExportEnabled && !className.startsWith(EXCLUDE_PKG_NAME_JAVA) && !className.startsWith(EXCLUDE_PKG_NAME_SUN)
                && className.contains(CONTAINS_CLASS_NAME)) {
            try {
                exportClazzToFile(className, classfileBuffer);
            } catch (Throwable e) {
                System.out.println("导出通过JDK动态代理生成的类出现异常，异常信息如下");
                e.printStackTrace();
            }
        }
        return classfileBuffer;
    }

    private void exportClazzToFile(String className, byte[] classfileBuffer) {
        int lastIndexOf = className.lastIndexOf("/") + 1;
        String fileName = className.substring(lastIndexOf) + ".class";
        FileOutputStream fos = null;
        try {
            File file = new File(classExportPath + fileName);
            if (!file.exists()) {
                boolean result = file.createNewFile();
                if (!result) {
                    throw new RuntimeException("文件创建失败");
                }
            }
            fos = new FileOutputStream(file);
            fos.write(classfileBuffer);
            System.out.println(className + " --> EXPORTED Succeess!");
        } catch (Exception e) {
            System.out.println("exception occured while doing some file operation");
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
