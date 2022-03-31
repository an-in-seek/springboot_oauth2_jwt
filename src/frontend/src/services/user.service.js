import axios from 'axios';
import authHeader from './auth-header';
import Constant from '../constant'

const API_URL = Constant.API_BASE_URL + '/api/users';

class UserService {

    getUser() {
        return axios.get(`${API_URL}`, { headers: authHeader() });
    }
}

export default new UserService();
