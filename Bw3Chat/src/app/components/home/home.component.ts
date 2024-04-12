import { Component, OnInit } from '@angular/core';
import { PostService } from '../../models/post.service';
import { Post, PostGet } from '../../interface/post.interface';
import { User } from '../../interface/user.interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent implements OnInit {

  uid!: string;
  activeFormGet: boolean = false;
  category: string = '';
  postContent!: string;
  postTitle!: string;
  post!: Post;
  data: PostGet[]= [];
  local = this.postSrv.loaclGet()?.uId;
  constructor(private postSrv: PostService, private router: Router) { }

  ngOnInit(): void {
    this.postSrv.getPost().subscribe((data:Post[]) => {
      console.log(data);
      
      
      this.data = data as PostGet[];  
      console.log(this.data)         
    })
  }
  createPost() {
    let idUt=this.postSrv.loaclGet();
    this.post = {
      category: this.category,
      title: this.postTitle,
      description: this.postContent,
      uId: idUt?.uId,
      displayName: idUt?.nomeUtente,
    }; 
     this.postSrv.addPost(this.post);
  };

  setCategory(categoryName: string) {
    if(categoryName!==''){
      this.activeFormGet=true;
    this.category = categoryName;
    }
    else{
      this.category = 'all';
    this.activeFormGet=false;
  }
  }
  onDeletePost(idPost: string): void {
    this.postSrv.deletePost(idPost).then(() => {
      console.log('Post eliminato con successo');
    }).catch((error) => {
      console.error('Errore durante l\'eliminazione del post:', error);
    });
  }
  onEditPost(i:number){
    console.log(i);
  }
}
