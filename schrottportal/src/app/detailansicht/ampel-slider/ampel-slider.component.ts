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

    switch (this.value) {
      case '0':
        this.color = 'red';
        break;

      case '1':
        this.color = 'orange';
        break;

      case '2':
        this.color = 'yellow';
        break;

      case '3':
        this.color = 'green';
        break;

      case '4':
        this.color = 'blue';
        break;
    }
  }

}
