var __defProp = Object.defineProperty;
var __getOwnPropDesc = Object.getOwnPropertyDescriptor;
var __decorateClass = (decorators, target, key, kind) => {
  var result = kind > 1 ? void 0 : kind ? __getOwnPropDesc(target, key) : target;
  for (var i = decorators.length - 1, decorator; i >= 0; i--)
    if (decorator = decorators[i])
      result = (kind ? decorator(target, key, result) : decorator(result)) || result;
  if (kind && result)
    __defProp(target, key, result);
  return result;
};
import { LitElement, html, css } from "lit";
import { customElement, property } from "lit/decorators.js";
import { when } from "lit/directives/when.js";
import { ref } from "lit/directives/ref.js";
import { WithLitDialog } from "./with-lit-dialog";
import "./lit-dialog";
let ConfirmModal = class extends WithLitDialog(LitElement) {
  constructor() {
    super(...arguments);
    this.cancelLabel = "Cancel";
    this.confirmLabel = "Confirm";
    this.secondaryLabel = "Alternative";
    this.closeOnConfirmation = true;
    this.enableLightDismiss = true;
  }
  handleConfirm() {
    if (this.confirmCallback) {
      this.confirmCallback();
      if (this.closeOnConfirmation) {
        this.closeDialog();
      }
    }
  }
  handleSecondaryAction() {
    if (this.secondaryAction) {
      this.secondaryAction();
      this.closeDialog();
    }
  }
  render() {
    return html`
      <lit-dialog
        ${ref(this.litDialogRef)}
        label=${this.confirmLabel}
        .enableLightDismiss=${this.enableLightDismiss}
      >
        <div class="confirmation-dialog">
          <div>
            <slot>
              <p>
                This is the message that asks the user to confirm the action. This component also
                has properties for a secondary action.
              </p>
            </slot>
          </div>
          <div class="button-row">
            <button @click=${() => this.closeDialog()} autofocus>${this.cancelLabel}</button>
            <span class="spacer"></span>
            ${when(
      this.secondaryAction,
      () => html`
                <button @click=${() => this.handleSecondaryAction()}>${this.secondaryLabel}</button>
              `
    )}
            ${when(
      this.confirmCallback,
      () => html`<button @click=${() => this.handleConfirm()}>${this.confirmLabel}</button>`
    )}
          </div>
        </div>
      </lit-dialog>
    `;
  }
};
ConfirmModal.styles = [
  css`
      .confirmation-dialog {
        background: white;
        padding: 1rem;
        border-radius: 0.5rem;
        border: 2px solid black;
        max-width: 480px;
        display: flex;
        flex-direction: column;
        gap: 1rem;
      }

      .button-row {
        display: flex;
        gap: 1rem;
      }

      .spacer {
        flex-grow: 1;
      }
    `
];
__decorateClass([
  property({ attribute: "cancel-label" })
], ConfirmModal.prototype, "cancelLabel", 2);
__decorateClass([
  property({ attribute: "confirm-label" })
], ConfirmModal.prototype, "confirmLabel", 2);
__decorateClass([
  property({ attribute: false })
], ConfirmModal.prototype, "confirmCallback", 2);
__decorateClass([
  property({ attribute: "secondary-label" })
], ConfirmModal.prototype, "secondaryLabel", 2);
__decorateClass([
  property({ attribute: false })
], ConfirmModal.prototype, "secondaryAction", 2);
__decorateClass([
  property({ type: Boolean, attribute: false })
], ConfirmModal.prototype, "closeOnConfirmation", 2);
__decorateClass([
  property({ type: Boolean, attribute: false })
], ConfirmModal.prototype, "enableLightDismiss", 2);
ConfirmModal = __decorateClass([
  customElement("confirm-modal")
], ConfirmModal);
export {
  ConfirmModal as default
};
//# sourceMappingURL=confirm-modal.js.map
