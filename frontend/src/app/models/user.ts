export class User {
  id?: number;
  nome!: string;
  email!: string;
  senha!: string;

  constructor(init?: Partial<User>) {
    Object.assign(this, init);
  }
}