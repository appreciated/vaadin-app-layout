package com.github.appreciated.example.search;

import com.github.appreciated.app.layout.addons.search.SearchButton;
import com.github.appreciated.app.layout.addons.search.overlay.SearchOverlayButton;
import com.github.appreciated.app.layout.addons.search.overlay.SearchOverlayButtonBuilder;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.applayout.LeftLayouts;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftClickableItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftHeaderItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.component.router.AppLayoutRouterLayout;
import com.github.appreciated.card.RippleClickableCard;
import com.github.appreciated.card.content.Item;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.function.SerializablePredicate;

import java.util.Arrays;

import static com.github.appreciated.app.layout.entity.Section.FOOTER;
import static com.github.appreciated.app.layout.entity.Section.HEADER;

/**
 * The main view contains a button and a template element.
 */

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class MainAppLayout extends AppLayoutRouterLayout<LeftLayouts.LeftResponsive> {


    public MainAppLayout() {
        // An Search overlay
        SearchOverlayButton<TestSearchResult, SerializablePredicate<TestSearchResult>> searchOverlayButton = initSearchOverlayButton();
        // An AppBar overlay with a search field
        SearchButton searchButton = new SearchButton().withValueChangeListener(event -> {
            /* React manually to user inputs */
        });

        init(AppLayoutBuilder.get(LeftLayouts.LeftResponsive.class)
                .withTitle("App Layout")
                .withAppBar(AppBarBuilder.get()
                        .add(searchButton)
                        .add(searchOverlayButton)
                        .build())
                .withAppMenu(LeftAppMenuBuilder.get()
                        .addToSection(HEADER,
                                new LeftHeaderItem("Menu-Header", "Version 4.0.0", "/frontend/images/logo.png"),
                                new LeftClickableItem("Clickable Entry", VaadinIcon.COG.create(), event -> Notification.show("onClick ..."))
                        )
                        .add(new LeftNavigationItem("Home", VaadinIcon.HOME.create(), View1.class))
                        .addToSection(FOOTER,
                                new LeftClickableItem("Clickable Entry", VaadinIcon.COG.create(), clickEvent -> Notification.show("onClick ..."))
                        )
                        .build())
                .build());
    }

    private SearchOverlayButton<TestSearchResult, SerializablePredicate<TestSearchResult>> initSearchOverlayButton() {
        // The data provider that provides the entities for the search
        ListDataProvider<TestSearchResult> listDataProvider = new ListDataProvider<>(Arrays.asList(
                new TestSearchResult("Header1", "Description1"),
                new TestSearchResult("Header2", "Description2"),
                new TestSearchResult("Header3", "Description3"),
                new TestSearchResult("Header4", "Description4"),
                new TestSearchResult("Header5", "Description5"),
                new TestSearchResult("Header6", "Description6"),
                new TestSearchResult("Header7", "Description7"),
                new TestSearchResult("Header8", "Description8"),
                new TestSearchResult("Header9", "Description9"),
                new TestSearchResult("Header10", "Description10")
        ));

        return new SearchOverlayButtonBuilder<TestSearchResult, SerializablePredicate<TestSearchResult>>()
                // add the data provider
                .withDataProvider(listDataProvider)
                // Set the query that is executed to filter the Entities above
                .withQueryProvider(s -> new Query<>(testEntity -> !s.equals("") && testEntity.getHeader().startsWith(s)))
                // Set the producer that produces Components to be shown as search result
                .withDataViewProvider(queryResult -> {
                    RippleClickableCard card = new RippleClickableCard(new Item(queryResult.getHeader(), queryResult.getDescription()));
                    card.setWidthFull();
                    card.setBackground("var(--lumo-base-color)");
                    return card;
                })
                // A Listener to react if a search result was clicked
                .withQueryResultListener(testSearchResult -> Notification.show(testSearchResult.getHeader()))
                .build();
    }
}
