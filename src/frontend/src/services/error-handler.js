class ErrorHandler {

    handleUnauthorizedError(error, $this){
        alert(error.response.data.errors);
        const status = error.response.status;
        switch(status){
            case 400:
                break;
            case 401:
                $this.$store.dispatch('auth/logout');
                $this.$router.push('/login');
                break;
        }
    }
}

export default new ErrorHandler();