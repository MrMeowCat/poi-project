import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";
import { MapComponent } from "./map.component";
import { mapRouter } from "./map.router";
import { SidebarComponent } from './sidebar/sidebar.component';
import { SidebarThemesComponent } from './sidebar/sidebar-themes/sidebar-themes.component';
import { SidebarPointsComponent } from './sidebar/sidebar-points/sidebar-points.component';
import { SidebarProfileComponent } from './sidebar/sidebar-profile/sidebar-profile.component';
import { SidebarSettingsComponent } from './sidebar/sidebar-settings/sidebar-settings.component';
import { SharedModule } from "../shared/shared.module";
import { FormsModule } from "@angular/forms";
import { TranslateModule } from "@ngx-translate/core";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    TranslateModule,
    RouterModule,
    SharedModule,

    mapRouter
  ],
  declarations: [
    MapComponent,
    SidebarComponent,
    SidebarThemesComponent,
    SidebarPointsComponent,
    SidebarProfileComponent,
    SidebarSettingsComponent
  ]
})
export class MapModule {
}
