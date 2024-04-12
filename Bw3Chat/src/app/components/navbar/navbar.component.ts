import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../auth/auth.service';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent implements OnInit {

   utente:any;
   displayName!:string;
  constructor(private authSrv: AuthService){
  }
  ngOnInit(): void {
   
    const user = localStorage.getItem('user') ?? '';
    if(user){
      this.utente = JSON.parse(user);
    this.displayName= this.utente.displayName
    }
  }
logout(){
this.authSrv.logout();
}
}
