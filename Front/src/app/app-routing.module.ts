import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroDoadorComponent } from './components/view/cadastro/cadastro-doador/cadastro-doador.component';
import { DeletePatientComponent } from './components/view/delete/delete-patient/delete-patient.component';
import { HomeComponent } from './components/view/home/home.component';
import { ViewDonatorComponent } from './components/view/view-donator/view-donator.component';
import { ViewPatientComponent } from './components/view/view-patient/view-patient.component';
import { DeleteDonatorComponent } from './components/view/delete/delete-donator/delete-donator.component';
import { UpdatePatientComponent } from './components/view/update/update-patient/update-patient.component';
import { UpdateDonatorComponent } from './components/view/update/update-donator/update-donator.component';
import { DoacaoComponent } from './components/view/doacao/doacao.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },


  {
    path: 'listarDoadores',
    component: ViewDonatorComponent
  },

  {
    path: 'listarPacientes',
    component: ViewPatientComponent
  },

  {
    path: 'listarDoadores/delete/:id',
    component: DeleteDonatorComponent
  },

  {
    path: 'listarDoadores/update/:id',
    component: UpdateDonatorComponent
  },

  {
    path: 'listarPacientes/delete/:id',
    component: DeletePatientComponent
  },

  {
    path: 'listarPacientes/update/:id',
    component: UpdatePatientComponent
  }, 
  {
    path: 'registraDoacao',
    component: DoacaoComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
