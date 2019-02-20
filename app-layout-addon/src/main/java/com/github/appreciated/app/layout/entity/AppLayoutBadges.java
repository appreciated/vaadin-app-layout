package com.github.appreciated.app.layout.entity;

import com.vaadin.flow.component.UI;

import java.util.Optional;

public class AppLayoutBadges {
    public static Optional<DefaultBadgeHolder> getBadge(String identifier) {
        BadgesHolder badges = getBadgesHolder();
        if (badges.containsKey(identifier)) {
            return Optional.ofNullable(badges.get(identifier));
        }
        return Optional.empty();
    }

    public static void addBadgeWithIdentifier(DefaultBadgeHolder holder, String identifier) {
        BadgesHolder badges = getBadgesHolder();

        UI.getCurrent().getSession().setAttribute(DefaultBadgeHolder.class, holder);
    }

    public static BadgesHolder getBadgesHolder() {
        BadgesHolder badges = UI.getCurrent().getSession().getAttribute(BadgesHolder.class);
        if (badges == null) {
            badges = new BadgesHolder();
            UI.getCurrent().getSession().setAttribute(BadgesHolder.class, badges);
        }
        return badges;
    }
}
