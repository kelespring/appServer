package com.gh.appserver.security.mgt;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadContext {
    private static final Logger log = LoggerFactory.getLogger(ThreadContext.class);
    public static final String SECURITY_MANAGER_KEY = ThreadContext.class.getName() + "_SECURITY_MANAGER_KEY";
    public static final String SUBJECT_KEY = ThreadContext.class.getName() + "_SUBJECT_KEY";
    private static final ThreadLocal<Map<Object, Object>> resources = new InheritableThreadLocal<Map<Object, Object>>();

    public static Subject getSubject() {
        return (Subject) get(SUBJECT_KEY);
    }
    
    public static Object get(Object key) {
    	 Map<Object, Object> perThreadResources = resources.get();
         return perThreadResources != null ? perThreadResources.get(key) : null;
    }
    
    private static void ensureResourcesInitialized(){
        if (resources.get() == null){
           resources.set(new HashMap<Object, Object>());
        }
    }
    
    public static void put(Object key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        if (value == null) {
            remove(key);
            return;
        }

        ensureResourcesInitialized();
        resources.get().put(key, value);

        if (log.isTraceEnabled()) {
            String msg = "Bound value of type [" + value.getClass().getName() + "] for key [" +
                    key + "] to thread " + "[" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }
    }
    
    public static Object remove(Object key) {
        Map<Object, Object> perThreadResources = resources.get();
        Object value = perThreadResources != null ? perThreadResources.remove(key) : null;

        if ((value != null) && log.isTraceEnabled()) {
            String msg = "Removed value of type [" + value.getClass().getName() + "] for key [" +
                    key + "]" + "from thread [" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }

        return value;
    }
    
    public static void bind(Subject subject) {
        if (subject != null) {
            put(SUBJECT_KEY, subject);
        }
    }
}
