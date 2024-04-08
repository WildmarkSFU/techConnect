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
import { ref, createRef } from "lit/directives/ref.js";
import { classMap } from "lit/directives/class-map.js";
let LitDialog = class extends LitElement {
  constructor() {
    super(...arguments);
    /** Reference for the `<dialog>` element. */
    this.dialogRef = createRef();
    this.label = "";
    this.enableLightDismiss = false;
    this.size = "small";
    this.unsetStyles = true;
  }
  /** Accessor for the value stored in [[dialogRef]]. */
  get dialog() {
    return this.dialogRef.value;
  }
  get classes() {
    return { unset: this.unsetStyles };
  }
  /** Convenience wrapper for the `<dialog>`'s `close()` function. */
  close() {
    this.dialog?.close();
  }
  /**
   * Handler for the `<dialog>`'s `"close"` event.
   * Triggers the `"removeModal"` event.
   * @event removeModal
   */
  onDialogClose() {
    this.dispatchEvent(new Event("removeModal", { bubbles: true, composed: true }));
  }
  firstUpdated() {
    this.dialog.showModal();
    this.dialog.addEventListener("close", () => this.onDialogClose());
    if (this.enableLightDismiss) {
      this.dialog.addEventListener("click", (e) => this.onClick(e));
    }
  }
  onClick(event) {
    if (event.target === this.dialog) {
      this.close();
    }
  }
  render() {
    return html`
      <dialog
        ${ref(this.dialogRef)}
        class=${classMap(this.classes)}
        size=${this.size}
        aria-label="${this.label}"
        aria-modal="true"
      >
        <slot></slot>
      </dialog>
    `;
  }
};
LitDialog.styles = css`
    dialog {
      display: flex;
      justify-content: center;
    }

    dialog.unset {
      border: unset;
      background: unset;
      max-width: unset;
      max-height: unset;
      height: unset;
      width: unset;
      margin: unset;
    }

    dialog::backdrop {
      background: var(--lit-dialog-backdrop-bg, hsl(0 0% 0% / 0.3));
    }

    dialog[size='small'] {
      align-items: center;
    }

    dialog[size='large'] {
      padding: 4rem;
    }
  `;
__decorateClass([
  property()
], LitDialog.prototype, "label", 2);
__decorateClass([
  property({ type: Boolean, attribute: false })
], LitDialog.prototype, "enableLightDismiss", 2);
__decorateClass([
  property()
], LitDialog.prototype, "size", 2);
__decorateClass([
  property({ type: Boolean, attribute: false })
], LitDialog.prototype, "unsetStyles", 2);
LitDialog = __decorateClass([
  customElement("lit-dialog")
], LitDialog);
export {
  LitDialog as default
};
//# sourceMappingURL=lit-dialog.js.map
