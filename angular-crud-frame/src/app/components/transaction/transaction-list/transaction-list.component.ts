import { Component } from '@angular/core';
import { Transaction } from '../../../../app/models/model-data';
import { MatTableDataSource } from '@angular/material/table';
import { PageEvent } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TransactionService } from 'src/app/services/transaction.service';

@Component({
  selector: 'app-transaction-list',
  templateUrl: './transaction-list.component.html',
  styleUrls: ['./transaction-list.component.scss']
})
export class TransactionListComponent {

  transactionList: Transaction[] = [];
  displayColumns = ["id", "accountNumber", "trxAmount", "description", "trxDateTime", "customerId", "actions"];
  transactionDataSource = new MatTableDataSource<Transaction>(this.transactionList);

  pageSizeOptions: number[] = [10, 20];
  page: number = 0;
  pageSize: number = 10;
  filterObj: any = {
    'customerId': '',
    'accountNumber': '',
    'description': ''
  };
  totalElements: number = 0;
  selectedFile: File | null = null;

  constructor(private transactionService: TransactionService, private router: Router, private snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.fetchTransaction();
  }

  fetchTransaction() {
    console.log("fetch start");
    this.transactionService.getTransactions(this.filterObj, this.page, this.pageSize).subscribe({
      next: (response: any) => {
        console.log("data blsls",response);
        if (response?.data) {
          this.transactionList = response?.data?.dataList;
          this.transactionDataSource.data = this.transactionList;
          this.totalElements = response?.data?.totalElements;
        } else {
          console.error("Unexpected API response:", response);
        }
      },
      error: (err: Error) => {
        console.error("API error:", err);
      }
    });
  }

  applyFilter() {
    this.page = 1; // Reset to the first page
    this.fetchTransaction(); // Fetch data with updated filters
  }

  changePage(event: PageEvent) {
    this.page = event.pageIndex;
    this.pageSize = event.pageSize;
    this.fetchTransaction();
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      this.selectedFile = file;

      this.uploadFile(); // Call the uploadFile method
    }
  }

  uploadFile() {
    if (!this.selectedFile) {
      return;
    }

    const formData = new FormData();
    formData.append('file', this.selectedFile);

    this.transactionService.uploadFile(formData).subscribe({
      next: (response) => {
        console.log('File uploaded successfully:', response);
        this.selectedFile = null; // Reset file
        this.showSuccessMessage('File Import successfully.');
        this.fetchTransaction();
      },
      error: (error) => {
        console.error('Error uploading file:', error);
        alert('Failed to upload file.');
      }
    });
  }

  showSuccessMessage(message: string){
    this.snackBar.open(message, 'Close', {
      duration: 10000
    })
  }
}