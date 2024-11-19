export class Post{
    id: number | undefined;
    title!: string;
    content!: string;
    orgNo!: string;
    createdDate!: string;
    updatedDate!: string;
    status: boolean = true;
}

export class Transaction{
    id: number | undefined;
    accountNumber!: string;
    trxAmount!: number;
    description!: string;
    trxDateTime!: string;
    customerId!: string;
}