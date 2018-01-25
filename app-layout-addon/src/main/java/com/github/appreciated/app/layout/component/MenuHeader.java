package com.github.appreciated.app.layout.component;

import com.vaadin.server.Resource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import static com.github.appreciated.app.layout.Styles.*;

/**
 * A simple container component which may contain an image and two labels
 * concerned component won't be added to its parent
 */
public class MenuHeader
		extends VerticalLayout
{
	//FIXME You are using constructor chaining here. Thats a good practice ;)
	// Add missing JavaDoc
	public MenuHeader( Resource resource )
	{
		this( null, resource );
	}

	public MenuHeader( String title, Resource resource )
	{
		this( title, null, resource );
	}

	public MenuHeader( String title, String subtitle, Resource resource )
	{
		this( title, subtitle, resource != null ? new RoundImage( resource ) : null );
	}

	public MenuHeader( String title, String subtitle, Image image )
	{
		Label name = new Label( title );
		name.addStyleName( APP_LAYOUT_MENU_HEADER_TITLE );
		
		Label description = new Label( subtitle );
		description.addStyleName( APP_LAYOUT_MENU_HEADER_TITLE );
		description.addStyleName( ValoTheme.LABEL_SMALL );
		
		addStyleName( APP_LAYOUT_MENU_BAR_ELEMENT );
		addStyleName( APP_LAYOUT_MENU_HEADER_ELEMENT );
		
		setMargin( false ); //FIXME you do not need to set margin twice here and later with MarginInfo. remove this call
		setSpacing( false );
		setMargin( new MarginInfo( true, false, false, false ) );
		
		// FIXME I added curly braces here. This is not enforced by the compiler but is good practice and makes code more readable
		if ( image != null )
		{
			addComponent( image );
		}
		
		// FIXME These ifs should be reworked. They are not bulletproof because you ask for n object not to be null but adding another object without a null check
		// you should create an instance of Label only if the corresponding text is not null and add it afterwards.
		// BTW: not creating an instance if not required is better for memory usage and the GarbageCollector would be very happy about that ;)
		if ( title != null )
		{
			addComponent( name );
		}
		if ( subtitle != null )
		{
			addComponent( description );
		}
	}

}