import { ActionReducerMap } from "@ngrx/store";
import { ThemeChangeAction } from "./actions";
import { State } from "./states";
import { DefaultThemes } from "../shared/models/default-themes";
import { Theme } from "../shared/models/theme";

export function themeChangeReducer(state: Theme = DefaultThemes.Standard, action: ThemeChangeAction)
  : Theme {
  return action.payload || state;
}

export const reducerMap: ActionReducerMap<State> = {
  theme: themeChangeReducer
};
