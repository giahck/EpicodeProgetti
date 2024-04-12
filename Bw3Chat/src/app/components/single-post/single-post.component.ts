import { Component, Input, input } from '@angular/core';
import { Post } from '../../interface/post.interface';
import { User } from '../../interface/user.interface';
@Component({
  selector: 'app-single-post',
  templateUrl: './single-post.component.html',
  styleUrl: './single-post.component.scss'
})
export class SinglePostComponent {
@Input() keypost!:Post;
@Input() user!: User;


}
