package com.github.appreciated.app.layout.builder.providers;

import com.vaadin.navigator.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class DefaultSpringNavigationElementInfoProvider extends BasicViewInfoProvider {

    public DefaultSpringNavigationElementInfoProvider() {
        withViewNameProvider(info -> getSpringViewName(info));
    }

    /**
     * This is a workaround since we don't want Spring as a dependency but also don't want code every user needs to copy
     *
     * @param aClass
     * @return
     */
    private String getSpringViewName(Class<? extends View> aClass) {
        Annotation a = Arrays.stream(aClass.getAnnotations())
                .filter(annotation -> annotation.annotationType().getName().equals("com.vaadin.spring.annotation.SpringView"))
                .findFirst()
                .get();
        String result = null;
        try {
            Method m = a.getClass().getMethod("name");
            result = (String) m.invoke(a);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }
}