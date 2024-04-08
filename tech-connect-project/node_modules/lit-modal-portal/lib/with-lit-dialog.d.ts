import { LitElement } from 'lit';
import { Ref } from 'lit/directives/ref.js';
import LitDialog from './lit-dialog';
export type GConstructor<T = {}> = new (...args: any[]) => T;
export declare class WithLitDialogInterface {
    litDialogRef: Ref<LitDialog>;
    closeDialog(): void;
}
export declare const WithLitDialog: <T extends GConstructor<LitElement>>(superclass: T) => GConstructor<WithLitDialogInterface> & T;
//# sourceMappingURL=with-lit-dialog.d.ts.map