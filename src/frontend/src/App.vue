<template>
    <div id="app">
        <b-navbar toggleable="lg" type="dark" variant="dark" fixed="top">
            <b-navbar-brand href="href" @click.prevent>Community</b-navbar-brand>
            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav="is-nav">
                <b-navbar-nav>
                    <li class="nav-item">
                        <router-link to="/home" class="nav-link">
                            <font-awesome-icon icon="home" />메인
                        </router-link>
                    </li>
                </b-navbar-nav>

                <b-navbar-nav>
                    <li class="nav-item">
                        <router-link v-if="currentUser" to="/boards" class="nav-link">
                            <font-awesome-icon icon="list" />게시판
                        </router-link>
                    </li>
                </b-navbar-nav>

                <b-navbar-nav class="ml-auto" v-if="!currentUser">
                    <li class="nav-item">
                        <router-link to="/login" class="nav-link">
                            <font-awesome-icon icon="sign-in-alt" />로그인
                        </router-link>
                    </li>
                </b-navbar-nav>

                <b-navbar-nav class="ml-auto" v-if="currentUser">
                    <li class="nav-item">
                        <router-link to="/profile" class="nav-link">
                            <font-awesome-icon icon="user" />프로필
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="href" @click.prevent="logOut">
                            <font-awesome-icon icon="sign-out-alt" />로그아웃
                        </a>
                    </li>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>

        <div class="container pt-60">
            <router-view />
        </div>
    </div>
</template>

<script>
export default {
    computed: {
        currentUser() {
            return this.$store.state.auth.user;
        }
    },
    methods: {
        logOut() {
            this.$store.dispatch('auth/logout');
            this.$router.push('/login');
        }
    }
};
</script>
<style>
.pt-60 {
    padding-top: 60px;
}
</style>