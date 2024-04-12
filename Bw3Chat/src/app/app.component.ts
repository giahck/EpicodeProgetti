import { Component, OnInit } from '@angular/core';
import { PostService } from './models/post.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'buildweek';

constructor(private postSrv: PostService) {}
local:any

ngOnInit(): void {
  if(this.postSrv.loaclGet()){
    this.local = this.postSrv.loaclGet();
  }

}

}
