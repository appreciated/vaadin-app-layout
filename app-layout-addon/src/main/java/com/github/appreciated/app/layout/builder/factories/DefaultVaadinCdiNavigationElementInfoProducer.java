package com.github.appreciated.app.layout.builder.factories;

import com.vaadin.navigator.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class DefaultVaadinCdiNavigationElementInfoProducer extends BasicViewInfoProducer {

    public DefaultVaadinCdiNavigationElementInfoProducer() {
        withViewNameProvider(info -> getCDIViewName(info));
    }

    private Optional<String> getCDIViewName(Class<? extends View> aClass) {
        Annotation a = Arrays.stream(aClass.getAnnotations())
                .filter(annotation -> annotation.annotationType().getName().equals("com.vaadin.cdi.CDIView"))
                .findFirst()
                .get();
        String result = null;
        try {
            Method m = a.getClass().getMethod("value");
            result = (String) m.invoke(a);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(result);
    }
}