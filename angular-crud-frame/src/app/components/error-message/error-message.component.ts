import { Component,Input } from '@angular/core';
import {CommonModule} from '@angular/common';
import { AbstractControl } from '@angular/forms';
import { getValidatorErrorMessage } from './validator.utils';

@Component({
  selector: '[app-error-message]',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './error-message.component.html',
  styleUrls: ['./error-message.component.scss']
})
export class ErrorMessageComponent {

  @Input() 
  control!: AbstractControl

  @Input()
  fieldName!: String

  constructor() { }

  get errorMessage() {    
    for (const validatorName in this.control?.errors) {
        if(this.control.touched) 
          return getValidatorErrorMessage(validatorName, this.control.errors[validatorName], this.fieldName);
    }
    return null;
  }
}
