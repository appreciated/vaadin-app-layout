/*
@license
Copyright (c) 2015 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/
import {html, PolymerElement} from '@polymer/polymer';
import {ThemableMixin} from '@vaadin/vaadin-themable-mixin';
import {ElementMixin} from '@vaadin/component-base/src/element-mixin.js';
import '@vaadin/vaadin-icons/vaadin-icons.js';
import '@polymer/app-layout/app-drawer/app-drawer.js';
import '@polymer/app-layout/app-drawer-layout/app-drawer-layout.js';
import '@polymer/app-layout/app-header/app-header.js';
import '@polymer/app-layout/app-header-layout/app-header-layout.js';
import '@polymer/app-layout/app-toolbar/app-toolbar.js';
import '@polymer/app-layout/app-scroll-effects/effects/waterfall.js';

class AppLayoutTop extends ElementMixin(ThemableMixin(PolymerElement)) {
    static get template() {
        return html`
        <style>
            :root {
                --paper-badge-background: var(--app-layout-badge-background);
                --paper-badge-text-color: var(--app-layout-badge-font-color);
            }

            slot[name="app-bar-content"]::slotted(*) {
                pointer-events: inherit;
            }

            app-toolbar {
                height: var(--app-layout-bar-height);
                padding: 0 0 0 16px;
                color: var(--app-layout-bar-font-color);
                background: var(--app-layout-bar-background-color);
            }

            app-header {
                background: var(--app-layout-bar-background-base-color);
                box-shadow: var(--app-layout-bar-shadow);
                color: var(--app-layout-bar-font-color);
            }

            app-drawer {
                --iron-icon-fill-color: #727272;
            }

            #toggle:not(.show-back-arrow) {
                display: none;
            }

            .application-content {
                width: 100%;
                background: var(--app-layout-background);
                margin-top: calc(var(--app-layout-bar-height) * -1);
                padding-top: var(--app-layout-bar-height);
                box-sizing: border-box;
            }

        </style>
        <app-header-layout fullbleed>
            <app-header part="app-bar" slot="header" condenses reveals style="transform: translate3d(0px, 0px, 0px); transition-duration: 0ms;">
                <app-toolbar style="transform: translate3d(0px, 0px, 0px);">
                    <vaadin-button id="toggle" theme="large tertiary icon">
                    </vaadin-button>
                    <div id="app-bar-elements" class="app-bar-content" style="height: 100%;width: 100%;">
                        <slot name="app-bar-content"></slot>
                    </div>
                </app-toolbar>
            </app-header>
            <div id="content" class="application-content">
                <slot name="application-content"></slot>
            </div>
        </app-header-layout>`;
    }

    static get is() {
        return 'app-layout-top'
    }

    ready() {
        super.ready();
        this.shadowRoot.querySelector("#toggle").addEventListener('click', evt => this.onclick());
    }

    onclick() {
        var drawer = this.shadowRoot.querySelector("#drawer");
        if (!this.shadowRoot.querySelector("#toggle").classList.contains('show-back-arrow')) {
            drawer.toggle();
        } else {
            this.onUpNavigation();
        }
    }

    onUpNavigation() {
    }

    closeIfNotPersistent() {
    }
}

customElements.define(AppLayoutTop.is, AppLayoutTop);