package com.github.appreciated.app.layout.webcomponents.papertabs;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

/*
<paper-tabs selected="0" scrollable>
  <paper-tab>The first tab</paper-tab>
  <paper-tab>Tab two</paper-tab>
  <paper-tab>The third tab</paper-tab>
  <paper-tab>Fourth tab</paper-tab>
</paper-tabs>
 */

@Tag("paper-tabs")
@HtmlImport("bower_components/paper-tabs/paper-tabs.html")
public class PaperTabs extends Component {

    public void setScrollable(boolean scrollable) {
        if (scrollable) {
            getElement().setAttribute("scrollable", true);
        } else {
            getElement().removeAttribute("scrollable");
        }
    }

}
