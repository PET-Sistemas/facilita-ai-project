<template>
  <div class="main-div">
    <BemVindo class="bem-vindo" />
    <LoginPage class="login-page" />
  </div>
</template>

<script>
import LoginPage from '../components/LoginPage.vue';
import BemVindo from '../components/BemVindo.vue';

export default {
  name: 'HomePage',
  components: {
    BemVindo,
    LoginPage 
  }
};
</script>

<style scoped>

html, body, #app {
  height: 100%;
  width: 100%;
  margin: 0;
  padding: 0;
  overflow: hidden;
}

.main-div {
  display: flex;
  flex-direction: row;
  height: 100%;
  width: 100%;
  overflow: hidden;
}

.bem-vindo {
  flex: 0 0 55vw; 
  height: 100%;
}

.login-page {
  flex: 0 0 45vw; 
  height: 100%;
}

@media screen and (max-width: 600px) {
  .main-div {
    flex-direction: column;
  }

  .login-page {
    width: 100%;
    margin-top: 0;
  }
}
</style>