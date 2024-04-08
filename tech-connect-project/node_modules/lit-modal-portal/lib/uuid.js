const template = `${1e7}-${1e3}-${4e3}-${8e3}-${1e11}`;
const hexChar = (a) => (a ^ Math.random() * 16 >> a / 4).toString(16);
function uuid(a) {
  return a ? hexChar(Number(a)) : template.replace(/[018]/g, uuid);
}
export {
  uuid as default
};
//# sourceMappingURL=uuid.js.map
