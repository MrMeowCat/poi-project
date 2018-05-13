import { Injectable } from '@angular/core';
import { AuthService } from "./auth.service";
import { HttpService } from "./http.service";
import { Observable } from "rxjs/Observable";
import { Theme } from "../models/theme";
import { ApiUrls } from "../utils/urls";

@Injectable()
export class ThemeService {

  constructor(private http: HttpService,
              private authService: AuthService) {

  }

  getCurrentTheme(): Observable<Theme> {
    const options = this.http.getDefaultOptions();
    options.withCredentials = true;
    this.authService.setCsrfHeader(options);
    return this.http.get<Theme>(ApiUrls.CURRENT_THEME_URL, options).map(theme => {
      theme.custom = true;
      return theme;
    });
  }

  getTheme(id: string): Observable<Theme> {
    const options = this.http.getDefaultOptions();
    options.withCredentials = true;
    this.authService.setCsrfHeader(options);
    return this.http.get<Theme>(ApiUrls.THEMES_URL + "/" + id, options).map(theme => {
      theme.custom = true;
      return theme;
    });
  }

  getThemes(withDefault = false): Observable<Theme[]> {
    const options = this.http.getDefaultOptions();
    options.withCredentials = true;
    this.authService.setCsrfHeader(options);
    return this.http.get<Theme[]>(ApiUrls.THEMES_URL, options).map(themes => {
      themes.forEach(theme => {
        theme.custom = true;
      });
      return themes;
    });
  }

  createTheme(theme: Theme): Observable<Theme> {
    const options = this.http.getDefaultOptions();
    options.withCredentials = true;
    this.authService.setCsrfHeader(options);
    return this.http.post(ApiUrls.THEMES_URL, theme, options);
  }

  updateTheme(theme: Theme, change = false): Observable<any> {
    const options = this.http.getDefaultOptions();
    options.withCredentials = true;
    options.params = {
      change
    };
    this.authService.setCsrfHeader(options);
    return this.http.put(ApiUrls.THEMES_URL, theme, options);
  }

  deleteTheme(id: string): Observable<any> {
    const options = this.http.getDefaultOptions();
    options.withCredentials = true;
    this.authService.setCsrfHeader(options);
    return this.http.delete(ApiUrls.THEMES_URL + "/" + id, options);
  }
}
