<template>
    <div>
        <div class="card card-container mt-4">
            <b-form v-if="showMainForm">
                <b-form-group id="boardNoGroup" :label="lblBoardNoGroup" label-for="id">
                    <b-form-input id="id" v-model="board.id" readonly></b-form-input>
                </b-form-group>

                <b-form-group id="usernameGroup" :label="lblUsernameGroup" label-for="username">
                    <b-form-input id="username" v-model="board.user.username" readonly></b-form-input>
                </b-form-group>

                <b-form-group
                    id="createdDateGroup"
                    :label="lblCreatedDateGroup"
                    label-for="createdDate"
                >
                    <b-form-input id="createdDate" v-model="board.createdDate" readonly></b-form-input>
                </b-form-group>

                <b-form-group id="titleGroup" :label="lblTitleGroup" label-for="title">
                    <b-form-input
                        id="title"
                        required="required"
                        v-model="board.title"
                        :placeholder="phTitle"
                        :readonly="formReadonly"
                    ></b-form-input>
                </b-form-group>

                <b-form-group id="boardTypeGroup" :label="lblBoardTypeGroup" label-for="boardType">
                    <b-form-select
                        id="boardType"
                        required="required"
                        v-model="board.boardType"
                        :options="boardTypes"
                        disabled
                    ></b-form-select>
                </b-form-group>

                <b-form-group id="ContentGroup" :label="lblContentGroup" label-for="content">
                    <b-form-textarea
                        id="content"
                        rows="6"
                        max-rows="6"
                        v-model="board.content"
                        :placeholder="phContent"
                        :readonly="formReadonly"
                    ></b-form-textarea>
                </b-form-group>

                <b-row>
                    <b-col lg="12" class="pb-1" v-if="showCreateButton">
                        <b-button
                            block
                            size="lg"
                            variant="primary"
                            @click="handleCreate"
                        >{{ btnCreate }}</b-button>
                    </b-col>
                    <b-col lg="6" class="pb-1" v-if="showUpdateButton">
                        <b-button
                            block
                            size="lg"
                            variant="primary"
                            @click="handleUpdate"
                        >{{ btnUpdate }}</b-button>
                    </b-col>
                    <b-col lg="6" class="pb-1" v-if="showDeleteButton">
                        <b-button
                            block
                            size="lg"
                            variant="danger"
                            @click="handleDelete"
                        >{{ btnDelete }}</b-button>
                    </b-col>
                </b-row>
            </b-form>
        </div>
    </div>
</template>

<script>
import Board from '../models/board';
import BoardService from '../services/board.service';
import ErrorHandler from '../services/error-handler';
export default {
    name: 'Board',
    data() {
        return {
            // show 상태값
            showMainForm: true,
            showCreateButton: true,
            showUpdateButton: true,
            showDeleteButton: true,
            formReadonly: true,

            // To Do: 추후 DB에서 다국어 값으로 가져오는 방법으로 변경 필요
            lblBoardNoGroup: '번호:',
            lblUsernameGroup: '작성자:',
            lblCreatedDateGroup: '작성일:',
            lblTitleGroup: '제목:',
            lblBoardTypeGroup: '타입:',
            lblContentGroup: '내용:',
            phTitle: '제목을 입력하세요.',
            phContent: '내용을 입력하세요.',
            btnCreate: '등록',
            btnUpdate: '수정',
            btnDelete: '삭제',

            // 데이터 세팅
            board: new Board(),

            // To Do: 추후 DB에서 코드 데이터로 가져오는 방법으로 변경 필요
            boardTypes: [
                {
                    text: '자유게시판',
                    value: 'FREE'
                }
            ]
        };
    },
    mounted() {
        this.formReadonly = false;
        this.showCreateButton = false;
        this.showUpdateButton = false;
        this.showDeleteButton = false;
        const currentUser = this.$store.state.auth.user;
        const id = Number(this.$route.params.id);
        if (id) {
            // 수정
            BoardService.getBoardById(id).then(response => {
                Object.assign(this.board, response.data);
                if (currentUser.id == this.board.user.id) {
                    this.showUpdateButton = true;
                    this.showDeleteButton = true;
                } else {
                    this.formReadonly = true;
                }
            }, error => {
                alert(error);
            });
        } else {
            // 등록
            this.showCreateButton = true;
            this.board.user.id = currentUser.id;
            this.board.user.username = currentUser.username;
            this.board.boardType = 'FREE';
        }
    },
    methods: {
        // 등록
        handleCreate(evt) {
            evt.preventDefault()
            BoardService
                .createBoard(this.board)
                .then(response => {
                    if (response.status !== 201) {
                        alert("Failed");
                        return;
                    }
                    this.$router.push({ path: '/boards' });
                }, error => {
                    ErrorHandler.handleUnauthorizedError(error, this);
                });
        },
        // 수정
        handleUpdate(evt) {
            evt.preventDefault()
            BoardService
                .updateBoard(this.board, this.$moment)
                .then(response => {
                    if (response.status !== 200) {
                        alert("Failed");
                        return;
                    }
                    this.$router.push({ path: '/boards' });
                }, error => {
                    ErrorHandler.handleUnauthorizedError(error, this);
                });
        },
        // 삭제
        handleDelete(evt) {
            evt.preventDefault()
            BoardService
                .deleteBoard(this.board.id)
                .then(response => {
                    if (response.status !== 204) {
                        alert("Failed");
                        return;
                    }
                    this.$router.push({ path: '/boards' });
                }, error => {
                    ErrorHandler.handleUnauthorizedError(error, this);
                });
        }
    }
};
</script>

<style scoped>
label {
    display: block;
    margin-top: 5px;
}

.card-container.card {
    padding: 20px 20px;
}

.card {
    background-color: #f7f7f7;
    padding: 20px 25px 30px;
    margin: 0 auto 25px;
    -moz-border-radius: 2px;
    -webkit-border-radius: 2px;
    border-radius: 2px;
    -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}
</style>