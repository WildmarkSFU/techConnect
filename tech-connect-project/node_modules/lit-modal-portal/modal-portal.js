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
import { customElement, state } from "lit/decorators.js";
import { repeat } from "lit/directives/repeat.js";
import { ref, createRef } from "lit/directives/ref.js";
import { List, is } from "immutable";
import modalC from "./modal-controller";
let ModalPortal = class extends LitElement {
  constructor() {
    super();
    this.modalStack = List();
    this.portalRef = createRef();
    /**
     * Inspects the `composedPath()` of the given event and removes the modal that
     * intersects with the path, if any.
     */
    this.removeModal = (e) => {
      e.stopImmediatePropagation();
      e.preventDefault();
      const eventPath = e.composedPath();
      const portalEventPathIndex = eventPath.findIndex((el) => el === this.portalRef.value);
      if (portalEventPathIndex < 1) {
        console.warn(
          "Could not locate modal portal at appropriate depth in the @removeModal event path"
        );
      } else {
        const modalNode = eventPath[portalEventPathIndex - 1];
        modalC.removeByNode(modalNode);
      }
    };
    modalC.attach(this);
  }
  /**
   * A list of the modals currently present in the DOM. Used by the [[ModalController]].
   */
  get modalNodes() {
    return this.portalRef.value?.children;
  }
  /**
   * Used by the [[ModalController]] when a modal is added or removed.
   * Updates the `<body>` element to have the `"modal-portal-active"` class
   * precisely when the `<modal-portal>` contains at least one modal.
   */
  offerState(newState) {
    if (!is(this.modalStack, newState.get("modalStack"))) {
      this.modalStack = newState.get("modalStack");
      if (this.modalStack.size > 0) {
        document.querySelector("body").classList.add("modal-portal-active");
      } else {
        document.querySelector("body").classList.remove("modal-portal-active");
      }
    }
  }
  connectedCallback() {
    super.connectedCallback();
    this.addEventListener("removeModal", this.removeModal);
  }
  render() {
    return html`
      <div id="portal" ${ref(this.portalRef)}>
        ${repeat(
      this.modalStack?.values(),
      (modal) => modal.key,
      (modal) => html`<div class="modal-node">${modal}</div>`
    )}
      </div>
    `;
  }
};
ModalPortal.styles = css`
    #portal {
      isolation: isolate;
    }
  `;
__decorateClass([
  state()
], ModalPortal.prototype, "modalStack", 2);
ModalPortal = __decorateClass([
  customElement("modal-portal")
], ModalPortal);
export {
  ModalPortal as default
};
//# sourceMappingURL=modal-portal.js.map
