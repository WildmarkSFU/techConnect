import { LitElement } from 'lit';
import './lit-dialog';
declare const ConfirmModal_base: import("./with-lit-dialog").GConstructor<import("./with-lit-dialog").WithLitDialogInterface> & typeof LitElement;
export default class ConfirmModal extends ConfirmModal_base {
    static styles: import("lit").CSSResult[];
    cancelLabel: string;
    confirmLabel: string;
    confirmCallback: Function | undefined;
    secondaryLabel: string;
    secondaryAction: Function | undefined;
    closeOnConfirmation: boolean;
    enableLightDismiss: boolean;
    handleConfirm(): void;
    handleSecondaryAction(): void;
    render(): import("lit-html").TemplateResult<1>;
}
declare global {
    interface HTMLElementTagNameMap {
        'confirm-modal': ConfirmModal;
    }
}
export {};
//# sourceMappingURL=confirm-modal.d.ts.map