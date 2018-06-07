package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutElementBase;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.design.AppLayoutDesign;
import com.github.appreciated.app.layout.builder.elements.AbstractNavigationElement;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.elements.SectionNavigationElement;
import com.github.appreciated.app.layout.builder.elements.SubmenuNavigationElement;
import com.github.appreciated.app.layout.builder.entities.NavigationElementInfo;
import com.github.appreciated.app.layout.builder.factories.DefaultNavigationElementInfoProducer;
import com.github.appreciated.app.layout.builder.factories.left.DefaultLeftClickableNavigationElementFactory;
import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;
import com.github.appreciated.app.layout.builder.interfaces.Factory;
import com.github.appreciated.app.layout.builder.interfaces.HasCaptionInterceptor;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Since the class AppLayoutBuilder was grew so large I decided to outsource the logic to configure an AppLayout instance into this class.
 * <p>
 * For every "Factory" you find in the class some information:
 * The following factories allow the user to exchange any {@link HasElement} that will be added to the
 * {@link AppLayoutElementBase} instance for any {@link Behaviour} or custom implementation
 */
public class AppLayoutConfiguration {

    private List<AbstractNavigationElement> navigationElements = new ArrayList<>();
    private List<Component> appBarElements = new ArrayList<>();

    private AppLayoutDesign design = AppLayoutDesign.DEFAULT;
    private String title;
    private ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> navigationElementProvider;
    private DefaultLeftClickableNavigationElementFactory customElementProvider;
    private ComponentFactory<HasElement, SectionNavigationElement> sectionProvider;
    private ComponentFactory<SubmenuNavigationElement.SubmenuElement, SubmenuNavigationElement> submenuProvider;

    private NavigationElementInfoProducer navigationElementInfoProvider = new DefaultNavigationElementInfoProducer();
    private List<AbstractNavigationElement> footerElements = new ArrayList<>();
    private List<AbstractNavigationElement> headerElements = new ArrayList<>();
    private List<NavigatorNavigationElement> navigatorElements = new ArrayList<>();
    private Component appBarIconComponent;

    private AppLayoutElementBase instance;

    private Factory<String, String> routeInterceptor = null;
    private Factory<String, String> captionInterceptor;

    private HasElement titleComponent;

    public AppLayoutConfiguration(AppLayoutElementBase instance) {
        this.instance = instance;
    }

    /**
     * FIXME this configuration should only hold configuration => Single responsibility principle
     * This terminating build method is part of the builder and not of the configuration.
     */
    public Component build() {
        if (titleComponent == null) {
            instance.setTitle(title);
        } else {
            instance.setTitleElement(titleComponent);
        }

        addComponents(headerElements, instance::addNavigationHeaderElement);
        addComponents(navigationElements, instance::addNavigationElement);
        addComponents(footerElements, instance::addNavigationFooterElement);
        appBarElements.forEach(instance::addAppBarElement);
        instance.setDesign(design);
        if (appBarIconComponent != null) {
            instance.addAppBarIcon(appBarIconComponent);
        }
        instance.setNavigatorNavigationElements(navigatorElements);
        return (Component) instance;
    }

    private void addComponents(List<AbstractNavigationElement> elements, Consumer<AbstractNavigationElement> consumer) {
        elements.forEach(element -> {
            prepareNavigationElement(element);
            consumer.accept(element);
        });
    }

    private void prepareNavigationElement(AbstractNavigationElement element) {
        if (element instanceof HasCaptionInterceptor) {
            ((HasCaptionInterceptor) element).setCaptionInterceptor(captionInterceptor);
        }
        if (element instanceof NavigatorNavigationElement) {
            NavigatorNavigationElement nElement = (NavigatorNavigationElement) element;
            nElement.setNavigationElementInfoProvider(navigationElementInfoProvider);
            nElement.setRouteInterceptor(routeInterceptor);
            nElement.initRouterInformation();
            navigatorElements.add(nElement);
        } else if (element instanceof SubmenuNavigationElement) {
            SubmenuNavigationElement sElement = (SubmenuNavigationElement) element;
            sElement.getSubmenuElements().forEach(element1 -> prepareNavigationElement(element1));
        }
    }

    public void add(AbstractNavigationElement element, Section section) {
        addToPosition(element, section);
    }

    public void addToPosition(AbstractNavigationElement element, Section section) {
        try {
            switch (section) {
                case HEADER:
                    headerElements.add(element);
                    break;
                case DEFAULT:
                    navigationElements.add(element);
                    break;
                case FOOTER:
                    footerElements.add(element);
                    break;
            }
        } catch (Exception e) {
            System.err.println("Section Can't be null in Element:" + element.getComponent().getElement() + "\n ->" + Arrays.toString(e.getStackTrace()));
        }
    }

    /***** Getters and Setters *****/

    public List<AbstractNavigationElement> getNavigationElements() {
        return navigationElements;
    }

    public AppLayoutDesign getDesign() {
        return design;
    }

    public void setDesign(AppLayoutDesign design) {
        this.design = design;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Component> getAppBarElements() {
        return appBarElements;
    }

    public void setNavigationElementProvider(ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> navigationElementProvider) {
        this.navigationElementProvider = navigationElementProvider;
    }

    public void setSectionProvider(ComponentFactory<HasElement, SectionNavigationElement> sectionProvider) {
        this.sectionProvider = sectionProvider;
    }

    public void setSubmenuProvider(ComponentFactory<SubmenuNavigationElement.SubmenuElement, SubmenuNavigationElement> submenuProvider) {
        this.submenuProvider = submenuProvider;
    }

    public void setNavigationElementInfoProvider(NavigationElementInfoProducer navigationElementInfoProvider) {
        this.navigationElementInfoProvider = navigationElementInfoProvider;
    }

    public void setAppBarIconComponent(Component appBarIconComponent) {
        this.appBarIconComponent = appBarIconComponent;
    }

    public void setRouteInterceptor(Factory<String, String> routeInterceptor) {
        this.routeInterceptor = routeInterceptor;
    }

    public void setCaptionInterceptor(Factory<String, String> captionInterceptor) {
        this.captionInterceptor = captionInterceptor;
    }

    public void setTitleComponent(HasElement titleComponent) {
        this.titleComponent = titleComponent;
    }

    @FunctionalInterface
    public interface NavigationElementInfoProducer extends Function<Class<? extends HasElement>, NavigationElementInfo> {
    }

}
