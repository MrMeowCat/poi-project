import { Action } from "@ngrx/store";
import { Theme } from "../shared/models/theme";
import { User } from "../shared/models/user";

export enum ActionTypes {
  AuthShow = "[AUTH] SHOW",
  AuthHide = "[AUTH] HIDE",
  ThemeChange = "[THEME] THEME_CHANGE",
  ThemesLoad = "[THEME] THEMES_LOAD",
  SidebarToggle = "[SIDEBAR] TOGGLE",
  SidebarShow = "[SIDEBAR] SHOW",
  SidebarHide = "[SIDEBAR] HIDE",
  SidebarTabChange = "[SIDEBAR] TAB_CHANGE",
  UserChange = "[USER] CHANGE",
}

export class AuthShowAction implements Action {
  readonly type: ActionTypes = ActionTypes.AuthShow;
}

export class AuthHideAction implements Action {
  readonly type: ActionTypes = ActionTypes.AuthHide;
}

export class ThemeChangeAction implements Action {
  readonly type: ActionTypes = ActionTypes.ThemeChange;
  constructor(public payload: Theme) { }
}

export class ThemesLoadAction implements Action {
  readonly type: ActionTypes = ActionTypes.ThemesLoad;

  constructor(public payload: Theme[]) { }
}

export class SidebarToggleAction implements Action {
  readonly type: ActionTypes = ActionTypes.SidebarToggle;
}

export class SidebarShowAction implements Action {
  readonly type: ActionTypes = ActionTypes.SidebarShow;
}

export class SidebarHideAction implements Action {
  readonly type: ActionTypes = ActionTypes.SidebarHide;
}

export class SidebarTabChangeAction implements Action {
  readonly type: ActionTypes = ActionTypes.SidebarTabChange;
  constructor(public payload: number) { }
}

export class UserChangeAction implements Action {
  readonly type: ActionTypes = ActionTypes.UserChange;
  constructor(public payload: User) { }
}

export type Actions =
  | AuthShowAction
  | AuthHideAction
  | ThemeChangeAction
  | ThemesLoadAction
  | SidebarToggleAction
  | SidebarShowAction
  | SidebarHideAction
  | SidebarTabChangeAction
  | UserChangeAction

export type UserActions = UserChangeAction
export type ThemesActions = ThemesLoadAction
export type ThemeActions = ThemeChangeAction

