package com.github.appreciated.app.layout.test.left;

import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.test.left.view.*;
import com.github.appreciated.app.layout.test.uis.left.AbstractLeftBehaviorView;
import com.vaadin.flow.component.Component;

public class LeftBehaviour extends AbstractLeftBehaviorView {
    @Override
    public Behaviour getVariant() {
        return Behaviour.LEFT;
    }

    @Override
    public Class<? extends Component> getViewForI(int i) {
        switch (i) {
            case 1:
                return View1.class;
            case 2:
                return View2.class;
            case 3:
                return View3.class;
            case 4:
                return View4.class;
            case 5:
                return View5.class;
            case 6:
                return View6.class;
            case 7:
                return View7.class;
            case 8:
                return View8.class;
            case 9:
                return View9.class;
        }
        return null;
    }


}
