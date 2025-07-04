import { Component } from '@angular/core';
import {NgClass} from '@angular/common';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-ampel-slider',
  imports: [
    NgClass,
    FormsModule
  ],
  templateUrl: './ampel-slider.component.html',
  styleUrl: './ampel-slider.component.css'
})
export class AmpelSliderComponent {
   value: string | undefined;
   color: string | undefined;



  onSlideChange(event: any) {
    this.value = event.target.value

    switch (true) {
      case this.value =='0' || this.value == '1' || this.value == '2':
        this.color = 'red';
        break;

      case this.value == '3'|| this.value == '4' || this.value == '5' :
        this.color = 'orange';
        break;

      case this.value == '6' || this.value == '7':
        this.color = 'yellow';
        break;

      case this.value == '8' || this.value == '9':
        this.color = 'green';
        break;
    }
  }

}
