package com.github.appreciated.app.layout;

import com.github.appreciated.app.layout.addons.search.SearchButton;
import com.github.appreciated.app.layout.navigation.TestEntitiy;
import com.github.appreciated.card.RippleClickableCard;
import com.github.appreciated.card.content.Item;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.router.Route;

import java.util.Arrays;

@Route(value = "search")
@Push
public class SearchViewTest extends HorizontalLayout {
    private final SearchButton<TestEntitiy> button;
    private final ListDataProvider<TestEntitiy> dataProvider;

    public SearchViewTest() {
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        button = new SearchButton<>();
        dataProvider = new ListDataProvider<>(Arrays.asList(
                new TestEntitiy("Header1", "Description1"),
                new TestEntitiy("Header2", "Description2"),
                new TestEntitiy("Header3", "Description3")
        ));
        button.setDataProvider(dataProvider, s -> new Query<>(testEntity -> !s.equals("") && testEntity.getHeader().startsWith(s)));
        button.setDataViewProvider(result -> result.map(o -> {
            RippleClickableCard card = new RippleClickableCard(new Item(o.getHeader(), o.getDescription()));
            card.setWidthFull();
            card.setBackground("var(--lumo-base-color)");
            return card;
        }));
        add(button);
    }
}
