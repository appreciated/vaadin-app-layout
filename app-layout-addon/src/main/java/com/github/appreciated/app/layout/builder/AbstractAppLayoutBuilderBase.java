package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutElementBase;
import com.github.appreciated.app.layout.builder.design.AppLayoutDesign;
import com.github.appreciated.app.layout.builder.elements.*;
import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;
import com.github.appreciated.app.layout.builder.interfaces.Factory;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.github.appreciated.app.layout.component.AppLayoutElement;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;

import java.util.Arrays;
import java.util.stream.Collectors;

public class AbstractAppLayoutBuilderBase<T extends AbstractAppLayoutBuilderBase> {

    protected AppLayoutConfiguration config;

    protected AbstractAppLayoutBuilderBase(AppLayoutElementBase component) {
        config = new AppLayoutConfiguration(component);
    }

    /**
     * Set the title which the layout is supposed to have after building
     *
     * @param title
     * @return
     */
    public T withTitle(String title) {
        config.setTitle(title);
        return (T) this;
    }

    /**
     * Replaces the title component with another component
     *
     * @param component
     * @return
     */
    public T withTitle(Component component) {
        config.setTitleComponent(component);
        return (T) this;
    }

    /**
     * Sets the an interceptor which will be applied to each {@link AbstractNavigationElement}. The can be f.e. used to replace
     * the I18N key with a language specific value on each {@link AbstractNavigationElement}.
     *
     * @param interceptor The interceptor which contains the procedure how the new caption is to be determined
     * @return
     */

    public T withCaptionInterceptor(Factory<String, String> interceptor) {
        config.setCaptionInterceptor(interceptor);
        return (T) this;
    }

    /**
     * Set the Design which the build menu is supposed to have
     *
     * @param design
     * @return
     */
    public T withDesign(AppLayoutDesign design) {
        config.setDesign(design);
        return (T) this;
    }

    /**
     * Sets the Component provider that is being used to generate navigation elements in the drawer.
     *
     * @param provider
     * @return
     */
    public T withNavigationElementProvider(ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> provider) {
        config.setNavigationElementProvider(provider);
        return (T) this;
    }

    /**
     * Sets the Component provider that is being used to generate section elements in the drawer.
     *
     * @param provider
     * @return
     */
    public T withSectionElementProvider(ComponentFactory<HasElement, SectionNavigationElement> provider) {
        config.setSectionProvider(provider);
        return (T) this;
    }

    /**
     * Sets the Component provider that is being used to generate submenu navigation elements in the drawer.
     *
     * @param provider
     * @return
     */
    public T withSubmenuElementProvider(ComponentFactory<SubmenuNavigationElement.SubmenuElement, SubmenuNavigationElement> provider) {
        config.setSubmenuProvider(provider);
        return (T) this;
    }

    /**
     * Determines that if the User navigates to another the panel which holds the content will be scrolled to the top
     *
     * @param scrollToTopOnNavigate
     * @return
     */
    public T withScrollToTopOnNavigate(boolean scrollToTopOnNavigate) {
        config.setScrollToTopOnNavigate(scrollToTopOnNavigate);
        return (T) this;
    }

    /**
     * Determines that if the User navigates to another View submenus will be closed if the View is not inside it
     *
     * @param closeSubmenusOnNavigate
     * @return
     */
    public T withCloseSubmenusOnNavigate(boolean closeSubmenusOnNavigate) {
        config.setCloseSubmenusOnNavigate(closeSubmenusOnNavigate);
        return (T) this;
    }

    /**
     * Appends a component to the bottom of the menu at the DEFAULT position
     *
     * @param element
     * @return
     */
    public T add(Component element) {
        return add(element, Section.DEFAULT);
    }

    /**
     * Appends a component to the menu to a specific section
     *
     * @param element
     * @param section
     * @return
     */
    public T add(Component element, Section section) {
        addToPosition(new ComponentNavigationElement(element), section);
        return (T) this;
    }

    /**
     * Appends a menu element which has a click element with a click listener to the DEFAULT position
     *
     * @param caption
     * @param icon
     * @param listener
     * @return
     */

    public T addClickable(String caption, Icon icon, ComponentEventListener<ClickEvent<Button>> listener) {
        return addClickable(caption, icon, listener, Section.DEFAULT);
    }

    /**
     * Appends a menu element which has a click element with a click listener at a specific section
     *
     * @param caption
     * @param icon
     * @param listener
     * @param section
     * @return
     */
    public T addClickable(String caption, Icon icon, ComponentEventListener<ClickEvent<Button>> listener, Section section) {
        addToPosition(new ClickableNavigationElement(caption, icon, listener), section);
        return (T) this;
    }

    /**
     * Appends one or multiple components to the menu to the DEFAULT position
     *
     * @param elements
     * @return
     */
    public T add(Component... elements) {
        config.getNavigationElements().addAll(Arrays.stream(elements).map(component -> new ComponentNavigationElement(component)).collect(Collectors.toList()));
        return (T) this;
    }

    /**
     * Appends a section element with a caption to the menu at the DEFAULT position
     *
     * @param caption
     * @return
     */
    public T addSection(String caption) {
        config.getNavigationElements().add(new SectionNavigationElement(caption));
        return (T) this;
    }

    /**
     * Appends a menu element which has a click element with a click listener at the DEFAULT position
     *
     * @param caption
     * @param listener
     * @return
     */
    public T addClickable(String caption, ComponentEventListener<ClickEvent<Button>> listener) {
        return addClickable(caption, null, listener);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param element
     * @return
     */
    public T add(AbstractNavigationElement element) {
        return add(element, Section.DEFAULT);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT section
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param element
     * @param section
     * @return
     */
    public T add(AbstractNavigationElement element, Section section) {
        config.add(element, section);
        return (T) this;
    }

    /**
     * Appends a Component to the 'app bar' (top bar)
     *
     * @param element
     * @return
     */
    public T addToAppBar(HasElement element) {
        config.getAppBarElements().add(element);
        return (T) this;
    }

    /**
     * Appends one or multiple Component(s) to the 'app bar' (top bar)
     *
     * @param element
     * @return
     */
    public T addToAppBar(HasElement... element) {
        config.getAppBarElements().addAll(Arrays.asList(element));
        return (T) this;
    }

    /**
     * Sets the Component which is shown directly behind the title label in the 'app bar' (top bar)
     *
     * @param resourceButton
     * @return
     */
    public T withAppBarIconComponent(Component resourceButton) {
        config.setAppBarIconComponent(resourceButton);
        return (T) this;
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element to a specific section
     *
     * @param element
     * @param section
     * @return
     */
    protected void addToPosition(AbstractNavigationElement element, Section section) {
        config.addToPosition(element, section);
    }

    /**
     * Build the layout and returns an instance of an AppLayout which also is a Component
     *
     * @return
     */
    public AppLayoutElement build() {
        return config.build();
    }

}
