export default function authHeader() {
  let result = {};
  const user = JSON.parse(localStorage.getItem('user'));
  if (user && user.accessToken) {
    result = { 'Authorization': `Bearer ${user.accessToken}` };  
  }
  return result;
}