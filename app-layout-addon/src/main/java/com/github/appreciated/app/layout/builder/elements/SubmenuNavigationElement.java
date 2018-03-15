package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.behaviour.Position;
import com.github.appreciated.app.layout.builder.interfaces.Factory;
import com.github.appreciated.app.layout.builder.interfaces.HasCaptionInterceptor;
import com.vaadin.server.Resource;
import com.vaadin.ui.Component;

import java.util.List;

/**
 * A class that contains only the data that is needed to create an instance of a {@link SubmenuComponent}.
 * This navigation element can contain tree like structures.
 */
public class SubmenuNavigationElement extends AbstractNavigationElement<SubmenuNavigationElement.SubmenuComponent, SubmenuNavigationElement> implements HasCaptionInterceptor {

    private final String title;
    private final Resource icon;
    private List<AbstractNavigationElement> submenuElements;
    private Factory<String, String> captionInterceptor;

    public SubmenuNavigationElement(String title, Resource icon, List<AbstractNavigationElement> submenuElements) {
        this.title = title;
        this.icon = icon;
        this.submenuElements = submenuElements;
    }

    public List<AbstractNavigationElement> getSubmenuElements() {
        return submenuElements;
    }

    @Override
    SubmenuNavigationElement getInfo() {
        return this;
    }

    public Resource getIcon() {
        return icon;
    }

    public String getTitle() {
        if (captionInterceptor == null) {
            return title;
        } else {
            return captionInterceptor.get(title);
        }
    }

    @Override
    public void setProvider(AppLayoutComponent provider) {
        setProvider(provider, Position.DRAWER);
    }

    @Override
    public void setProvider(AppLayoutComponent provider, Position position) {
        if (position == Position.TOP) {
            setProvider(provider.getTopSubmenuElementProvider());
        } else {
            setProvider(provider.getDrawerSubmenuElementProvider());
        }
        submenuElements.forEach(element -> element.setProvider(provider, position));
    }

    public boolean requiresNavigator() {
        return submenuElements.stream().anyMatch(element -> element instanceof NavigatorNavigationElement ||
                (element instanceof SubmenuNavigationElement && ((SubmenuNavigationElement) element).requiresNavigator()));
    }

    public void setCaptionInterceptor(Factory<String, String> captionInterceptor) {
        this.captionInterceptor = captionInterceptor;
    }

    public void closeEventually(NavigatorNavigationElement element) {
        if (!hasChild(element)) {
            getComponent().close();
            submenuElements.stream()
                    .filter(submenuElement -> submenuElement instanceof SubmenuNavigationElement)
                    .map(submenuElement -> (SubmenuNavigationElement) submenuElement)
                    .forEach(submenuNavigationElement -> submenuNavigationElement.getComponent().close());
        }
    }

    public boolean hasChild(NavigatorNavigationElement child) {
        boolean hasChild = submenuElements.stream()
                .map(element -> element == child)
                .reduce((b1, b2) -> b1 || b2)
                .orElse(false);
        if (hasChild == false)
            hasChild = submenuElements.stream()
                    .filter(element -> element instanceof SubmenuNavigationElement)
                    .map(element -> (SubmenuNavigationElement) element)
                    .map(element -> element.hasChild(child)).reduce((b1, b2) -> b1 || b2)
                    .orElse(false);
        return hasChild;
    }

    public interface SubmenuComponent extends Component {
        default void close() {
        }
    }
}
