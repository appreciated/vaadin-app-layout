<p align="center"><img src="https://raw.githubusercontent.com/appreciated/blob/master/app-layout/logo_low_res.png">
<br>
<br>
</p>    

<p align="center">
  <a href="https://vaadin.com/directory/component/app-layout-add-on"><img src="https://img.shields.io/badge/Vaadin%20Directory-published-00b4f0.svg"></a>
  <a href="https://vaadin.com/directory/component/app-layout-add-on"><img src="https://img.shields.io/vaadin-directory/version/app-layout-add-on.svg" /></a>
   <a href="https://vaadin.com/directory/component/app-layout-add-on"><img src="https://img.shields.io/vaadin-directory/rating/app-layout-add-on.svg" /></a>
   <a href="https://vaadin.com/directory/component/app-layout-add-on"><img src="https://img.shields.io/vaadin-directory/release-date/app-layout-add-on.svg" /></a>
   <a href="https://dev.azure.com/GoebelJohannes/GoebelJohannes/_build/latest?definitionId=1&branchName=master"><img src="https://dev.azure.com/GoebelJohannes/GoebelJohannes/_apis/build/status/appreciated.vaadin-app-layout?branchName=master" /></a>
</p>
<p align="center">
   <br>
A collection of ready to use templates for the Webcomponent <a href="https://www.webcomponents.org/element/PolymerElements/app-layout">&lt;app-layout&gt;</a>
  <br>
   <br>
  <br>
  <img src="https://github.com/appreciated/blob/blob/master/app-layout/mockup.png?raw=true">
</p>
<br>
<br>

## [Demo](https://appreciated-collection.herokuapp.com/app-layout/)

## Installation

Add the following lines to you POM
```
<dependency>
   <groupId>com.github.appreciated</groupId>
   <artifactId>app-layout-addon</artifactId>
   <version></version>
</dependency>

<!-- Should be there by default
<repository>
   <id>vaadin-addons</id>
   <url>https://maven.vaadin.com/vaadin-addons</url>
</repository> 
--> 
```

## Versioning

* Vaadin 8 -> [App-Layout `1.*`](https://vaadin.com/directory/component/app-layout-add-on/1.0.2)
* Vaadin 10 -> [App-Layout `2.*`](https://vaadin.com/directory/component/app-layout-add-on/2.1.0)
* Vaadin 13 or Vaadin 14 with bower mode -> [App-Layout `3.*`](https://vaadin.com/directory/component/app-layout-add-on/3.0.0.beta5)
* Vaadin 14 with NPM Mode -> App-Layout [App-Layout `4.*`](https://vaadin.com/directory/component/app-layout-add-on)

Execute the following command in the at the root of your project

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
cd <project-root-dir>
mvn clean install -DskipTests # to skip the integration tests
cd <project-root-dir>/app-layout-addon
mvn jetty:run
```

To see the demo, navigate to [http://localhost:8080/](http://localhost:8080/)

## Issue tracking

The issues for this add-on are tracked on its github.com page. All bug reports and feature requests are appreciated. 

## Branching information

* `master` the latest version of the app-layout, currently even with `V13`
* `V8` version based on Vaadin 8 -> App-Layout `1.*`
* `V10` version based on Vaadin 10 -> App-Layout `2.*`
* `V13` support is discontinued due to the short support consider to upgrade to Vaadin 14 instead
* `V14` version based on Vaadin 14 -> App-Layout `4.*`

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


# Donations

[You like this Project and want to support me?](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=RH84HC939XQHS)

## Features

...
