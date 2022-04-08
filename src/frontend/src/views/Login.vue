<template>
  <div class="col-md-12">
    <div class="card card-container">
      <div class="social-form">
        <a class="social-title">소셜 로그인</a>
        <div class="social-group">
          <a class="btn social-btn google" :href="GOOGLE_AUTH_URL">
            <img :src="googleLogo" alt="Google" style="width: 50px; height: 50px;" />
          </a>
          <a class="btn social-btn facebook" :href="FACEBOOK_AUTH_URL">
            <img :src="fbLogo" alt="Facebook" style="width: 50px; height: 50px;" />
          </a>
          <a class="btn social-btn github" :href="GITHUB_AUTH_URL">
            <img :src="githubLogo" alt="Github" style="width: 50px; height: 50px;" />
          </a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Constant from '../constant'
import fbLogo from '../img/fb-logo.png';
import googleLogo from '../img/google-logo.png';
import githubLogo from '../img/github-logo.png';

export default {
  name: 'Login',
  data() {
    return {
      GOOGLE_AUTH_URL: Constant.GOOGLE_AUTH_URL,
      FACEBOOK_AUTH_URL: Constant.FACEBOOK_AUTH_URL,
      GITHUB_AUTH_URL: Constant.GITHUB_AUTH_URL,
      fbLogo: fbLogo,
      googleLogo: googleLogo,
      githubLogo: githubLogo
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  created() {
    if (this.loggedIn) {
      this.$router.push('/profile');
    }
  }
};
</script>

<style scoped>
.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}
.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}
.social-group {
  border-top: 1px solid rgba(0, 0, 0, 0.125);
}

.social-form {
  height: 90px;
  text-align: center;
  border: 1px solid rgba(0, 0, 0, 0.125);
}
</style>