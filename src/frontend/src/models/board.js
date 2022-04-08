export default class Board {
    constructor(id, username, title, content, boardType, createdDate, updatedDate) {
      this.id = id;
      this.user = { username }
      this.title = title;
      this.content = content;
      this.boardType = boardType;
      this.createdDate = createdDate;
      this.updatedDate = updatedDate;
    }
  }
  