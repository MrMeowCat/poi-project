import { ActionReducerMap } from "@ngrx/store";
import { ThemeChangeAction } from "./actions";
import { State } from "./states";
import { StyledTheme, StyledThemes } from "../shared/models/styled-themes";

export function themeChangeReducer(state: StyledTheme = StyledThemes.Standard, action: ThemeChangeAction)
  : StyledTheme {
  return action.payload || state;
}

export const reducerMap: ActionReducerMap<State> = {
  theme: themeChangeReducer
};
