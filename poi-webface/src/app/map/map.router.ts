import { RouterModule, Routes } from "@angular/router";
import { MapComponent } from "./map.component";

const routes: Routes = [
  { path: '', component: MapComponent },
];

export const mapRouter = RouterModule.forChild(routes);
