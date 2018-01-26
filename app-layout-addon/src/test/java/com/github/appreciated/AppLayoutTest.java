package com.github.appreciated;

import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayout;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.providers.DefaultNavigationElementInfoProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
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

@RunWith(PowerMockRunner.class)
@PrepareForTest(UI.class)
public class AppLayoutTest {

  @Mock
  private UI ui;

  @Mock
  private VaadinSession session;

  @Mock
  private Navigator navigator;

  @Before
  public void setUp() {
    PowerMockito.mockStatic(UI.class);
    MockitoAnnotations.initMocks(this);
    Mockito.when(UI.getCurrent()).thenReturn(ui);
    Mockito.when(ui.getSession()).thenReturn(session);
    Mockito.when(ui.getNavigator()).thenReturn(navigator);
  }

  @Test
  public void thisAlwaysPasses() {
    Assert.assertEquals(true, true);
  }

  @Test
  public void whenSerialized() {
    SerializationUtils.serialize(AppLayout.getCDIBuilder(Behaviour.LEFT_RESPONSIVE)
        .withNavigator(components -> navigator)
        .withNavigationElementInfoProvider(new DefaultNavigationElementInfoProvider())
        .build());
  }

}
