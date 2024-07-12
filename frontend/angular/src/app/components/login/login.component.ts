import { Component } from '@angular/core';
import {AvatarModule} from "primeng/avatar";
import {InputTextModule} from "primeng/inputtext";
import {ButtonDirective} from "primeng/button";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    AvatarModule,
    InputTextModule,
    ButtonDirective
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

}
