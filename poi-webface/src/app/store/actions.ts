import { Action } from "@ngrx/store";
import { Theme } from "../shared/models/theme";

export enum ActionTypes {
  ThemeChange = "[MAP] THEME_CHANGE",
  SidebarToggle = "[SIDEBAR] TOGGLE"
}

export class ThemeChangeAction implements Action {
  readonly type: ActionTypes = ActionTypes.ThemeChange;
  constructor(public payload: Theme) { }
}

export class SidebarToggleAction implements Action {
  readonly type: ActionTypes = ActionTypes.SidebarToggle;
}

export type Actions = ThemeChangeAction
| SidebarToggleAction
