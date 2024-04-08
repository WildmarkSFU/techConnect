import { List, Map } from "immutable";
import uuid from "./lib/uuid";
import { applyState, isNew } from "./lib/state";
function addKeyToTemplate(el, key) {
  const keyedTemplate = el;
  keyedTemplate.key = key;
  return keyedTemplate;
}
let _modalState = Map({
  modalStack: List(),
  modalNodes: List(),
  closeCallbacks: Map()
});
function removeModal(index) {
  if (index >= 0) {
    const key = this.modalStack.get(index)?.key;
    const callback = this.closeCallbacks.get(key);
    if (callback !== void 0) {
      callback();
    }
    this.modalState = applyState(this.modalState, {
      modalStack: this.modalStack.delete(index),
      modalNodes: this.modalNodes.delete(index),
      closeCallbacks: this.closeCallbacks.delete(key)
    });
  }
}
function replaceModal(key, template, closeCallback) {
  const index = this.modalStack.findIndex((kt) => kt.key === key);
  if (index >= 0) {
    this.modalState = applyState(this.modalState, {
      modalStack: this.modalStack.set(index, addKeyToTemplate(template, key)),
      closeCallbacks: closeCallback !== void 0 ? this.closeCallbacks.set(key, closeCallback) : this.closeCallbacks.delete(key)
    });
  }
}
const modalController = {
  host: void 0,
  set modalState(newState) {
    if (isNew(newState, "modalStack", this.modalStack)) {
      this.host.offerState(
        Map({
          modalStack: newState.get("modalStack")
        })
      );
    }
    _modalState = newState;
  },
  get modalState() {
    return _modalState;
  },
  get modalStack() {
    return this.modalState.get("modalStack");
  },
  get modalNodes() {
    return this.modalState.get("modalNodes");
  },
  get closeCallbacks() {
    return this.modalState.get("closeCallbacks");
  },
  attach(host) {
    if (this.host === void 0) {
      (this.host = host).addController(this);
    } else {
      console.error("You attempted to attach a singleton controller to more than one host.");
    }
  },
  hostConnected() {
    this.host.offerState(Map({ modalStack: this.modalStack }));
  },
  hostUpdated() {
    this.modalState = applyState(this.modalState, {
      modalNodes: List(this.host.modalNodes)
    });
  },
  push(template, closeCallback) {
    const key = uuid();
    this.modalState = applyState(this.modalState, {
      modalStack: this.modalStack.push(addKeyToTemplate(template, key)),
      closeCallbacks: closeCallback !== void 0 ? this.closeCallbacks.set(key, closeCallback) : this.closeCallbacks
    });
    return {
      remove: () => this.removeByKey(key),
      replace: (template2, closeCallback2) => replaceModal.call(this, key, template2, closeCallback2)
    };
  },
  pop() {
    removeModal.call(this, this.modalStack.size - 1);
  },
  removeByNode(modal) {
    removeModal.call(this, this.modalNodes.indexOf(modal));
  },
  removeByKey(key) {
    removeModal.call(
      this,
      this.modalStack.findIndex((kt) => kt.key === key)
    );
  },
  removeAll() {
    while (this.modalStack.size > 0) {
      this.pop();
    }
  }
};
var modal_controller_default = modalController;
export {
  modal_controller_default as default
};
//# sourceMappingURL=modal-controller.js.map
