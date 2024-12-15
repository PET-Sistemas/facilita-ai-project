const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})
module.exports = {
  devServer: {
    port: 3001,
    proxy: {
      '/api': {
        target: 'http://151.106.108.50:8080',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ''), // Remove '/api' ao redirecionar
      },
  },
}  
}
