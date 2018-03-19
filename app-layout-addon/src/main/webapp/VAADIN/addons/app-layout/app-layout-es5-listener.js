window.com_github_appreciated_app_layout_behaviour_listener_AppLayoutES5Listener = function () {
    function check() {
        "use strict";

        if (typeof Symbol === "undefined") return false;
        try {
            eval("class Foo {}");
            eval("var bar = (x) => x+1");
        } catch (e) {
            return false;
        }

        return true;
    }

    if (check()) {
        // The engine supports ES6 features
    } else {
        // The engine doesn't support those ES6 features
        // Use the boring ES5 :(

        var script_es5_adapter = document.createElement("script");
        script_es5_adapter.src = "/VAADIN/frontend/bower_components/webcomponentsjs/custom-elements-es5-adapter.js";
        document.head.appendChild(script_es5_adapter);
    }
};