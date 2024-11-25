// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';

// Importar seus componentes dinamicamente
const HomePage = () => import('../components/HomePage.vue');
const CadastroPage = () => import('../components/CadastroPage.vue');
const InicialTela = () => import('../components/InicialTela.vue');
const ProfilePage = () => import('../components/ProfilePage.vue');

// Obter a URL base a partir da variável de ambiente
const baseUrl = process.env.VUE_APP_BASE_URL || '/';

const router = createRouter({
  history: createWebHistory(baseUrl),
  routes: [
    {
      path:'/inicial_tela',
      name: 'inicial_tela',
      component: InicialTela,
    },
    {
      path: '/',
      name: 'home',
      component: HomePage,
    },
    {
      path: '/sign_up',
      name: 'sign_up',
      component: CadastroPage,
    },
    {
      path: '/profile',
      name: 'profile',
      component: ProfilePage,
    },
  ],
});

export default router;
