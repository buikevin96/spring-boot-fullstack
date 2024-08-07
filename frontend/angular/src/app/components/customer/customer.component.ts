import { Component } from '@angular/core';
import {MenuBarComponent} from "../menu-bar/menu-bar.component";
import {HeaderBarComponent} from "../header-bar/header-bar.component";
import {ButtonDirective} from "primeng/button";
import {SidebarModule} from "primeng/sidebar";
import {ManageCustomerComponent} from "../manage-customer/manage-customer.component";

@Component({
  selector: 'app-customer',
  standalone: true,
  imports: [
    MenuBarComponent,
    HeaderBarComponent,
    ButtonDirective,
    SidebarModule,
    ManageCustomerComponent
  ],
  templateUrl: './customer.component.html',
  styleUrl: './customer.component.scss'
})
export class CustomerComponent {

  display: false;

}
