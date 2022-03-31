<template>
    <div></div>
</template>
<script>
import Constant from '../constant'
export default {
    name: 'OAuth2RedirectHandler',
    created() {
        this.render();
    },
    methods: {
        getUrlParameter(key) {
            key = key.replace(/[[]/, '\\[').replace(/[\]]/, '\\]');
            const regex = new RegExp('[\\?&]' + key + '=([^&#]*)');
            const results = regex.exec(location.search);
            return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
        },
        render() {
            const accessToken = this.getUrlParameter('token');
            if (accessToken) {
                localStorage.setItem(Constant.USER, JSON.stringify({ accessToken }));
                location.href = "/profile";
            } else {
                const error = this.getUrlParameter('error');
                console.log(error);
                this.$router.push('/login');
            }
        }
    }
}
</script>
