import { TemplateResult } from 'lit';
import { Directive } from 'lit/directive.js';
import { ModalRegistry } from './modal-controller';
export declare class PortalDirective extends Directive {
    modalRegistry?: ModalRegistry;
    getTemplate(templateOrSupplier: TemplateResult | (() => TemplateResult)): TemplateResult;
    render(showModal: boolean | Function, template: TemplateResult | (() => TemplateResult), closeCallback?: Function): void;
}
export declare const portal: (showModal: boolean | Function, template: TemplateResult | (() => TemplateResult), closeCallback?: Function) => import("lit-html/directive").DirectiveResult<typeof PortalDirective>;
//# sourceMappingURL=portal.d.ts.map