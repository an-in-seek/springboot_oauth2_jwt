class ErrorHandler {

    handleUnauthorizedError(error, $this){
        alert(error);
        if (error.response.status === 401) {
            $this.$store.dispatch('auth/logout');
            $this.$router.push('/login');
        }
    }
}

export default new ErrorHandler();