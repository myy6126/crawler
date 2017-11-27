package com.myy.classLoader;

import com.myy.crawler.CommonWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * wrapper实例类
 *
 * @author caozhaorui
 */
public class WrapperReference {

    private static Logger logger = LoggerFactory.getLogger(WrapperReference.class);

    private WrapperReference() {

    }

    /**
     * @param codeBase
     * @param className
     * @return 根据wrapperid, 取到wrapper代码的reference, 并强转为其父类行
     */
    public static CommonWrapper getCommonWrapperRef(String codeBase, String className) {

        CommonWrapper wrapperIns = null;
        try {

            wrapperIns = (CommonWrapper) getWrapper(codeBase, className);
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
        return wrapperIns;
    }

    /**
     * @param codeBase
     * @param className
     * @return
     * @throws Exception 根据wrapperid,取到wrapper代码的reference,Object类型
     */
    public static Object getWrapper(String codeBase, String className) throws Exception {
        TClassLoaderFactory factory = TClassLoaderFactory.getFactory();
        ClassLoader classloader;
        try {
            classloader = factory.getClassLoader(codeBase);
        } catch (Exception e) {
            logger.error("codeBase: {},className :{} error message :{}", codeBase, className, e.getMessage(), e);
            throw e;
        }
        Class<?> c;
        try {
            c = classloader.loadClass(className);
        } catch (Exception e) {
            logger.error("codeBase: {},className :{} ,error message:{}", codeBase, className, e.getMessage(), e);
            throw e;
        }
        Object wrapperIns;
        try {
            wrapperIns = c.newInstance();
        } catch (Exception e) {
            logger.error("codeBase: {},className :{} error message :{}", codeBase, className, e.getMessage(), e);
            throw e;
        }
        return wrapperIns;
    }
}
