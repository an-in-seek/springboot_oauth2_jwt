const FRONT_BASE_URL = 'http://seek316.duckdns.org';
const API_BASE_URL = 'http://seek316.duckdns.org:8080';
const USER = "user";
const ACCESS_TOKEN = "accessToken";
const OAUTH2_REDIRECT_URI = `${FRONT_BASE_URL}/oauth2/redirect`;
const GOOGLE_AUTH_URL = `${API_BASE_URL}/oauth2/authorize/google?redirect_uri=${OAUTH2_REDIRECT_URI}`;
const FACEBOOK_AUTH_URL = `${API_BASE_URL}/oauth2/authorize/facebook?redirect_uri=${OAUTH2_REDIRECT_URI}`;
const GITHUB_AUTH_URL = `${API_BASE_URL}/oauth2/authorize/github?redirect_uri=${OAUTH2_REDIRECT_URI}`;
export default { 
    API_BASE_URL, 
    USER, 
    ACCESS_TOKEN, 
    GOOGLE_AUTH_URL, 
    FACEBOOK_AUTH_URL, 
    GITHUB_AUTH_URL 
};
