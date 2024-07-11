import {NgModule} from "@angular/core";
import {AppComponent} from "./app.component";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {AppRoutingModule} from "./app-routing.module";
import {InputTextModule} from "primeng/inputtext";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule
    AppRoutingModule,
    FormsModule,
    InputTextModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule{}
