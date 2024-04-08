import { createRef } from "lit/directives/ref.js";
const WithLitDialog = (superclass) => {
  class LitElementWithLitDialog extends superclass {
    constructor() {
      super(...arguments);
      this.litDialogRef = createRef();
    }
    closeDialog() {
      this.litDialogRef.value?.close();
    }
  }
  return LitElementWithLitDialog;
};
export {
  WithLitDialog
};
//# sourceMappingURL=with-lit-dialog.js.map
