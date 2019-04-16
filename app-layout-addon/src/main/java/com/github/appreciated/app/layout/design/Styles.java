package com.github.appreciated.app.layout.design;

/**
 * FIXME add JavaDoc, even for each constant.
 * PS: This is a constant class. So be sure nobody can create an instance of it by private Constructor.
 * <p>
 * The following constants are the css classes that are used to style the webcomponents for the specific Designs
 */
public class Styles {

    private Styles() {
        /*
         * Private constructor ensures that nobody creates an instance of this constant class.
         */
    }

    public static final String APP_LAYOUT = "app-layout";
    public static final String APP_BAR_NOTIFICATION_LIST = "app-layout-notification-list";
    public static final String APP_BAR_NOTIFICATION_PRIORITY_ERROR = "app-layout-notification-priority-high";
    public static final String APP_BAR_NOTIFICATION_PRIORITY_WARNING = "app-layout-notification-priority-high";
    public static final String APP_BAR_NOTIFICATION_PRIORITY_HIGH = "app-layout-notification-priority-high";
    public static final String APP_BAR_NOTIFICATION_PRIORITY_MEDIUM = "app-layout-notification-priority-medium";
    public static final String APP_BAR_NOTIFICATION_PRIORITY_LOW = "app-layout-notification-priority-low";
}
