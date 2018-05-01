import { Injectable } from '@angular/core';
import { AuthService } from "./auth.service";
import { HttpService } from "./http.service";
import { Observable } from "rxjs/Observable";
import { Theme } from "../models/theme";
import { Urls } from "../utils/urls";
import { StyledTheme } from "../models/styled-themes";

@Injectable()
export class ThemeService {

  constructor(private http: HttpService,
              private authService: AuthService) {

  }

  getCurrentTheme(): Observable<Theme> {
    const options = this.http.getDefaultOptions();
    options.withCredentials = true;
    this.authService.setCsrfHeader(options);
    return this.http.get(Urls.CURRENT_THEME_URL, options);
  }

  getTheme(id: string): Observable<Theme> {
    const options = this.http.getDefaultOptions();
    options.withCredentials = true;
    this.authService.setCsrfHeader(options);
    return this.http.get(Urls.THEMES_URL + "/" + id, options);
  }

  getThemes(): Observable<Theme[]> {
    const options = this.http.getDefaultOptions();
    options.withCredentials = true;
    this.authService.setCsrfHeader(options);
    return this.http.get(Urls.THEMES_URL, options);
  }

  createTheme(theme: Theme): Observable<Theme> {
    const options = this.http.getDefaultOptions();
    options.withCredentials = true;
    this.authService.setCsrfHeader(options);
    return this.http.post(Urls.THEMES_URL, theme, options);
  }

  updateTheme(theme: Theme): Observable<any> {
    const options = this.http.getDefaultOptions();
    options.withCredentials = true;
    this.authService.setCsrfHeader(options);
    return this.http.put(Urls.THEMES_URL, theme, options);
  }

  deleteTheme(id: string): Observable<any> {
    const options = this.http.getDefaultOptions();
    options.withCredentials = true;
    this.authService.setCsrfHeader(options);
    return this.http.delete(Urls.THEMES_URL + "/" + id, options);
  }

  convertTheme(theme: Theme): StyledTheme {
    const style = JSON.parse(theme.style);
    return {
      name: theme.name,
      theme: new google.maps.StyledMapType(style)
    }
  }
}
