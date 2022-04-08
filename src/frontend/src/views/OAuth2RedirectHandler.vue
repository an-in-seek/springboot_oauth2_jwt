<template>
    <br />
</template>
<script>
import Constant from '../constant'
import User from '../models/user';
import UserService from '../services/user.service';
export default {
    name: 'OAuth2RedirectHandler',
    data() {
        return {
            currentUser: new User()
        }
    },
    created() {
        this.validateAccessToken();
    },
    methods: {
        getUrlParameter(key) {
            key = key.replace(/[[]/, '\\[').replace(/[\]]/, '\\]');
            const regex = new RegExp('[\\?&]' + key + '=([^&#]*)');
            const results = regex.exec(location.search);
            return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
        },
        validateAccessToken() {
            const accessToken = this.getUrlParameter('token');
            if (accessToken) {
                localStorage.setItem(Constant.USER, JSON.stringify({ accessToken }));
                UserService.getUser()
                    .then(
                        response => {
                            Object.assign(this.currentUser, response.data);
                            this.currentUser.accessToken = accessToken;
                            localStorage.setItem(Constant.USER, JSON.stringify(this.currentUser));
                            location.href = "/profile";
                        },
                        error => {
                            alert.log(error);
                        }
                    );
            } else {
                const error = this.getUrlParameter('error');
                alert.log(error);
                this.$router.push('/login');
            }
        }
    }
}
</script>
