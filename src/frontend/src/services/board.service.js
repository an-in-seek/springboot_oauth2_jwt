import axios from 'axios';
import authHeader from './auth-header';
import Constant from '../constant'

const API_URL = `${Constant.API_BASE_URL}/api/boards`;

class BoardService {

  getBoardList(){
    return axios.get(API_URL, { headers: authHeader() });
  }

  getBoardById(id) {
    return axios.get(`${API_URL}/${id}`, { headers: authHeader() });
  }

  createBoard(board) {
    delete board.id;
    delete board.createdDate;
    delete board.updatedDate;
    return axios.post(API_URL, board, { headers: authHeader() });
  }

  updateBoard(board, $moment) {
    board.createdDate = $moment(board.createdDate).format('YYYY-MM-DDTHH:MM:SS');
    board.updatedDate = $moment(board.updatedDate).format('YYYY-MM-DDTHH:MM:SS');
    board.user.createdDate = $moment(board.user.createdDate).format('YYYY-MM-DDTHH:MM:SS');
    board.user.updatedDate = $moment(board.user.updatedDate).format('YYYY-MM-DDTHH:MM:SS');
    return axios.put(`${API_URL}/${board.id}`, board, { headers: authHeader() });
  }

  deleteBoard(id) {
    return axios.delete(`${API_URL}/${id}`, { headers: authHeader() });
  }
}

export default new BoardService();
