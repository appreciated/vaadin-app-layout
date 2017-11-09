package com.github.appreciated.app.layout.builder.providers;

import com.github.appreciated.app.layout.annotations.MenuCaption;
import com.github.appreciated.app.layout.annotations.MenuIcon;
import com.github.appreciated.app.layout.builder.AppLayoutConfiguration;
import com.github.appreciated.app.layout.builder.entities.NavigationElementInfo;
import com.vaadin.navigator.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class DefaultSpringNavigationElementInfoProvider implements AppLayoutConfiguration.NavigationElementInfoProvider {

    @Override
    public NavigationElementInfo apply(Class<? extends View> aClass) {
        String viewName = getSpringViewName(aClass);
        return new NavigationElementInfo(
                Optional.ofNullable(aClass.getAnnotation(MenuCaption.class)) // Caption
                        .map(menuElement -> menuElement.value())
                        .orElse(viewName),
                Optional.ofNullable(aClass.getAnnotation(MenuIcon.class))  // Icon
                        .map(menuElement -> menuElement.value())
                        .orElse(null),
                viewName);
    }

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