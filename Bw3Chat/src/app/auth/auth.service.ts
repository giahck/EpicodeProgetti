import { Injectable } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { Router } from '@angular/router';
import firebase from 'firebase/compat/app';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
 

  constructor(private fireAuth: AngularFireAuth,private router:Router) { }

  login(email:string, password:string): Promise<void>{
    return this.fireAuth.signInWithEmailAndPassword(email, password).then( () => {
      const token = {
        displayName: firebase.auth().currentUser?.displayName,
        uid:firebase.auth().currentUser?.uid,
        fotoUrl: firebase.auth().currentUser?.photoURL,
      };
      const tokenString = JSON.stringify(token)
      
      localStorage.setItem('user', `${tokenString}`)
      console.log(token)
      return
    }, err => {
      alert('Something went wrong')
      return
    }
  )
  
}

signUp(email:string, password:string,fullName:string, foto:string): Promise<void> {
 return this.fireAuth.createUserWithEmailAndPassword(email, password)
  .then((userCredential) => {
      let users = firebase.auth().currentUser;
     
      users!.updateProfile({
        displayName: fullName,
        photoURL: foto
        
      }).then((elm)=> {
       
        
      }).catch(function(error) {
        // An error happened.
      });
    })
}

logout(){
this.fireAuth.signOut().then(() => {
  localStorage.removeItem('user')
  this.router.navigate(['login'])
},err => {
  alert(err.message)
})
}
}
