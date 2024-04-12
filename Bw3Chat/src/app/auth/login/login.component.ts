import { Component, EventEmitter, Output } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  constructor(private auth : AuthService, private router: Router){}

  ngOnInit(): void {
  
    
  } 

  login(){
    if(this.email == ''){
      alert('Please enter email')
      return;
    }
    if(this.password == ''){
      alert('Please enter password')
      return;
    }
    this.auth.login(this.email, this.password).then(
      (e)=>{
        console.log(e)
        console.log('User is logged in');
        this.router.navigate(['/home'])
      },
      (err)=>{
        console.log(err)
        alert('Login failed')
      }
    );
    this.email = ''
    this.password = ''
  }
}
