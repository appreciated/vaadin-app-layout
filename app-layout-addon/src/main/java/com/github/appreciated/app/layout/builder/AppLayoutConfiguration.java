package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutElementBase;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.design.AppLayoutDesign;
import com.github.appreciated.app.layout.builder.elements.AbstractNavigationElement;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.elements.SectionNavigationElement;
import com.github.appreciated.app.layout.builder.elements.SubmenuNavigationElement;
import com.github.appreciated.app.layout.builder.entities.NavigationElementInfo;
import com.github.appreciated.app.layout.builder.factories.left.DefaultLeftClickableNavigationElementFactory;
import com.github.appreciated.app.layout.builder.factories.left.DefaultLeftNavigationBadgeElementComponentFactory;
import com.github.appreciated.app.layout.builder.factories.left.DefaultLeftSectionElementComponentFactory;
import com.github.appreciated.app.layout.builder.factories.left.DefaultLeftSubmenuNavigationElementFactory;
import com.github.appreciated.app.layout.builder.factories.top.DefaultTopSubmenuNavigationElementFactory;
import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;
import com.github.appreciated.app.layout.builder.interfaces.Factory;
import com.github.appreciated.app.layout.builder.interfaces.HasCaptionInterceptor;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.github.appreciated.app.layout.component.AppLayoutElement;
import com.github.appreciated.app.layout.session.AppLayoutSessionHelper;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Since the class AppLayoutBuilder was grew so large I decided to outsource the logic to configure an AppLayout instance into this class.
 * <p>
 * For every "Factory" you find in the class some information:
 * The following factories allow the user to exchange any {@link HasElement} that will be added to the
 * {@link AppLayoutElementBase} instance for any {@link Behaviour} or custom implementation
 * To do this you will have to replace the
 */
public class AppLayoutConfiguration {

    private Behaviour variant = Behaviour.LEFT;
    private List<AbstractNavigationElement> navigationElements = new ArrayList<>();
    private List<HasElement> appBarElements = new ArrayList<>();

    private AppLayoutDesign design = AppLayoutDesign.DEFAULT;
    private String title;
    private NavigatorNavigationElement defaultNavigationElement;
    private ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> navigationElementProvider;
    private DefaultLeftClickableNavigationElementFactory customElementProvider;
    private ComponentFactory<HasElement, SectionNavigationElement> sectionProvider;
    private ComponentFactory<SubmenuNavigationElement.SubmenuElement, SubmenuNavigationElement> submenuProvider;

    private NavigationElementInfoProducer navigationElementInfoProvider = null;
    private List<AbstractNavigationElement> footerElements = new ArrayList<>();
    private List<AbstractNavigationElement> headerElements = new ArrayList<>();
    private List<NavigatorNavigationElement> navigatorElements = new ArrayList<>();
    private Component appBarIconComponent;

    private AppLayoutElementBase instance;

    private Factory<String, String> viewNameInterceptor = null;
    private Factory<String, String> captionInterceptor;

    private boolean isCDI = false;
    private boolean isScrollToTopOnNavigate = true;
    private boolean isCloseSubmenusOnNavigate = true;
    private boolean isNavigatorEnabled = true;
    private HasElement titleComponent;

    public AppLayoutConfiguration(AppLayoutElementBase instance) {
        this.instance = instance;
    }

    /**
     * FIXME this configuration should only hold configuration => Single responsibility principle
     * This terminating build method is part of the builder and not of the configuration.
     */
    public AppLayoutElement build() {

        // this method has a lot of magic. add some inline comments describing why are you doing what.
        if (navigationElementProvider == null) {
            if (variant.isTop())
                navigationElementProvider = new DefaultLeftNavigationBadgeElementComponentFactory();
            else
                navigationElementProvider = new DefaultLeftNavigationBadgeElementComponentFactory();
        }
        if (customElementProvider == null) {
            if (variant.isTop())
                customElementProvider = new DefaultLeftClickableNavigationElementFactory();
            else
                customElementProvider = new DefaultLeftClickableNavigationElementFactory();
        }
        if (sectionProvider == null) {
            if (variant.isTop())
                sectionProvider = new DefaultLeftSectionElementComponentFactory();
            else
                sectionProvider = new DefaultLeftSectionElementComponentFactory();
        }
        if (submenuProvider == null) {
            if (variant.isTop())
                submenuProvider = new DefaultTopSubmenuNavigationElementFactory();
            else
                submenuProvider = new DefaultLeftSubmenuNavigationElementFactory();
        }

        AppLayoutSessionHelper.setActiveVariant(variant);
        if (titleComponent == null) {
            instance.setTitle(title);
        } else {
            instance.setTitleElement(titleComponent);
        }

        if (!isCDI && defaultNavigationElement == null) {
            defaultNavigationElement = navigationElements.stream()
                    .filter(element -> element instanceof NavigatorNavigationElement)
                    .map(element -> ((NavigatorNavigationElement) element)).findFirst().orElse(null);
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

        if (!isCDI && defaultNavigationElement != null) {
            defaultNavigationElement.addViewToNavigator();
        } else if (isCDI && defaultNavigationElement != null) {
            System.err.println("WARNING - AppLayout - You are using isCDI but try to set the DefaultNavigationElement this will have no effect");
        }

        return (AppLayoutElement) instance;
    }

    private Optional<NavigatorNavigationElement> findNextNavigationElement(String viewName) {
        if (viewName.equals("")) {
            return navigatorElements.stream()
                    .filter(element -> (defaultNavigationElement != null && element.getViewClassName().equals(defaultNavigationElement.getViewClassName())) || element.getViewName().equals(""))
                    .findFirst();
        }
        return navigatorElements.stream()
                .filter(element -> element instanceof NavigatorNavigationElement)
                .filter(element -> element.getViewName().equals(viewName))
                .findFirst();
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
            nElement.setCDI(isCDI);
            nElement.setNavigationElementInfoProvider(navigationElementInfoProvider);
            if ((isCDI == false && nElement.getViewClassName() == defaultNavigationElement.getViewClassName()) ||
                    (isCDI == true && nElement.getViewName().equals(""))) {
                AppLayoutSessionHelper.updateActiveElementSessionData(nElement);
            }
            nElement.setViewNameInterceptor(viewNameInterceptor);
            nElement.addViewToNavigator();
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
            System.err.println("Section Can't be null in Element:"+element.getComponent().getElement()+"\n ->"+ Arrays.toString(e.getStackTrace()));
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

    public List<HasElement> getAppBarElements() {
        return appBarElements;
    }

    public void setDefaultNavigationElement(NavigatorNavigationElement defaultNavigationElement) {
        this.defaultNavigationElement = defaultNavigationElement;
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

    public void setViewNameInterceptor(Factory<String, String> viewNameInterceptor) {
        this.viewNameInterceptor = viewNameInterceptor;
    }

    public void setCaptionInterceptor(Factory<String, String> captionInterceptor) {
        this.captionInterceptor = captionInterceptor;
    }

    public void setCDI(boolean CDI) {
        this.isCDI = CDI;
    }

    public void setNavigatorEnabled(boolean navigatorEnabled) {
        this.isNavigatorEnabled = navigatorEnabled;
    }

    public void setScrollToTopOnNavigate(boolean scrollToTopOnNavigate) {
        this.isScrollToTopOnNavigate = scrollToTopOnNavigate;
    }

    public void setCloseSubmenusOnNavigate(boolean closeSubmenusOnNavigate) {
        this.isCloseSubmenusOnNavigate = closeSubmenusOnNavigate;
    }

    private boolean beforeViewChange(String viewName) {
        AppLayoutSessionHelper.removeStyleFromCurrentlyActiveNavigationElement();
        Optional<NavigatorNavigationElement> result = findNextNavigationElement(viewName);
        if (result.isPresent()) {
            result.ifPresent(element -> {
                AppLayoutSessionHelper.setActiveNavigationElement(element);
                if (isCloseSubmenusOnNavigate) {
                    navigationElements.stream()
                            .filter(nelement -> nelement instanceof SubmenuNavigationElement)
                            .filter(nelement -> nelement != element)
                            .map(nElement -> (SubmenuNavigationElement) nElement)
                            .forEach(submenuNavigationElement -> submenuNavigationElement.closeEventually(element));
                }
                if (isScrollToTopOnNavigate) {
                    // instance.getContentHolder().setScrollTop(0);
                }
            });
        }
        return true;
    }

    public void setTitleComponent(HasElement titleComponent) {
        this.titleComponent = titleComponent;
    }

    @FunctionalInterface
    public interface NavigationElementInfoProducer extends Function<Class<? extends HasElement>, NavigationElementInfo> {
    }


}
