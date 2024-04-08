import { is } from "immutable";
function applyState(existingState, newState) {
  if (newState === void 0 || Object.keys(newState).length == 0) {
    return existingState;
  }
  return existingState.merge(Object.entries(newState));
}
function isNew(newState, name, current) {
  return newState.has(name) && !is(newState.get(name), current);
}
export {
  applyState,
  isNew
};
//# sourceMappingURL=state.js.map
