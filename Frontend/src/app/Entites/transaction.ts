export class Transaction {
    transactionId: number;
    giverId: number;
    receiverId: number;
    amount: number;
    doneAt: string; // Use string to represent LocalDateTime
    eventId?: number; // Optional
    description?: string; // Optional

    constructor(
        transactionId: number,
        giverId: number,
        receiverId: number,
        amount: number,
        doneAt: string,
        eventId?: number,
        description?: string
    ) {
        this.transactionId = transactionId;
        this.giverId = giverId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.doneAt = doneAt;
        this.eventId = eventId;
        this.description = description;
    }
}