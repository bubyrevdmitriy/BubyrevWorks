import {MatIconModule} from "@angular/material/icon";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {MatMenuModule} from "@angular/material/menu";
import {MatDialogModule} from "@angular/material/dialog";
import {MatInputModule} from "@angular/material/input";
import {MatDividerModule} from "@angular/material/divider";
import {MatCardModule} from "@angular/material/card";
import {MatToolbarModule} from "@angular/material/toolbar";
import {NgModule} from "@angular/core";
import {MatExpansionModule, MatExpansionPanel} from "@angular/material/expansion";
import {MatTab, MatTabsModule} from "@angular/material/tabs";
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';

import { BrowserModule }  from '@angular/platform-browser';

import {MatPaginatorModule} from "@angular/material/paginator";

import { AppComponent } from './app.component';
import { MatFileUploadModule } from 'angular-material-fileupload';
import {MatButtonModule} from "@angular/material/button";
import { MaterialFileInputModule } from 'ngx-material-file-input'

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
    MatFileUploadModule,
    MaterialFileInputModule,
    MatProgressSpinnerModule,
    MatPaginatorModule
  ]
})

export  class MaterialModule {
}
