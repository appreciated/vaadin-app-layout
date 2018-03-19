window.com_github_appreciated_app_layout_behaviour_listener_AppLayoutResizeListener = function () {

    var self = this;
    var limits = [0, 640, 1024]; // 0 - 640 small mobile device | 641 - 1023 tablet | > 1024 desktop or large tablet
    var currentState = 0;
    for (i = limits.length; i > 0; i--) {
        if (window.innerWidth > limits[i]) {
            currentState = i;
        }
    }
    var previousWidth;
    window.addEventListener('resize', function () {
        var currentWidth = window.innerWidth;
        if (currentWidth !== previousWidth) {
            previousWidth = currentWidth;
            if (limits[currentState] > currentWidth) {
                currentState = currentState - 1;
                setTimeout(function () {
                    self.onAppLayoutResized();
                }, 200);
            } else if (currentState !== limits.length && limits[currentState + 1] < currentWidth) {
                currentState = currentState + 1;
                setTimeout(function () {
                    self.onAppLayoutResized();
                }, 200);
            }
        }
    });

    if (window.customElements) {

        if (HTMLImports.ready) {
            setTimeout(function () {
                self.onAppLayoutResized();
            }, 1000);
        } else {
            HTMLImports.whenReady(function () {
                setTimeout(function () {
                    self.onAppLayoutResized();
                }, 1000);
            });
        }
    }
    if (document.querySelector('paper-icon-button')) {
        document.querySelector('paper-icon-button').addEventListener('click', function () {
            setTimeout(function () {
                self.onAppLayoutResized();
            }, 200);
        });
    }
};