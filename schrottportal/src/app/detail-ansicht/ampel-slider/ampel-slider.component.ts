import {Component, Input} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {ImmobilieDTO} from '../../models/immobilie.model';

@Component({
  selector: 'app-ampel-slider',
  imports: [FormsModule],
  templateUrl: './ampel-slider.component.html',
  styleUrl: './ampel-slider.component.scss',
  standalone: true
})
export class AmpelSliderComponent {

  @Input() states!: string[];
  @Input() immobilie!: ImmobilieDTO;

}
