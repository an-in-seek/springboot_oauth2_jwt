<template>
  <div>
    <header class="jumbotron mt-4">
      <h3>
        <strong>{{ currentUser.username }}</strong> Profile
      </h3>
    </header>
    <p>
      <strong>Token:</strong>
      {{ currentUser.accessToken }}
    </p>
    <p>
      <strong>Id:</strong>
      {{ currentUser.id }}
    </p>
    <p>
      <strong>Name:</strong>
      {{ currentUser.username }}
    </p>
    <p>
      <strong>SocialType:</strong>
      {{ currentUser.socialType }}
    </p>
    <p>
      <strong>Email:</strong>
      {{ currentUser.email }}
    </p>
    <strong>Authorities:</strong>
    <ul>
      <li v-for="(role, index) in currentUser.roles" :key="index">{{ role }}</li>
    </ul>
  </div>
</template>

<script>
import User from '../models/user';
import UserService from '../services/user.service';
export default {
  name: 'Profile',
  data() {
    return {
      currentUser: new User()
    }
  },
  mounted() {
    // 엑세스 토큰으로 사용자 정보 조회
    UserService.getUser()
      .then(
        response => {
          Object.assign(this.currentUser, response.data);
          this.currentUser.accessToken = this.$store.state.auth.user.accessToken;
          if (!this.currentUser) {
            this.$router.push('/login');
          }
        },
        error => {
          alert((error.response && error.response.data.message) || error.message || error.toString());
        }
      );
  }
};
</script>