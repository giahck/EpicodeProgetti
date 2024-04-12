import { User } from './../interface/user.interface';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Post } from '../interface/post.interface';
import { AngularFireDatabase } from '@angular/fire/compat/database';
import { Observable, map } from 'rxjs';
import { log } from 'console';

@Injectable({
  providedIn: 'root',
})

export class PostService {

  constructor(private http: HttpClient, private db: AngularFireDatabase) { }

  loaclGet() {
    const user = localStorage.getItem('user') ?? '';
    if (user) {
      let utente = JSON.parse(user);
      return {uId:utente.uid, nomeUtente:utente.displayName}
    } else {
      return null; // o return {};
    }
  }

  addPost(post: Post) {
    const user = localStorage.getItem('user') ?? '';
    const utente = JSON.parse(user);
    return this.db.list('post').push(post);
  }

  getPost(): Observable<Post[]> {
    return this.db.list('post').snapshotChanges().pipe(
      map(changes =>
        changes.map(c => {
          const data = c.payload.val() as Post;
          const postId = c.payload.key;
          return { postId, ...data };
        })
      )
    );
  }
  updateData(key: string, data: any): Promise<void> {
    return this.db.object(`post/${key}`).update(data);
  }
  deletePost(idPost: string): Promise<void> {
    return this.db.list('post').remove(idPost);
  }
}
