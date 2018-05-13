import { ActionReducerMap } from "@ngrx/store";
import { Actions, ActionTypes, SidebarTabChangeAction, ThemeActions, ThemesActions, UserActions } from "./actions";
import { State } from "./states";
import { DefaultThemes } from "../shared/models/default-themes";
import { Theme } from "../shared/models/theme";
import { User } from "../shared/models/user";

export function authToggleReducer(authVisible: boolean = false, action: Actions): boolean {
  switch (action.type) {
    case ActionTypes.AuthShow:
      return true;
    case ActionTypes.AuthHide:
      return false;
    default:
      return authVisible;
  }
}

export function themeChangeReducer(theme: Theme = DefaultThemes.Standard, action: ThemeActions)
  : Theme {
  switch (action.type) {
    case ActionTypes.ThemeChange:
      return action.payload || theme;
    default:
      return theme;
  }
}

export function themesReducer(themes: Theme[] = [], action: ThemesActions)
  : Theme[] {
  switch (action.type) {
    case ActionTypes.ThemesLoad:
      return action.payload;
    default:
      return themes;
  }
}

export function sidebarToggleReducer(sidebarVisible: boolean = false, action: Actions)
  : boolean {
  switch (action.type) {
    case ActionTypes.SidebarToggle:
      return !sidebarVisible;
    case ActionTypes.SidebarShow:
      return true;
    case ActionTypes.SidebarHide:
      return false;
    default:
      return sidebarVisible;
  }
}

export function sidebarTabChangeReducer(sidebarTab: number = 0, action: SidebarTabChangeAction)
  : number {
  switch (action.type) {
    case ActionTypes.SidebarTabChange:
      return action.payload;
    default:
      return sidebarTab;
  }
}

export function userReducer(user: User = undefined, action: UserActions): User {
  switch (action.type) {
    case ActionTypes.UserChange:
      return action.payload;
    default:
      return user;
  }
}

export const reducerMap: ActionReducerMap<State> = {
  authVisible: authToggleReducer,
  theme: themeChangeReducer,
  themes: themesReducer,
  sidebarVisible: sidebarToggleReducer,
  sidebarTab: sidebarTabChangeReducer,
  user: userReducer,
};
