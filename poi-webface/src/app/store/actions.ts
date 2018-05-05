import { Action } from "@ngrx/store";
import { Theme } from "../shared/models/theme";

export enum ThemeActionTypes {
  ThemeChange = "[MAP] THEME_CHANGE"
}

export type ActionTypes = ThemeActionTypes

export class ThemeChangeAction implements Action {
  readonly type: ThemeActionTypes = ThemeActionTypes.ThemeChange;
  constructor(public payload: Theme) { }
}

export type Actions = ThemeChangeAction
