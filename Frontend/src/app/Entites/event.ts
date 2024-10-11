export class Event {
  eventId: number;
  description: string;
  happenedAt: string; // Use string to match LocalDateTime format
  amount: number;
  splitType: string;
  groupId?: string; // Optional if it can be null

  constructor(
    eventId: number,
    description: string,
    happenedAt: string,
    amount: number,
    splitType: string,
    groupId?: string
  ) {
    this.eventId = eventId;
    this.description = description;
    this.happenedAt = happenedAt;
    this.amount = amount;
    this.splitType = splitType;
    this.groupId = groupId;
  }
}