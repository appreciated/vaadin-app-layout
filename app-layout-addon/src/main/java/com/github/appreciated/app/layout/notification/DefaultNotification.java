package com.github.appreciated.app.layout.notification;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import org.ocpsoft.prettytime.PrettyTime;

import com.vaadin.server.Resource;

/**
 * A concrete implementation of {@link Notification}.
 */
public class DefaultNotification
		implements Notification
{

	private String title;
	private String description;
	private Resource image;
	private Priority priority = Priority.MEDIUM;
	private boolean isSticky = true;
	private boolean isRead = false;
	private LocalDateTime creationTime = LocalDateTime.now();

	/**
	 * Creates a new {@link DefaultNotification} with the given arguments.
	 * 
	 * @param title
	 *        The title for this notification. Must not be <code>null</code>.
	 * @param description
	 *        The description for this notification. Must not be <code>null</code>.
	 * @throws NullPointerException
	 *         in case of any argument is <code>null</code>.
	 */
	public DefaultNotification( String title, String description )
	{
		Objects.requireNonNull( title, "The title must not be null." );
		Objects.requireNonNull( description, "The description must not be null." );

		this.title = title;
		this.description = description;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle( String title )
	{
		Objects.requireNonNull( title, "The title must not be null." );
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		Objects.requireNonNull( description, "The description must not be null." );
		this.description = description;
	}

	public Resource getImage()
	{
		return image;
	}

	public void setImage( Resource image )
	{
		this.image = image;
	}

	public LocalDateTime getCreationTime()
	{
		return creationTime;
	}

	@Override
	public void setCreationTime( LocalDateTime creationTime )
	{
		Objects.requireNonNull( creationTime, "The creationTime must not be null." );
		this.creationTime = creationTime;
	}

	public String getStyle()
	{

		if ( priority == null )
		{
			return "";
		}

		return priority.getStyle();
	}

	public boolean isRead()
	{
		return isRead;
	}

	@Override
	public Priority getPriority()
	{
		return priority;
	}

	public void setRead( boolean isRead )
	{
		this.isRead = isRead;
	}

	public boolean isSticky()
	{
		return isSticky;
	}

	public void setSticky( boolean isSticky )
	{
		this.isSticky = isSticky;
	}

	public String getTimeAgo()
	{
		return new PrettyTime().format( Date.from( creationTime.atZone( ZoneId.systemDefault() ).toInstant() ) );
	}

	@Override
	public int compareTo( Notification otherNotification )
	{
		if ( otherNotification == this )
		{
			return 0;
		}

		if ( this.getPriority() != otherNotification.getPriority() )
		{
			return this.getPriority().getValue().compareTo( otherNotification.getPriority().getValue() );
		}
		else
		{
			return this.getCreationTime().compareTo( otherNotification.getCreationTime() );
		}
	}

}
