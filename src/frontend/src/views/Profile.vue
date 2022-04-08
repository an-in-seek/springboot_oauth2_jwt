<template>
  <div>
    <header class="jumbotron mt-4">
      <h3>
        <strong>{{ currentUser.username }}</strong> Profile
      </h3>
    </header>
    <div>
      <p>
        <strong>Image:</strong>
      </p>
      <img :src="currentUser.imageUrl" />
    </div>
    <p class="token">
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
export default {
  name: 'Profile',
  data() {
    return {
      currentUser: new User()
    }
  },
  mounted() {
    this.currentUser = this.$store.state.auth.user;
  }
};
</script>

<style scoped>
.token {
  word-break: break-all;
}
</style>