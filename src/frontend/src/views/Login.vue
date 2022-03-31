<template>
  <div class="col-md-12">
    <div class="card card-container">
      <img
        id="profile-img"
        src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
        class="profile-img-card"
      />
      <form name="form" @submit.prevent="handleLogin">
        <div class="form-group">
          <label for="username">ID</label>
          <input
            v-model="user.username"
            v-validate="'required'"
            type="text"
            class="form-control"
            name="username"
          />
          <div
            v-if="errors.has('username')"
            class="alert alert-danger"
            role="alert"
          >Username is required!</div>
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input
            v-model="user.password"
            v-validate="'required'"
            type="password"
            class="form-control"
            name="password"
          />
          <div
            v-if="errors.has('password')"
            class="alert alert-danger"
            role="alert"
          >Password is required!</div>
        </div>
        <div class="form-group">
          <button class="btn btn-primary btn-block" :disabled="loading">
            <span v-show="loading" class="spinner-border spinner-border-sm"></span>
            <span>로그인</span>
          </button>
        </div>
        <div class="form-group">
          <div v-if="message" class="alert alert-danger" role="alert">{{ message }}</div>
        </div>
      </form>
      <div class="social-form">
        <a class="social-title">다른 계정으로 로그인</a>
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
import User from '../models/user';
import Constant from '../constant'
import fbLogo from '../img/fb-logo.png';
import googleLogo from '../img/google-logo.png';
import githubLogo from '../img/github-logo.png';

export default {
  name: 'Login',
  data() {
    return {
      user: new User('', ''),
      snsuser: new User('', ''),
      loading: false,
      message: '',
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
  },
  methods: {
    handleLogin() {
      this.loading = true;
      this.$validator.validateAll().then(isValid => {
        if (!isValid) {
          this.loading = false;
          return;
        }

        if (this.user.username && this.user.password) {
          this.$store.dispatch('auth/login', this.user).then(
            () => {
              this.$router.push('/profile');
            },
            error => {
              this.loading = false;
              this.message =
                (error.response && error.response.data.message) ||
                error.message ||
                error.toString();
            }
          );
        }
      });
    },
    bindingSocialUserInfo(socailUserInfo, type) {
      if (type === 'google') {
        this.snsuser.username = socailUserInfo.getBasicProfile().getEmail();
        this.snsuser.password = socailUserInfo.getBasicProfile().getEmail();
        this.snsuser.email = socailUserInfo.getBasicProfile().getEmail();
        this.snsuser.principal = socailUserInfo.getAuthResponse().id_token;
        this.snsuser.user_nickname = socailUserInfo.getBasicProfile().getGivenName() + socailUserInfo.getBasicProfile().getFamilyName();
        this.snsuser.social_type = 'google';
      }
      return this.snsuser;
    }
  }
};
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}

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

.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}

.sns-icon-wrapper {
  position: absolute;
  margin-top: -7px;
  margin-left: -13px;
  width: 50px;
  height: 40px;
  border-radius: 2px;
  background-color: #fff;
}

.sns-icon {
  position: absolute;
  margin-top: 5px;
  margin-left: -15px;
  width: 30px;
  height: 30px;
}
.social-group {
  display: flex;
  border-top: 1px solid rgba(0, 0, 0, 0.125);
}
.social-group form {
  margin-left: 50px;
}
.social-title {
  font-size: 14px;
  margin-top: 30px;
  margin-bottom: 10px;
  text-align: center;
  font-weight: bold;
}
.social-form {
  height: 90px;
  text-align: center;
  border: 1px solid rgba(0, 0, 0, 0.125);
}
</style>