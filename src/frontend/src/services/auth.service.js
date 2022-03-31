import axios from 'axios';
import Constant from '../constant'

const BASE_URI = `${Constant.API_BASE_URL}/api/auth`;

class AuthService {
  register(user) {
    return axios.post(`${BASE_URI}/signup`, {
      username: user.username,
      email: user.email,
      password: user.password
    });
  }

  login(user) {
    return axios
      .post(`${BASE_URI}/signin`, {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      });
  }
  
  loginWithSocial(socialType) {
    let url = "";
    switch(socialType){
      case "google":
        url = Constant.GOOGLE_AUTH_URL;
        break;
      case "facebook":
        url = Constant.FACEBOOK_AUTH_URL;
        break;
      case "github":
        url = Constant.GITHUB_AUTH_URL;
        break;
    }
    return axios
      .get(url)
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }
        return response.data;
      });
  }  

  logout() {
    localStorage.removeItem('user');
  }
}

export default new AuthService();
