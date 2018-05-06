import { ActionReducerMap } from "@ngrx/store";
import { ActionTypes, SidebarToggleAction, ThemeChangeAction } from "./actions";
import { State } from "./states";
import { DefaultThemes } from "../shared/models/default-themes";
import { Theme } from "../shared/models/theme";

export function themeChangeReducer(theme: Theme = DefaultThemes.Standard, action: ThemeChangeAction)
  : Theme {
  switch (action.type) {
    case ActionTypes.ThemeChange:
      return action.payload || theme;
    default:
      return theme;
  }
}

export function sidebarToggleReducer(sidebarVisible: boolean = false, action: SidebarToggleAction)
  : boolean {
  return action.type === ActionTypes.SidebarToggle ? !sidebarVisible : sidebarVisible;
}

export const reducerMap: ActionReducerMap<State> = {
  theme: themeChangeReducer,
  sidebarVisible: sidebarToggleReducer
};
