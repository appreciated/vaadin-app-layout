package com.github.appreciated.app.layout.entity;

import com.github.appreciated.app.layout.session.UIAttributes;
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

    public static BadgesHolder getBadgesHolder() {
        BadgesHolder badges = UIAttributes.get(BadgesHolder.class);
        if (badges == null) {
            badges = new BadgesHolder();
            UIAttributes.set(BadgesHolder.class, badges);
        }
        return badges;
    }

    public static void addBadgeWithIdentifier(DefaultBadgeHolder holder, String identifier) {
        BadgesHolder badges = getBadgesHolder();

        UIAttributes.set(DefaultBadgeHolder.class, holder);
    }
}
