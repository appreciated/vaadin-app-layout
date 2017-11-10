## Integration of the Polymer Component app-layout as a Add-on for Vaadin 8
A collection of ready to use templates for the polymer component [app-layout](https://www.webcomponents.org/element/PolymerElements/app-layout)
<br>
<br>
<br>
![Markup](https://github.com/appreciated/blob/blob/master/app-layout/mockup.png?raw=true "App-Layout Markup")

## Vaadin Directory

For download and reviews, go to https://vaadin.com/directory#!addon/app-layout-add-on

## [Demo](https://app-layout-wrapper.herokuapp.com/ )

## Installation

Add the following lines to you POM
```
<dependency>
   <groupId>com.github.appreciated</groupId>
   <artifactId>vaadin-app-layout</artifactId>
   <version>LATEST</version>
</dependency>

<!-- Should be there by default
<repository>
   <id>vaadin-addons</id>
   <url>http://maven.vaadin.com/vaadin-addons</url>
</repository> 
--> 
```

Excute the following command in the at the root of your project
```
mvn install
```

## Building and running demo

#### 1. 

```
git clone git@github.com:appreciated/vaadin-app-layout.git
```
#### 2. 

```
cd <project-root-dir>/vaadin-app-layout
mvn clean install
cd <project-root-dir>/vaadin-app-layout/app-layout-demo
mvn jetty:run
```

To see the demo, navigate to http://localhost:8080/

## Working with Vaadin Designer 1 or 2

The Designer won't be able to successfully compile the SCSS as long as it doesn't have access to the addon theme files. To fix this you will either need to unzip the jar and copy the needed files to src/main/webapp/VAADIN/addons/ or you could use the following maven plugin that does the job for you.

Notes:

Every time you are updating the Addon to a new version you need to run a mvn install otherwise the designer and the scss compiler will continue to use the old theme.
I do not recommend to include this job for jenkins builds, only for developement purposes.
To avoid pushing unecessary files to git you might want to add src/main/webapp/VAADIN/addons/ to your .gitignore.
````
 <plugin>
    <artifactId>maven-dependency-plugin</artifactId>
    <executions>
        <execution>
            <id>unpack-one</id>
            <phase>generate-resources</phase>
            <goals>
                <goal>unpack-dependencies</goal>
            </goals>
            <configuration>
                <includeGroupIds>com.github.appreciated</includeGroupIds>
                <includeArtifactIds>app-layout-addon</includeArtifactIds>
                <type>zip</type>
                <excludes>*</excludes>
                <includes>VAADIN/addons/**</includes>
                <outputDirectory>src/main/webapp/</outputDirectory>
            </configuration>
        </execution>
    </executions>
</plugin>
````

## Issue tracking

The issues for this add-on are tracked on its github.com page. All bug reports and feature requests are appreciated. 

## Contributions

Contributions are welcome, but there are no guarantees that they are accepted as such. Process for contributing is the following:
- Fork this project
- Create an issue to this project about the contribution (bug or feature) if there is no such issue about it already. Try to keep the scope minimal.
- Develop and test the fix or functionality carefully. Only include minimum amount of code needed to fix the issue.
- Refer to the fixed issue in commit
- Send a pull request for the original project
- Comment on the original issue that you have implemented a fix for it

## License & Author

Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.

The Java Project vaadin-app-layout is written by Appreciated 

The used Polymer Components are [distributed under the BSD License](https://github.com/Polymer/polymer/blob/master/LICENSE.txt)

## Features

...
