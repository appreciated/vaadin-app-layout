package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.steps.CDIBuilderNavigatorPreamble;

public class AppLayout
{

	/**
	 * FIXME 
	 *  This is confusing. Client code should call AppLayout.getCDIBuilder(...) to build a new instance of
	 *  AppLayoutComponent but must pass an existing component in this method? Where does this instance come from? 
	 *  If an instance can be created outside, the Builder makes no sense.
	 *  
	 *  From my point of view only the methods makes sense which gets an instance of Behavior as argument.
	 *  
	 *  Or explain the methods via JavaDoc. 
	 *  IMPORTANT: In general this class should explained very well and carefully because it's the bootstrap class for client code.
	 *  e.g. see JPA bootstrap class: javax.persistence.Persistence
	 *  
	 *  Therefore I would move that class to an not so deep package like: com.github.appreciated.app.layout
	 *  
	 *  Package naming. I think AppLayout is this component in total. I would not devide the name in the package like:
	 *  
	 *  com.github.appreciated.app_layout
	 *  or
	 *  com.github.appreciated.applayout
	 *  
	 *  
	 *  It is also a good idea to have all classes you create instances from with this builder in the same package and 
	 *  the constructors of that classes are package-private.
	 *  So you force client to use your builder and nobody can create an instance of it's own.
	 *  
	 *  
	 */
	public static CDIBuilderNavigatorPreamble getCDIBuilder( AppLayoutComponent layout )
	{
		return new CDIBuilderNavigatorPreamble( CDIAppLayoutBuilder.get( layout ) );
	}

	public static CDIBuilderNavigatorPreamble getCDIBuilder( Behaviour behaviour )
	{
		return new CDIBuilderNavigatorPreamble( CDIAppLayoutBuilder.get( behaviour.getInstance() ) );
	}

	public static NavigatorAppLayoutBuilder getDefaultBuilder( AppLayoutComponent layout )
	{
		return NavigatorAppLayoutBuilder.get( layout );
	}

	public static NavigatorAppLayoutBuilder getDefaultBuilder( Behaviour behaviour )
	{
		return NavigatorAppLayoutBuilder.get( behaviour.getInstance() );
	}

	public static NoNavigatorAppLayoutBuilder getNoNavigatorBuilder( AppLayoutComponent layout )
	{
		return NoNavigatorAppLayoutBuilder.get( layout );
	}

	public static NoNavigatorAppLayoutBuilder getNoNavigatorBuilder( Behaviour behaviour )
	{
		return NoNavigatorAppLayoutBuilder.get( behaviour.getInstance() );
	}
}
