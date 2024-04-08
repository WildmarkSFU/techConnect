import { LitElement, CSSResultGroup } from 'lit';
import { Ref } from 'lit/directives/ref.js';
export type ModalSize = 'small' | 'large';
export default class LitDialog extends LitElement {
    static styles: CSSResultGroup;
    protected dialogRef: Ref<HTMLDialogElement>;
    protected get dialog(): HTMLDialogElement | undefined;
    label: string;
    enableLightDismiss: boolean;
    size: ModalSize;
    unsetStyles: boolean;
    get classes(): {
        unset: boolean;
    };
    close(): void;
    onDialogClose(): void;
    firstUpdated(): void;
    onClick(event: MouseEvent): void;
    render(): import("lit-html").TemplateResult<1>;
}
declare global {
    interface HTMLElementTagNameMap {
        'lit-dialog': LitDialog;
    }
}
//# sourceMappingURL=lit-dialog.d.ts.map