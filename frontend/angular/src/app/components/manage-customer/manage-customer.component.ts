import { Component } from '@angular/core';
import {InputTextModule} from "primeng/inputtext";
import {ButtonDirective} from "primeng/button";

@Component({
  selector: 'app-manage-customer',
  standalone: true,
  imports: [
    InputTextModule,
    ButtonDirective
  ],
  templateUrl: './manage-customer.component.html',
  styleUrl: './manage-customer.component.scss'
})
export class ManageCustomerComponent {

}
