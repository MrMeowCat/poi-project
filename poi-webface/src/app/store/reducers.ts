import { ActionReducerMap } from "@ngrx/store";
import { ThemeChangeAction } from "./actions";
import { Theme, Themes } from "../models/themes";
import { State } from "./states";

export function themeChangeReducer(state: Theme = Themes.Standard, action: ThemeChangeAction): Theme {
  return action.payload || state;
}

export const reducerMap: ActionReducerMap<State> = {
  theme: themeChangeReducer
};
