<script setup>
import { RouterLink } from 'vue-router';
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import { login } from '../services/api';

const email = ref('');
const password = ref('');
const router = useRouter();

const handleLogin = async () => {
    try {
        const result = await login(email.value, password.value);
        router.push('/inicial_tela'); 
    } catch (error) {
        router.push('/inicial_tela')
        //console.error('Falha no login:', error);
    }
};
</script>

<template>
  <div class="login-container">
    <div id="title">Entrar</div>
    <div class="email-field">
      <label for="email">Email</label>
      <input type="email" id="email" v-model="email" placeholder=" "/>
    </div>
    <div class = "password-field">
      <label for="password">Senha</label>
      <input type="password" id="password" v-model="password" placeholder="">
      <RouterLink to="/recover">
        <a id="forgot-password" href="/">Esqueci a senha</a>
      </RouterLink>
    </div>
    <div class="button-login">
      <button id="button-login2" @click="handleLogin">ENTRAR</button>
    </div>
    <div class="signup_text">
      Não possui uma conta?
      <RouterLink to="sign_up" class="signup_link">Cadastre-se</RouterLink>
    </div>
  </div>
</template>

<script>


</script>



<style scoped>
body {
  margin: 0 !important;
}
.login-container {
  width: calc(100% - 60px); 
  max-width: 600px; 
  height: calc(100% - 282px); 
  max-height: 550px; 
  margin: auto; 
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  background-color: white;
  padding: 20px;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  align-items: left;
}

#title {
  font-size: 2.1em;
  margin-bottom: 1em;
  margin-top: 1.8em;
  margin-left: 0.5em;
  font-family: 'crete round', serif;
}

.email-field, .password-field {
  width: 100%;
  max-width: 500px; 
  margin-bottom: 1em;
  margin-left: 0.5em;
  font-family: 'crete round', serif;
  font-size: 1rem;
}

.email-field label, .password-field label {
  display: block;
  margin-bottom: 0.1em;
  margin-top: 0.4em;
  margin-left: 0.5em;
  font-size: 1.1em;
  

}

input[type="email"], input[type="password"] {
  width: 100%;
  padding: 1em;
  border-radius: 8px;
  border: 1px solid #ccc;
  margin-top: 0.3em;
  margin-left: 0.5em;
}

.button-login {
  margin-top: 2.5em;
  text-align: center;
}

#button-login2 {
  width: 95%;
  padding: 0.5em;
  border: none;
  background-color: #F26530;
  color: white;
  font-size: 1.2em;
  border-radius: 5px;
  cursor: pointer;
}

.signup_text {
  margin-top: 1.2em;
  text-align: center;
  font-size: 1em;
  font-family: 'inter', sans-serif;
}

.signup_link {
  color: #1570EF;
}

.password-field {
  display: flex;
  flex-direction: column;
}

#forgot-password {
  color: #1570EF;
  text-decoration: none;
  margin-top: 2rem;
  margin-left: 0.5em;
  font-family: 'inter', sans-serif;
}

@media (max-width: 600px) {
  .login-container {
    width: calc(100% - 20px); 
    height: calc(100% - 120px); 
    padding: 10px; 
  }
  
  #title {
    font-size: 1.5em; 
  }

  input[type="email"], input[type="password"] {
    padding: 0.4em; 
  }

  #button-login2 {
    font-size: 1em; 
  }

  .signup_text {
    font-size: 0.9em; 
  }

  #forgot-password {
    font-size: 0.9em; 
  }
}

@media (min-width: 601px) and (max-width: 1024px) {
  .login-container {
    width: calc(100% - 40px); 
    height: calc(100% - 200px); 
    padding: 15px; 
  }
  
  #title {
    font-size: 1.8em; 
  }

  input[type="email"], input[type="password"] {
    padding: 0.45em; 
  }

  #button-login2 {
    font-size: 1.1em; 
  }

  .signup_text {
    font-size: 1em; 
  }

  #forgot-password {
    font-size: 1em; 
  }
}

</style>
