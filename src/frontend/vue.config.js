const path = require('path');
// const url = 'http://localhost:8080';
const url = 'http://seek316.duckdns.org';

module.exports = {
  // outputDir: path.resolve(__dirname, '../' + 'main/resources/static'),
  devServer: {
    proxy: {
      '/api': {
        target: url,
        ws: true,
        changeOrigin: true
      },
      '/upload': {
        target: url,
        ws: true,
        changeOrigin: true
      }
    }
  }
};
