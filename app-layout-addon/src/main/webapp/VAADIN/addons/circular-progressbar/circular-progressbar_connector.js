window.com_github_appreciated_circularprogressbar_CircularProgressBarClient = function () {

    var circularProgressbar = this.getElement();
    circularProgressbar.innerHTML = '<svg id="animated" viewBox="0 0 100 100">'+
    //'<defs>' +
    //'<linearGradient id="gradient" x1="0%" y1="0%" x2="100%" y2="0%">' +
    //'<stop offset="0%" stop-color="#f00"/>' +
    //'<stop offset="100%" stop-color="#0f0"/>' +
    //'</linearGradient>' +
    //'</defs>' +
    '<path id="progress-background" class="circular-progressbar-circle-part" stroke-linecap="round" stroke-width="6" stroke="#d4d4d4" fill="none" stroke-dasharray="251.2,251.2" d="M50 10 a 40 40 0 0 1 0 80 a 40 40 0 0 1 0 -80"></path>'+ // Track
    '<path id="progress-border" class="circular-progressbar-circle-part" stroke-linecap="round" stroke-width="6" stroke="#1362b1" fill="none" stroke-dasharray="0,251.2" d="M50 10 a 40 40 0 0 1 0 80 a 40 40 0 0 1 0 -80"></path>'+ // Border
    '<path id="progress-inner" class="circular-progressbar-circle-part" stroke-linecap="round" stroke-width="4" stroke="#197de1" fill="none" stroke-dasharray="0,251.2" d="M50 10 a 40 40 0 0 1 0 80 a 40 40 0 0 1 0 -80"></path>'+ // Indicator
    '<text id="count" x="50" y="50" text-anchor="middle" dy="7" font-size="17">0%</text>' + // Percentage Label
    '</svg>';
    this.onStateChange = function () {
        updateTrack(this.getState().scale);
        if (this.getState().label) {
            updateLabel(this.getState().label);
        }
        animateProgress(this.getState().value, this.getState().scale);
    }

    function animateProgress(percent = 0, scale = 1.0) {
        if (percent !== 'undefined') {
            console.log(percent);
            var progressBorder = circularProgressbar.querySelector('#progress-border');
            var progressInner = circularProgressbar.querySelector('#progress-inner');
            var text = circularProgressbar.querySelector('#count');
            var animationStep = progressBorder.getAttribute('stroke-dasharray').split(",")[0];
            var id = setInterval(frame, 10);
            var grow = animationStep < 251.2 * percent;
            function frame() {
                if (grow) {
                    if (animationStep >= 251.2 * percent * scale  ) {
                        progressBorder.setAttribute('stroke-dasharray', 251.2 * percent * scale + ',251.2');
                        progressInner.setAttribute('stroke-dasharray', 251.2 * percent * scale  + ',251.2');
                        text.textContent = parseInt(percent*100)+ '%';
                        clearInterval(id);
                    } else {
                        progressBorder.setAttribute('stroke-dasharray', animationStep + ',251.2');
                        progressInner.setAttribute('stroke-dasharray', animationStep + ',251.2');
                        text.textContent = parseInt(animationStep / 251.2 * 100)+ '%';
                        animationStep++;
                    }
                } else {
                    if (animationStep < 251.2 * percent * scale) {
                        progressBorder.setAttribute('stroke-dasharray', 251.2 * percent * scale + ',251.2');
                        progressInner.setAttribute('stroke-dasharray', 251.2 * percent * scale + ',251.2');
                        text.textContent = parseInt(percent*100)+ '%';
                        clearInterval(id);
                    } else {
                        progressBorder.setAttribute('stroke-dasharray', animationStep + ',251.2');
                        progressInner.setAttribute('stroke-dasharray', animationStep + ',251.2');
                        text.textContent = parseInt(animationStep / 251.2*100)+ '%';
                        animationStep--;
                    }
                }
            }
        }
    }

    function updateTrack(scale = 1.0){
        var progressTrack = circularProgressbar.querySelector('#progress-background');
        progressTrack.setAttribute('stroke-dasharray', 251.2 * scale + ',251.2');
    }

    function updateLabel(labelText){
        var count = circularProgressbar.querySelector('#count');
        count.setAttribute('y', 43);
        var label = circularProgressbar.querySelector('#label');
        if (!label){
              var svg = circularProgressbar.querySelector('#animated');
              svg.innerHTML = svg.innerHTML + '<text id="label" x="50" y="59" text-anchor="middle" dy="7" font-size="11">'+labelText+'</text>';
        } else {
            label.textContent = labelText;
        }
    }
};