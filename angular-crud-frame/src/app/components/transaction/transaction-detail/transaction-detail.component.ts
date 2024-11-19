import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { Subject, takeUntil } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule, ReactiveFormsModule, FormControl } from '@angular/forms';
import { ErrorMessageComponent } from '../../error-message/error-message.component';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { TransactionService } from 'src/app/services/transaction.service';
import { Transaction } from 'src/app/models/model-data';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatNativeDateModule } from '@angular/material/core';

@Component({
  selector: 'app-transaction-detail',
  templateUrl: './transaction-detail.component.html',
  standalone: true,
  imports: [CommonModule, MatFormFieldModule, MatInputModule, MatCardModule, MatCheckboxModule, MatDatepickerModule, MatSelectModule,
    FormsModule, ReactiveFormsModule, ErrorMessageComponent, MatGridListModule, MatButtonModule, MatIconModule, MatNativeDateModule],
  styleUrls: ['./transaction-detail.component.scss']
})
export class TransactionDetailComponent {
  private subscription: Subscription | undefined;
  transactionForm!: FormGroup;
  transaction: Transaction = new Transaction();

  isLoading: boolean = false;
  private destroy$: Subject<void> = new Subject<void>();

  constructor(private transactionService: TransactionService, private formBuilder: FormBuilder,
    private router: Router, private activatedRouter: ActivatedRoute, private snackBar: MatSnackBar) {

    this.transactionForm = formBuilder.group({
      accountNumber: ['', Validators.required],
      trxAmount: ['', Validators.required],
      description: ['', Validators.required],
      trxDateTime: [''],
      customerId: ['', Validators.required]
    });
  }

  ngOnInit() {
    const id = parseInt(this.activatedRouter.snapshot.paramMap.get("id") || '');
    console.log('ID:', id); // Check if ID is correct
    if (id !== 0) {
      this.transactionService.getTransactionById(id)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (response) => {
            console.log("data", response);
            this.transaction = response?.data;
          },
          error: (err) => {
            console.error('Error fetching transaction:', err); // Handle error
          }
          //this.createdDate.setValue(new Date(this.post.createdDate));
          //this.editCreditCardForm.patchValue(this.creditCardData);
        });
    } else {
      console.log('ID is 0, no data fetched');
    }

  }

  onSave() {
    if (!this.isLoading) {
      this.isLoading = true;
      if (this.transactionForm.valid) {
        console.log(this.transaction);
        this.transactionService.saveTransaction(this.transaction)
          .pipe(takeUntil(this.destroy$))
          .subscribe({
            next: (data: any) => {
              this.goBack();
            },
            error: (err: Error) => {
              console.log("not success");
              console.log(err);
              this.showSuccessMessage(err.message);
            },
            complete: () => this.isLoading = false
          });
      }
    }

  }

  showSuccessMessage(message: string) {
    this.snackBar.open(message, 'Close', {
      duration: 10000
    })
  }

  ngOnDestory() {
    this.destroy$.next();
    this.destroy$.complete();
  }

  goBack() {
    this.router.navigate(['/transactionlist']);
  }
}
