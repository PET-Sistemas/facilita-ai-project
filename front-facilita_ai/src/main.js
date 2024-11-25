import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import VueTheMask from 'vue-the-mask'; 


const app = createApp(App).use(router);

app.use(router);
app.use(VueTheMask);  

app.mount('#app');

