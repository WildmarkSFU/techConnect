import { ReactiveController, TemplateResult } from 'lit';
import { List, Map } from 'immutable';
import { MapOf } from './lib/state';
import ModalPortal from './modal-portal';
export type KeyedTemplateResult = TemplateResult & {
    key: string;
};
export type ModalRegistry = {
    remove: Function;
    replace: (template: TemplateResult, closeCallback?: Function) => void;
};
export type ModalState = {
    modalStack: List<KeyedTemplateResult>;
    modalNodes: List<EventTarget>;
    closeCallbacks: Map<string, Function>;
};
export interface ModalController extends ReactiveController {
    host?: ModalPortal;
    modalState: MapOf<ModalState>;
    modalStack: List<KeyedTemplateResult>;
    modalNodes: List<EventTarget>;
    closeCallbacks: Map<string, Function>;
    attach: (host: ModalPortal) => void;
    push: (template: TemplateResult, closeCallback?: Function) => ModalRegistry;
    pop: () => void;
    removeByNode: (modal: EventTarget) => void;
    removeByKey: (key: string) => void;
    removeAll: () => void;
}
declare const modalController: ModalController;
export default modalController;
//# sourceMappingURL=modal-controller.d.ts.map