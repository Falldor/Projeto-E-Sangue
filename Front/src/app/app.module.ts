import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

// IMPORTS ANGULAR MATERIAL
import {MatTableModule} from '@angular/material/table';
import { HeaderComponent } from './components/template/header/header.component';
import {MatMenuModule} from '@angular/material/menu';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import { NavComponent } from './components/template/nav/nav.component';
import { HomeComponent } from './components/view/home/home.component';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatCardModule} from '@angular/material/card';
import { ViewDonatorComponent } from './components/view/view-donator/view-donator.component';
import { ViewPatientComponent } from './components/view/view-patient/view-patient.component';
import { CadastroPacienteComponent } from './components/view/cadastro/cadastro-paciente/cadastro-paciente.component';
import { CadastroDoadorComponent } from './components/view/cadastro/cadastro-doador/cadastro-doador.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatRadioModule} from '@angular/material/radio';
import {MatDialogModule} from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { DeleteDonatorComponent } from './components/view/delete/delete-donator/delete-donator.component';
import { DeletePatientComponent } from './components/view/delete/delete-patient/delete-patient.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { UpdatePatientComponent } from './components/view/update/update-patient/update-patient.component';
import { UpdateDonatorComponent } from './components/view/update/update-donator/update-donator.component';
import { DoacaoComponent } from './components/view/doacao/doacao.component';
import { MatIconModule } from '@angular/material/icon';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    NavComponent,
    HomeComponent,
    ViewDonatorComponent,
    ViewPatientComponent,
    CadastroPacienteComponent,
    CadastroDoadorComponent,
    DeleteDonatorComponent,
    DeletePatientComponent,
    UpdatePatientComponent,
    UpdateDonatorComponent,
    DoacaoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatMenuModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatCardModule,
    MatFormFieldModule,
    MatRadioModule,
    MatDialogModule,
    MatInputModule,
    FormsModule,
    MatAutocompleteModule,
    HttpClientModule,
    MatSnackBarModule,
    MatIconModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [CadastroDoadorComponent, CadastroPacienteComponent],

})
export class AppModule { }
