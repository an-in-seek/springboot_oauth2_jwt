export default class User {
  constructor(id, username, email, imageUrl, principal, socialType, roles) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.imageUrl = imageUrl;
    this.principal = principal;
    this.socialType = socialType;
    this.roles = roles;
  }
}
