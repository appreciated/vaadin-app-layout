npm install -g polymer-cli
polymer install
polymer build --js-compile
echo "you have successfully build the JS dependencies for this project now you only need to update src/main/webapp/VAADIN/bower_components with the freshly built one in build/default/bower_components"