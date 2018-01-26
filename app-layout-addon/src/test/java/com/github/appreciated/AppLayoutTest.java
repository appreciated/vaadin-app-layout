package com.github.appreciated;

import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayout;
import com.github.appreciated.app.layout.builder.AppLayoutConfiguration;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.providers.DefaultNavigationElementInfoProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import junit.framework.Assert;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.vaadin.viritin.button.MButton;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UI.class)
public class AppLayoutTest {

  @Mock(serializable = true)
  private UI ui;

  @Mock(serializable = true)
  private VaadinSession session;

  @Before
  public void setUp() {
    PowerMockito.mockStatic(UI.class);
    MockitoAnnotations.initMocks(this);
    Mockito.when(UI.getCurrent()).thenReturn(ui);
    Mockito.when(ui.getSession()).thenReturn(session);
  }

  @Test
  public void thisAlwaysPasses() {
    Assert.assertEquals(true, true);
  }

  @Test
  public void whenSerialized() {
    DefaultBadgeHolder holder = new DefaultBadgeHolder();
    holder.setCount(200);

    SerializationUtils.serialize(AppLayout.getCDIBuilder(Behaviour.LEFT_RESPONSIVE)
        .withNavigator(components -> mock(Navigator.class, withSettings().serializable()))
        .withNavigationElementInfoProvider(new DefaultNavigationElementInfoProvider())
        .withTitle("Quotation")
        .addToAppBar(new MButton()
                        .withIcon(VaadinIcons.COG)
                        .withStyleName(ValoTheme.BUTTON_LINK)
                        .withListener((Button.ClickListener) e -> Notification.show("test")))
        .add(holder, TestView.class)
        .build());
  }

}
