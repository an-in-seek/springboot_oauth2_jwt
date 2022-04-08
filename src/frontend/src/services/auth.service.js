class AuthService {
  logout() {
    localStorage.removeItem('user');
  }
}
export default new AuthService();
