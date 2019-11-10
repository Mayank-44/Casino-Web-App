import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  // event variable coming from Body Component
  @Input()
  error: boolean;

  constructor() { }

  ngOnInit() {
  }

}
