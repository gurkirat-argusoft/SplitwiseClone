// Converted to class
export class User {
    constructor(
        public userId: number,
        public userName: string,
        public email: string,
        public phoneNo: string,
        public password:string,
        public imageUrl?: string // Optional property
    ) {}
}