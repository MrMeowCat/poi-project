import { Theme } from "../shared/models/theme";
import { User } from "../shared/models/user";

export interface State {
  authVisible: boolean,
  theme: Theme;
  themes: Theme[];
  sidebarVisible: boolean;
  sidebarTab: number;
  user: User;
}
