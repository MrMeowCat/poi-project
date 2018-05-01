import { ActionReducerMap } from "@ngrx/store";
import { ThemeChangeAction } from "./actions";
import { StyledTheme, StyledThemes } from "../models/styled-themes";
import { State } from "./states";

export function themeChangeReducer(state: StyledTheme = StyledThemes.Standard, action: ThemeChangeAction): StyledTheme {
  return action.payload || state;
}

export const reducerMap: ActionReducerMap<State> = {
  theme: themeChangeReducer
};
