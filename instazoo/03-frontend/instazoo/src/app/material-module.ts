import {NgModule} from "@angular/core";
import {MatIconModule} from "@angular/material/icon";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {MatButtonModule} from "@angular/material/button";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatMenuModule} from "@angular/material/menu";
import {MatDividerModule} from "@angular/material/divider";
import {MatCardModule} from "@angular/material/card";
import {MatDialogModule} from "@angular/material/dialog";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatTabsModule} from "@angular/material/tabs";
import {BrowserModule} from "@angular/platform-browser";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";


@NgModule({
  exports: [
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatSnackBarModule,
    MatButtonModule,
    MatToolbarModule,
    MatMenuModule,
    MatDividerModule,
    MatCardModule,
    MatDialogModule,
    MatExpansionModule,
    MatTabsModule,
    MatButtonModule,
    BrowserModule,
    MatProgressSpinnerModule,
    MatPaginatorModule
  ]
})

export  class MaterialModule {

}
