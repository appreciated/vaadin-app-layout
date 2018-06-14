package com.github.appreciated.app.layout.annotations;

import com.vaadin.navigator.View;

import java.lang.annotation.Annotation;
import java.util.Optional;

public class AnnotationHelper {
    public static <A extends Annotation> Optional<A> getAnnotationFromView(Class<? extends View> info, Class<A> annotationClass) {
        if (info == null || !info.isAnnotationPresent(annotationClass)) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(info.getAnnotation(annotationClass));
        }
    }


    public static <A extends Annotation> Optional<A> getAnnotationOptionally(Class<? extends View> info, Class<A> annotationClass) {
        if (info != null && info.isAnnotationPresent(annotationClass)) {
            return Optional.ofNullable(info.getAnnotation(annotationClass));
        }
        return Optional.empty();
    }
}
