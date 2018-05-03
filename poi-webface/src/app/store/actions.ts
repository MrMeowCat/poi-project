import { Action } from "@ngrx/store";
import { StyledTheme } from "../shared/models/styled-themes";

export enum ThemeActionTypes {
  ThemeChange = "[MAP] THEME_CHANGE"
}

export type ActionTypes = ThemeActionTypes

export class ThemeChangeAction implements Action {
  readonly type: ThemeActionTypes = ThemeActionTypes.ThemeChange;
  constructor(public payload: StyledTheme) { }
}

export type Actions = ThemeChangeAction
