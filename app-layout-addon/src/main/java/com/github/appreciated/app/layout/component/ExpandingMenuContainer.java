package com.github.appreciated.app.layout.component;

import com.github.appreciated.app.layout.builder.elements.SubmenuNavigationElement;
import com.github.appreciated.app.layout.helper.LayoutHelper;
import com.github.appreciated.app.layout.webcomponents.ironcollapsebutton.IronCollapseButton;
import com.github.appreciated.app.layout.webcomponents.paperdrawer.PaperDrawerIconItem;
import com.github.appreciated.app.layout.webcomponents.paperdrawer.PaperDrawerItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Element;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.appreciated.app.layout.builder.design.Styles.EXPANDING_MENU_SUBMENU_CONTAINER;

/**
 * The component which is used for submenu webcomponents. On click it toggles a css class which causes it to grow / shrink
 */
public class ExpandingMenuContainer extends IronCollapseButton implements SubmenuNavigationElement.SubmenuElement {

    private final VerticalLayout submenuWrapper;
    private List<Element> elements;
    private Element toggle;

    public ExpandingMenuContainer(String sectionName, Icon icon) {
        super();
        getElement().getStyle().set("width", "100%");
        //.set("box-sizing", "border-box")
        //.set("padding-right", "10px");
        getElement().getShadowRoot().ifPresent(shadowRoot -> elements = getElement().getChildren().collect(Collectors.toList()));
        submenuWrapper = new VerticalLayout();
        submenuWrapper.setMargin(false);
        submenuWrapper.setPadding(false);
        submenuWrapper.getElement().getClassList().add(EXPANDING_MENU_SUBMENU_CONTAINER);
        submenuWrapper.setWidth("100%");
        LayoutHelper.disableFlex(submenuWrapper);
        if (icon != null) {
            toggle = new PaperDrawerIconItem(sectionName, icon.getElement().getAttribute("icon")).getElement();
            toggle.getStyle().set("height", "48px");
            setButton(toggle);
        } else {
            toggle = new PaperDrawerItem(sectionName).getElement();
            toggle.getStyle().set("height", "48px");
            setButton(toggle);
        }
        setContent(submenuWrapper.getElement());
    }

    public void add(Component... components) {
        submenuWrapper.add(components);
    }

}
