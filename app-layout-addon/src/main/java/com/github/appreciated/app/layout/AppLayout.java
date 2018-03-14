package com.github.appreciated.app.layout;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.CDIAppLayoutBuilder;
import com.github.appreciated.app.layout.builder.NavigatorAppLayoutBuilder;
import com.github.appreciated.app.layout.builder.NoNavigatorAppLayoutBuilder;
import com.github.appreciated.app.layout.builder.steps.CDIBuilderNavigatorPreamble;

public class AppLayout {

    /**
     * This is confusing. Client code should call AppLayout.getCDIBuilder(...) to build a new instance of AppLayoutComponent but must
     * pass an existing component in this method? Where does this instance come from? If an instance can be created outside, the Builder
     * makes no sense.
     *
     * From my point of view only the methods makes sense which gets an instance of Behavior as argument.
     *
     * Or explain the methods via JavaDoc. IMPORTANT: In general this class should explained very well and carefully because it's the
     * bootstrap class for client code. e.g. see JPA bootstrap class: javax.persistence.Persistence
     *
     * Therefore I would move that class to an not so deep package like: com.github.appreciated.app.layout
     *
     * Package naming. I think AppLayout is this component in total. I would not devide the name in the package like:
     *
     *
     *
     * It is also a good idea to have all classes you create instances from with this builder in the same package and the constructors of
     * that classes are package-private. So you force client to use your builder and nobody can create an instance of it's own.
     *
     *
     */

    /**
     * Returns a builder that allows you to fill you own custom AppLayoutComponent instance
     *
     * @param layout the custom component you want to be configured by the Builder
     * @return a {@link CDIBuilderNavigatorPreamble} this is required since there are a few methods that are required to be called when using a {@link CDIAppLayoutBuilder}
     */
    public static CDIBuilderNavigatorPreamble getCDIBuilder(AppLayoutComponent layout) {
        return new CDIBuilderNavigatorPreamble(CDIAppLayoutBuilder.get(layout));
    }

    /**
     * Returns a builder that allows you to fill a one of the predefined {@link Behaviour}
     *
     * @param behaviour the {@link Behaviour} you want to use
     * @return a {@link CDIBuilderNavigatorPreamble} this is required since there are a few methods that are required to be called when using a {@link CDIAppLayoutBuilder}
     */
    public static CDIBuilderNavigatorPreamble getCDIBuilder(Behaviour behaviour) {
        return new CDIBuilderNavigatorPreamble(CDIAppLayoutBuilder.get(behaviour.getInstance()));
    }

    /**
     * This builder should be used when using the {@link com.vaadin.navigator.Navigator} but not SpringCDI and when also providing your own {@link AppLayoutComponent} instance
     *
     * @param layout the custom component you want to be configured by the Builder
     * @return a {@link NavigatorAppLayoutBuilder} instance to build your {@link AppLayoutComponent}
     */
    public static NavigatorAppLayoutBuilder getDefaultBuilder(AppLayoutComponent layout) {
        return NavigatorAppLayoutBuilder.get(layout);
    }

    /**
     * This builder should be used when using the {@link com.vaadin.navigator.Navigator} but not SpringCDI and when also providing your own {@link AppLayoutComponent} instance
     *
     * @param behaviour the {@link Behaviour} you want to use
     * @return a {@link NavigatorAppLayoutBuilder} instance to build your {@link AppLayoutComponent}
     */
    public static NavigatorAppLayoutBuilder getDefaultBuilder(Behaviour behaviour) {
        return NavigatorAppLayoutBuilder.get(behaviour.getInstance());
    }

    /**
     * This builder should be used when using not {@link com.vaadin.navigator.Navigator} and also not SpringCDI and when also providing your own {@link AppLayoutComponent} instance
     *
     * @param layout the custom component you want to be configured by the Builder
     * @return a {@link NoNavigatorAppLayoutBuilder} instance to build your {@link AppLayoutComponent}
     */
    public static NoNavigatorAppLayoutBuilder getNoNavigatorBuilder(AppLayoutComponent layout) {
        return NoNavigatorAppLayoutBuilder.get(layout);
    }

    /**
     * This builder should be used when using not {@link com.vaadin.navigator.Navigator} and also not SpringCDI and when also providing your own {@link AppLayoutComponent} instance
     *
     * @param behaviour the {@link Behaviour} you want to use
     * @return a {@link NoNavigatorAppLayoutBuilder} instance to build your {@link AppLayoutComponent}
     */
    public static NoNavigatorAppLayoutBuilder getNoNavigatorBuilder(Behaviour behaviour) {
        return NoNavigatorAppLayoutBuilder.get(behaviour.getInstance());
    }
}
