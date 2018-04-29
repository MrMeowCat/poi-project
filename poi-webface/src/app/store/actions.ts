import { Theme } from "../models/themes";
import { Action } from "@ngrx/store";

export enum ThemeActionTypes {
  ThemeChange = "[MAP] THEME_CHANGE"
}

export type ActionTypes = ThemeActionTypes

export class ThemeChangeAction implements Action {
  readonly type: ThemeActionTypes = ThemeActionTypes.ThemeChange;
  constructor(public payload: Theme) { }
}

export type Actions = ThemeChangeAction
