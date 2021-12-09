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
import '@vaadin/vaadin-themable-mixin/vaadin-themable-mixin.js';
import '@vaadin/vaadin-icons/vaadin-icons.js';
import '@polymer/app-layout/app-drawer/app-drawer.js';
import '@polymer/app-layout/app-drawer-layout/app-drawer-layout.js';
import '@polymer/app-layout/app-header/app-header.js';
import '@polymer/app-layout/app-header-layout/app-header-layout.js';
import '@polymer/app-layout/app-toolbar/app-toolbar.js';

class AppLayoutLeftHybrid extends ElementMixin(ThemableMixin(PolymerElement)) {
    static get is() {
        return 'app-layout-left-hybrid'
    }

    static get template() {
        return html`
        <style>
            :root {
                --paper-badge-background: var(--app-layout-badge-background);
                --paper-badge-text-color: var(--app-layout-badge-font-color);
                --primary-color: var(--app-layout-primary-color);
            }

            slot[name="app-bar-content"]::slotted(*) {
                pointer-events: inherit;
            }

            app-header {
                right: 0px !important;
                background: var(--app-layout-bar-background-base-color);
                top: 0px;
                height: var(--app-layout-bar-height);
            }

            app-toolbar {
                height: var(--app-layout-bar-height);
                background: var(--app-layout-bar-background-color);
                box-shadow: var(--app-layout-bar-shadow);
                color: var(--app-layout-bar-font-color);
                padding: var(--app-layout-bar-padding);
            }

            #app-bar-elements {
                min-width: 0;
            }

            /* non-overlay */
            :root {
                --app-drawer-scrim-background: rgba(0, 0, 0, 0);
            }

            app-toolbar {
                z-index: 1;
                position: absolute;
                top: 0px;
                left: 0px;
                right: 0px;
            }

            #drawer {
                margin-top: var(--app-layout-bar-height);
            }

            .application-content {
                width: 100%;
                padding-top: var(--app-layout-bar-height);
                box-sizing: border-box;
                background: var(--app-layout-background);
            }

            /* non-overlay */
            #drawer {
                --app-drawer-content-container: {
                    box-shadow: var(--app-layout-drawer-shadow);
                    background-color: var(--app-layout-drawer-background-base-color);
                    max-width: 100%;
                };
                transition: 1s width;
            }

            #drawer .drawer-content {
                height: 100%;
                width: 100%;
                background: var(--app-layout-drawer-background-color);
            }

            #toggle {
                flex-shrink: 0;
                width: calc(var(--app-layout-bar-height) - calc(var(--app-layout-space-s) * 2));
                height: calc(var(--app-layout-bar-height) - calc(var(--app-layout-space-s) * 2));
                margin: var(--app-layout-space-s);
                min-width: calc(var(--app-layout-bar-height) - calc(var(--app-layout-space-s) * 2));
            }
            
             #toggle iron-icon {
               margin-right: var(--app-layout-bar-toggle-icon-right);
            }

            @media only screen and (min-width: 640px) {
                #drawerLayout.small {
                    --app-drawer-width: var(--app-layout-drawer-small-width);
                    --app-layout-badge-width: var(--app-layout-badge-small-width);
                    --app-layout-badge-height: var(--app-layout-badge-small-height);
                    --app-layout-badge-top: var(--app-layout-badge-small-top);
                    --app-layout-badge-right: var(--app-layout-badge-small-right);
                    --app-layout-badge-color: transparent;
                    --app-layout-menu-button-margin: 0;
                    --app-layout-menu-button-padding: 0 11px;
                }

                #drawerLayout.small .app-menu-item {
                    overflow: hidden;
                }

                #drawerLayout.small iron-icon[icon="icons:expand-more"],
                #drawerLayout.small iron-icon[icon="icons:expand-less"] {
                    color: transparent;
                }

                app-menu-icon-item {
                    transition: color 0.2s ease;
                }

                #drawerLayout {
                    --expand-icon-fill-color: var(--iron-icon-fill-color, currentcolor) !important;
                }

                app-menu {
                    background: transparent;
                }

                #drawerLayout.small * {
                    --expand-icon-fill-color: transparent !important;
                    --app-layout-badge-font-color: transparent;
                }
            }
        </style>
        <app-header-layout id="layout-wrapper" fullbleed>
            <app-header fixed slot="header" part="app-bar">
                <app-toolbar style="height: var(--app-layout-bar-height);">
                    <vaadin-button id="toggle" theme="large tertiary icon">
                    </vaadin-button>
                    <div class="app-bar-content" id="app-bar-elements" style="width: 100%;height: 100%;">
                        <slot name="app-bar-content"></slot>
                    </div>
                </app-toolbar>
            </app-header>
            <app-drawer-layout id="drawerLayout" fullbleed>
                <app-drawer part="drawer" id="drawer" slot="drawer">
                    <div class="drawer-content" id="menu-elements">
                        <slot name="drawer-content"></slot>
                    </div>
                </app-drawer>
                <div class="application-content" part="application-content" id="content">
                    <slot name="application-content"></slot>
                </div>
            </app-drawer-layout>
        </app-header-layout>`;
    }

    ready() {
        super.ready();
        this.shadowRoot.querySelector("#toggle").addEventListener('click', evt => this.onclick());
    }

    onclick() {
        var drawer = this.shadowRoot.querySelector("#drawer");
        var drawerLayout = this.shadowRoot.querySelector("#drawerLayout");

        if (drawer.persistent) {
            if (!this.shadowRoot.querySelector("#toggle").classList.contains('show-back-arrow')) {
                drawerLayout.classList.toggle("small");
                this.classList.toggle("small");
            } else {
                this.onUpNavigation();
            }
        } else {
            if (!this.shadowRoot.querySelector("#toggle").classList.contains('show-back-arrow')) {
                drawer.toggle();
            } else {
                this.onUpNavigation();
            }
        }
    }

    onUpNavigation() {
    }

    closeIfNotPersistent() {
        var drawer = this.shadowRoot.querySelector("#drawer");
        if (!drawer.persistent) {
            drawer.close();
        }
    }
}

customElements.define(AppLayoutLeftHybrid.is, AppLayoutLeftHybrid);