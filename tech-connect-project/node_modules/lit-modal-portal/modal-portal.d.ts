import { LitElement } from 'lit';
import { List } from 'immutable';
import { KeyedTemplateResult } from './modal-controller';
import { MapOf, StatefulElement } from './lib/state';
export type ModalPortalState = {
    modalStack: List<KeyedTemplateResult>;
};
export default class ModalPortal extends LitElement implements StatefulElement<ModalPortalState> {
    static styles: import("lit").CSSResult;
    private modalStack;
    private portalRef;
    get modalNodes(): HTMLCollection | undefined;
    constructor();
    offerState(newState: MapOf<ModalPortalState>): void;
    protected removeModal: (e: Event) => void;
    connectedCallback(): void;
    render(): import("lit-html").TemplateResult<1>;
}
declare global {
    interface HTMLElementTagNameMap {
        'modal-portal': ModalPortal;
    }
}
//# sourceMappingURL=modal-portal.d.ts.map