package com.github.appreciated.app.layout.component;

import com.github.appreciated.app.layout.builder.elements.SubmenuNavigationElement;
import com.github.appreciated.app.layout.component.button.NavigationButton;
import com.vaadin.server.Resource;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import static com.github.appreciated.app.layout.Styles.*;

/**
 * The component which is used for submenu elements. On click it toggles a css class which causes it to grow / shrink
 */
public class ExpandingMenuContainer
		extends VerticalLayout
		implements SubmenuNavigationElement.SubmenuComponent
{

	private final VerticalLayout submenuWrapper;

	public ExpandingMenuContainer( String sectionName, Resource icon )
	{
		addStyleName( EXPANDING_MENU_CONTAINER_PRIMARY_STYLE );
		setMargin( false );
		NavigationButton expandMenuButton = new NavigationButton( sectionName, icon );
		expandMenuButton.addClickListener( clickEvent -> {
			if ( getStyleName().contains( EXPANDING_MENU_CONTAINER_OPEN ) )
			{
				removeStyleName( EXPANDING_MENU_CONTAINER_OPEN );
			}
			else
			{
				addStyleName( EXPANDING_MENU_CONTAINER_OPEN );
			}
		} );
		HorizontalLayout buttonWrapper = new HorizontalLayout( expandMenuButton );
		buttonWrapper.addStyleName( EXPANDING_MENU_CONTAINER_BUTTON );
		buttonWrapper.setSpacing( false );
		buttonWrapper.setWidth( 100, Unit.PERCENTAGE );
		super.addComponent( buttonWrapper );

		submenuWrapper = new VerticalLayout();
		submenuWrapper.addStyleName( EXPANDING_MENU_SUBMENU_CONTAINER );
		submenuWrapper.setMargin( false );
		submenuWrapper.setWidth( 100, Unit.PERCENTAGE );

		super.addComponent( buttonWrapper );
		super.addComponent( submenuWrapper );
	}

	@Override
	public void addComponent( Component c )
	{
		submenuWrapper.addComponent( c );
	}

	@Override
	public void close()
	{
		if ( getStyleName().contains( EXPANDING_MENU_CONTAINER_OPEN ) )
		{
			removeStyleName( EXPANDING_MENU_CONTAINER_OPEN );
		}
	}
}
