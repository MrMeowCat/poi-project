export class Urls {

  static readonly BASE_PATH = "http://localhost:8080/api/v1";

  static readonly LOGIN_URL = Urls.BASE_PATH + "/login";
  static readonly SIGN_UP_URL = Urls.BASE_PATH + "/signUp";
  static readonly LOGOUT_URL = Urls.BASE_PATH + "/logout";

  static readonly USERS_URL = Urls.BASE_PATH + "/users";
  static readonly CURRENT_USER_URL = Urls.BASE_PATH + "/currentUser";
  static readonly SET_LOCALE_URL = Urls.BASE_PATH + "/setLocale";
  static readonly SET_AVATAR_URL = Urls.BASE_PATH + "/avatar";

  static readonly CURRENT_THEME_URL = Urls.BASE_PATH + "/currentTheme";
  static readonly THEMES_URL = Urls.BASE_PATH + "/themes";
}
