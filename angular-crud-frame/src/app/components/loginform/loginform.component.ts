import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { ErrorMessageComponent } from '../error-message/error-message.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { UserService } from './../../services/user.service';


@Component({
  selector: 'app-loginform',
  standalone: true,
  imports: [CommonModule,ReactiveFormsModule,ErrorMessageComponent,MatFormFieldModule,MatInputModule,MatCardModule,MatGridListModule,MatIconModule],
  templateUrl: './loginform.component.html',
  styleUrls: ['./loginform.component.scss']
})
export class LoginformComponent {

  hide: boolean = true;
  loginForm?: any;
  constructor(private fb: FormBuilder, private authService : AuthService, private userService: UserService, private router: Router) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.compose([Validators.required,Validators.minLength(4), Validators.maxLength(8)])]
    });
  }

  onSubmit(): void {
    console.log("submit");
    console.log(this.loginForm?.valid);
    console.log(this.loginForm);
    if (this.loginForm.valid) {
      console.log("valid");
      const username = this.loginForm.get('username').value;
      const password = this.loginForm.get('password').value;

      this.userService.loginUser(username, password).subscribe({
        next: (response: any) => {
          console.log("resp", response);
        //   localStorage.setItem(this.authSecretKey, data.token);
        // this.isAuthenticated = true;
        // Call the authentication service's login method
       if (this.authService.login(response)) {
        // Navigate to the ProductListComponent upon successful login
        this.router.navigate(['dashboard']);
      }
        console.log("success");
        return true;
        },
        error: (err: any) => {
          console.log("not success");
          console.log(err);
          return false;
        },
      });

    }
  }
}
