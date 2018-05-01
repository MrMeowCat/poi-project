import { StyledTheme } from "../models/styled-themes";
import { Action } from "@ngrx/store";

export enum ThemeActionTypes {
  ThemeChange = "[MAP] THEME_CHANGE"
}

export type ActionTypes = ThemeActionTypes

export class ThemeChangeAction implements Action {
  readonly type: ThemeActionTypes = ThemeActionTypes.ThemeChange;
  constructor(public payload: StyledTheme) { }
}

export type Actions = ThemeChangeAction
