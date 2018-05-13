export class ApiUrls {

  static readonly BASE_PATH = "http://localhost:8080/api/v1";

  static readonly LOGIN_URL = ApiUrls.BASE_PATH + "/login";
  static readonly SIGN_UP_URL = ApiUrls.BASE_PATH + "/signUp";
  static readonly LOGOUT_URL = ApiUrls.BASE_PATH + "/logout";
  static readonly IS_AUTHENTICATED_URL = ApiUrls.BASE_PATH + "/isAuthenticated";

  static readonly USERS_URL = ApiUrls.BASE_PATH + "/users";
  static readonly CURRENT_USER_URL = ApiUrls.BASE_PATH + "/currentUser";
  static readonly SET_LOCALE_URL = ApiUrls.BASE_PATH + "/setLocale";
  static readonly SET_AVATAR_URL = ApiUrls.BASE_PATH + "/avatar";

  static readonly CURRENT_THEME_URL = ApiUrls.BASE_PATH + "/currentTheme";
  static readonly THEMES_URL = ApiUrls.BASE_PATH + "/themes";
}

export class Locations {

  static readonly ASSETS_PATH = "assets";
  static readonly MARKER_1_PATH = "assets/img/markers/marker_1.png";
  static readonly MARKER_2_PATH = "assets/img/markers/marker_2.png";
  static readonly MARKER_3_PATH = "assets/img/markers/marker_3.png";
  static readonly MARKER_4_PATH = "assets/img/markers/marker_4.png";

}
