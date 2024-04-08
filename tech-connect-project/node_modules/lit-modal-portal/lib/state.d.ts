import { ReactiveElement } from 'lit';
import { Map } from 'immutable';
export type MapOf<T> = Map<keyof T, any>;
export declare function applyState<T>(existingState: MapOf<T>, newState: Partial<T>): MapOf<T>;
export interface StatefulElement<T> extends ReactiveElement {
    offerState(state: MapOf<T>): void;
}
export declare function isNew<T>(newState: MapOf<T>, name: keyof T, current: any): boolean;
//# sourceMappingURL=state.d.ts.map