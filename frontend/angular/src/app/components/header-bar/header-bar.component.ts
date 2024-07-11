import { Component } from '@angular/core';
import {ButtonDirective} from "primeng/button";
import {Ripple} from "primeng/ripple";
import {AvatarModule} from "primeng/avatar";
import {MenuModule} from "primeng/menu";
import {MenuItem} from "primeng/api";

@Component({
  selector: 'app-header-bar',
  standalone: true,
  imports: [
    ButtonDirective,
    Ripple,
    AvatarModule,
    MenuModule
  ],
  templateUrl: './header-bar.component.html',
  styleUrl: './header-bar.component.scss'
})
export class HeaderBarComponent {
  items: Array<MenuItem> = [
    {
      label: 'Profile',
      icon: 'pi pi-user'
    },
    {
      label: 'Settings',
      icon: 'pi pi-cog'
    },
    {
      separator: true
    },
    {
      label: 'Sign Out',
      icon: 'pi pi-sign-out'
    }
  ];

}
