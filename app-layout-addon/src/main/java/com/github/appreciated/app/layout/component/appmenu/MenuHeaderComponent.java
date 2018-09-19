package com.github.appreciated.app.layout.component.appmenu;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * A simple container component which may contain an image and two labels concerned component won't be added to its parent
 */
public class MenuHeaderComponent extends VerticalLayout {

	private RoundImage image;
	private Label titleLabel;
	private Label subtitleLabel;

	public MenuHeaderComponent( ){ }

	public MenuHeaderComponent( String title, String subtitle, String src ){
		if( src != null )
			image = new RoundImage( src, "56px", "56px" );

		if( title != null ){
			titleLabel = new Label( title );
			add( titleLabel );
		}

		if( subtitle != null ){
			subtitleLabel = new Label( subtitle );
			add( subtitleLabel );
		}

		init( );
	}

	public MenuHeaderComponent( Label titleLabel, Label subtitleLabel, RoundImage image ){
		this.image = image;
		this.titleLabel = titleLabel;
		this.subtitleLabel = subtitleLabel;

		init( );
	}

	private void init( ){
		setMargin( false );
		setId( "menu-header-wrapper" );

		if( image != null )
			add( image );

		if( titleLabel != null ){
			titleLabel.setId( "menu-header-title" );
			add( titleLabel );
		}

		if( subtitleLabel != null ){
			subtitleLabel.setId( "menu-header-subtitle" );
			add( subtitleLabel );
		}
	}

	public RoundImage getImage( ){
		return image;
	}

	public void setImage( RoundImage image ){
		this.image = image;
	}

	public Label getTitleLabel( ){
		return titleLabel;
	}

	public void setTitleLabel( Label titleLabel ){
		this.titleLabel = titleLabel;
	}

	public Label getSubtitleLabel( ){
		return subtitleLabel;
	}

	public void setSubtitleLabel( Label subtitleLabel ){
		this.subtitleLabel = subtitleLabel;
	}
}