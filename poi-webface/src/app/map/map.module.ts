import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";
import { MapComponent } from "./map.component";
import { mapRouter } from "./map.router";

@NgModule({
  imports: [
    CommonModule,
    RouterModule,

    mapRouter
  ],
  declarations: [
    MapComponent
  ]
})
export class MapModule {
}
