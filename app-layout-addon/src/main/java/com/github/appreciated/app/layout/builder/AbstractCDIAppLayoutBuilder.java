package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutElementBase;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.icon.Icon;

public class AbstractCDIAppLayoutBuilder<T extends AbstractCDIAppLayoutBuilder> extends AbstractAppLayoutBuilderBase<T> {

    protected AbstractCDIAppLayoutBuilder(AppLayoutElementBase component) {
        super(component);
        config.setCDI(true);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param icon
     * @param element
     * @return
     */
    public T add(String caption, Icon icon, Class<? extends HasElement> element) {
        return add(caption, icon, null, element);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position which also has a badge 
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param icon
     * @param badgeHolder
     * @param element
     * @return
     */
    public T add(String caption, Icon icon, DefaultBadgeHolder badgeHolder, Class<? extends HasElement> element) {
        return add(caption, icon, badgeHolder, element, Section.DEFAULT);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param element
     * @return
     */
    public T add(String caption, Class<? extends HasElement> element) {
        return add(caption, null, element);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT section
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param icon
     * @param element
     * @param section
     * @return
     */
    public T add(String caption, Icon icon, Class<? extends HasElement> element, Section section) {
        return add(caption, icon, null, element, section);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param className
     * @return
     */
    public T add(Class<? extends HasElement> className) {
        return add(null, className, Section.DEFAULT);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param className
     * @return
     */
    public T add(DefaultBadgeHolder badgeHolder, Class<? extends HasElement> className) {
        return add(null, null, badgeHolder, className, Section.DEFAULT);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at a specific section
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param className
     * @return
     */
    public T add(Class<? extends HasElement> className, Section section) {
        return add(null, className, section);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param className
     * @return
     */
    public T add(Icon icon, Class<? extends HasElement> className) {
        return add(icon, className, Section.DEFAULT);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT section
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param className
     * @return
     */
    public T add(Icon icon, Class<? extends HasElement> className, Section section) {
        return add(new NavigatorNavigationElement(icon, className), section);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT section
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param icon
     * @param badgeHolder
     * @param element
     * @param section
     * @return
     */
    public T add(String caption, Icon icon, DefaultBadgeHolder badgeHolder, Class<? extends HasElement> element, Section section) {
        addToPosition(new NavigatorNavigationElement(caption, icon, badgeHolder, element), section);
        return (T) this;
    }

    /**
     * Sets the NavigationElementInfoProvider for the AppLayout.
     * The NavigationElementInfoProvider contains a algorithm to determine the icon and the caption for each View which
     * then later on is used to generate the menu entries. This allows to outsource the the information into annotations
     * generally.
     * Use in combination with {@link #add(Class)}
     *
     * @param provider The Factory which contains the procedure how the icon and the caption are to be determined
     * @return
     */
    public T withNavigationElementInfoProvider(AppLayoutConfiguration.NavigationElementInfoProducer provider) {
        this.config.setNavigationElementInfoProvider(provider);
        return (T) this;
    }

}