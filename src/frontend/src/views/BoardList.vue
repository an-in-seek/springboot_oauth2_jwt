<template>
<div>
  <header class="jumbotron mt-4">
    <h2>사용자 게시판</h2>
    <hr class="my-4" />
    <p>게시글을 작성할 수 있습니다.</p>
    <p class="lead">
      <b-button variant="success" size="lg" @click="handleRegister">글쓰기</b-button>
    </p>
  </header>
  <body>
    <b-row>
      <b-col lg="2" class="my-1">
        <b-form-group class="mb-0">
          <b-form-select v-model="selected" :options="filterOptions">
            <template v-slot:first>
              <option value="null">전체</option>
            </template>
          </b-form-select>
        </b-form-group>
      </b-col>

      <b-col lg="8" class="my-1">
        <b-form-group label-for="filterInput" class="mb-0">
          <b-input-group>
            <b-form-input
              v-model="filter"
              type="search"
              id="filterInput"
              placeholder="검색 키워드를 입력하세요."
            ></b-form-input>
            <b-input-group-append>
              <b-button :disabled="!filter" @click="filter = ''">Clear</b-button>
            </b-input-group-append>
          </b-input-group>
        </b-form-group>
      </b-col>

      <b-col lg="2" class="my-1">
        <b-button block="block" variant="primary" @click="handleSearch">서버조회</b-button>
      </b-col>
    </b-row>

    <div class="overflow-auto">
      <b-table
        id="user-table"
        striped="striped"
        hover="hover"
        :fields="fields"
        :items="items"
        :busy="isBusy"
        :per-page="perPage"
        :current-page="currentPage"
        :filter="filter"
        :filterIncludedFields="selected"
        caption-top
        @filtered="onFiltered"
        @row-clicked="rowClick"
      >
        <template v-slot:table-caption>글 목록</template>
        <template v-slot:table-busy>
          <div class="text-center text-danger my-2">
            <b-spinner class="align-middle"></b-spinner>
            <strong>Loading...</strong>
          </div>
        </template>
      </b-table>
      <div class="mt-3">
        <b-pagination
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
          first-number
          last-number
          align="center"
          aria-controls="user-table"
        ></b-pagination>
      </div>
    </div>
  </body>
</div>
</template>

<script>
import BoardService from '../services/board.service';
import ErrorHandler from '../services/error-handler';

export default {
  name: 'User',
  data() {
    return {
      isBusy: false,
      fields: [
        { key: 'id', label: 'NO', sortable: true },
        { key: 'title', label: '제목', sortable: true },
        { key: 'boardType', label: '타입', sortable: true },
        { key: 'user.username', label: '작성자', sortable: true },
        { key: 'createdDate', label: '작성일', sortable: true },
        { key: 'updatedDate', label: '수정일', sortable: true }
      ],
      items: [],
      totalRows: 0,
      perPage: 10,
      currentPage: 1,
      filter: null,
      selected: null
    };
  },
  mounted() {
    BoardService.getBoardList().then(
      response => {
        this.items = response.data;
        this.totalRows = response.data.length;
      },
      error => {
        ErrorHandler.handleUnauthorizedError(error, this);
      }
    );
  },
  methods: {
    onFiltered(filteredItems) {
      this.totalRows = filteredItems.length;
      this.currentPage = 1;
    },
    rowClick(item) {
      this.$router.push({
        path: `/board/${item.id}`
      });
    },
    handleRegister(evt) {
      evt.preventDefault();
      this.$router.push({
        path: `/board`
      });
    },
    handleSearch(evt) {
      evt.preventDefault();
      BoardService.getBoardList().then(
        response => {
          this.items = response.data;
          this.totalRows = response.data.length;
        },
        error => {
          ErrorHandler.handleUnauthorizedError(error, this);
        }
      );
    }
  },
  computed: {
    rows() {
      return this.totalRows;
    },
    filterOptions() {
      return this.fields.filter(f => f.sortable).map(f => { return { value: f.key, text: f.label }; })
    }
  }
};
</script>
