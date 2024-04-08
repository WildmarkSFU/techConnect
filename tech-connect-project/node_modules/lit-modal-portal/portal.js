import { Directive, directive } from "lit/directive.js";
import modalC from "./modal-controller";
class PortalDirective extends Directive {
  /** Resolves the template argument if it is a supplier function. */
  getTemplate(templateOrSupplier) {
    if (templateOrSupplier instanceof Function) {
      return templateOrSupplier();
    } else {
      return templateOrSupplier;
    }
  }
  /**
   * The core logic of the [[portal | `portal`]] directive.
   *
   * If `showModal` is true, or if it is a Function that produces a truthy result,
   * then the given `template` and optional `closeCallback` will be pushed to the [[ModalPortal | `<modal-portal>`]].
   *
   * If there already exists a registry for a modal sent using this exact directive,
   * then it will be replaced using the new arguments.
   *
   * If `showModal` is falsy, then the modal is removed and the registry is reset.
   */
  render(showModal, template, closeCallback) {
    if (showModal instanceof Function) {
      showModal = showModal();
    }
    if (this.modalRegistry) {
      if (showModal) {
        this.modalRegistry.replace(this.getTemplate(template), closeCallback);
      } else {
        this.modalRegistry.remove();
        this.modalRegistry = void 0;
      }
    } else if (showModal) {
      this.modalRegistry = modalC.push(this.getTemplate(template), closeCallback);
    }
  }
}
const portal = directive(PortalDirective);
export {
  PortalDirective,
  portal
};
//# sourceMappingURL=portal.js.map
