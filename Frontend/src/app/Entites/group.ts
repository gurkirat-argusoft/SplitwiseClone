import { User } from "./user";

export class Group {
    constructor(
        public groupId: number,
        public groupName: string,
        public imageUrl?: string ,// Optional property
        public users?: Set<User> // Optional property to hold users
    ) {}
}