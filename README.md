# 
A port of VAADIN.polymer app-layout
# Integration of the Polymer Component app-layout as a Add-on for Vaadin 8

## Vaadin Directory

For download and reviews, go to https://vaadin.com/directory#!addon/circular-progressbar-add-on1

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
```
git clone git@github.com:appreciated/circular-progressbar.git
mvn clean install
cd demo
mvn jetty:run
```

To see the demo, navigate to http://localhost:8080/

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

The used Polymer Components are distributed under the BSD License

## Features

...
