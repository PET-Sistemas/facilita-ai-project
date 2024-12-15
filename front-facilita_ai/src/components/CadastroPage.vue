<template>
  <div class="blue-side">
    <div class="container">
      <div class="full-forms">
        <div id="title">Cadastrar</div>
        <div class="name-field">
          <label for="name">Nome completo</label>
          <input type="text" id="name" placeholder="" v-model="name" />
        </div>

        <div class="double-field">
          <div class="data-field">
            <label for="datanascimento">Data de nascimento</label>
            <input type="date" name="datanascimento" class="data" id="datanascimento" required v-model="birthdate" placeholder="DD/MM/AAAA">
          </div>
      
          <div class="phone-field">
            <label for="phone">Telefone</label>
            <input type="tel" id="phone" class="phone-input" placeholder="(XX) XXXXX-XXXX" v-model="phone"/>
          </div>
        </div>

        <div class="double-field">
          <div class="state-field">
            <label for="state">Estado</label>
            <select id="state" class="state" v-model="selectedState" @change="fetchCities">
              <option value="">UF</option>
              <option v-for="state in states" :key="state.sigla" :value="state.sigla">
                {{ state.nome }}
              </option>
            </select>
          </div>
          <div class="city-field">
            <label for="city">Cidade</label>
            <select id="city" class="city" v-model="selectedCity">
              <option value="">Cidade</option>
              <option v-for="city in cities" :key="city.nome" :value="city.nome">
                {{ city.nome }}
              </option>
            </select>
          </div>
        </div>

        <div class="email-field">
          <label for="email">E-mail</label>
          <input type="email" id="email" placeholder="" v-model="email" />
        </div>

        <div class="double-field">
          <div class="password-field">
            <label for="password">Senha</label>
            <input type="password" id="password" placeholder="" v-model="password" />
          </div>
          <div class="confirmation">
            <label for="confirmation">Confirmar senha</label>
            <input type="password" id="confirmation" placeholder="" v-model="confirmation" />
          </div>
        </div>

        <div class="form-check form-switch">
          <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault" v-model="sellProduct" />
          <label class="form-check-label" for="flexSwitchCheckDefault">Vender produto</label>
        </div>

        <div class="button-sign">
          <!-- Agora, ao clicar, o submitForm será chamado -->
          <button id="button-sign2" @click="submitForm">CADASTRAR</button>
        </div>

        <div class="login_text">
          Já possui uma conta?
          <router-link to="/" class="login_link">Entrar</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      name: '',
      birthdate: '',
      phone: '',
      selectedState: '',
      selectedCity: '',
      email: '',
      password: '',
      confirmation: '',
      sellProduct: false,
      states: [],
      cities: []
    };
  },
  mounted() {
    this.fetchStates();
  },
  methods: {
    async fetchStates() {
      try {
        const response = await axios.get('https://servicodados.ibge.gov.br/api/v1/localidades/estados');
        this.states = response.data;
      } catch (error) {
        console.error('Erro ao buscar estados:', error);
      }
    },
    async fetchCities() {
      try {
        if (this.selectedState) {
          const response = await axios.get(`https://servicodados.ibge.gov.br/api/v1/localidades/estados/${this.selectedState}/municipios`);
          this.cities = response.data;
        }
      } catch (error) {
        console.error('Erro ao buscar cidades:', error);
      }
    },
    async submitForm() {
      // Verificar se as senhas são iguais
      if (this.password !== this.confirmation) {
        alert('As senhas não coincidem.');
        return;
      }

      // Preparar os dados do usuário no formato esperado pelo backend
      const userData = {
        nomeCompleto: this.name,
        dataNascimento: this.birthdate,
        endereco: 'Rua das Flores, 123',  // Ajuste conforme o campo necessário
        cidade: this.selectedCity,
        uf: this.selectedState,
        email: this.email,
        telefone: this.phone,
        senha: this.password
      };

      try {
          const response = await axios.post('http://localhost/api/usuario', userData);
          console.log('Resposta completa:', response); // Adicione este log
          
          if (response.status === 200 || response.status === 201) {
              alert('Cadastro realizado com sucesso!');
              console.log('Dados salvos:', response.data); // Adicione este log
          } else {
              alert('Erro ao cadastrar usuário.');
          }
      } catch (error) {
          console.error('Erro detalhado:', error.response); // Modificado para mostrar mais detalhes
          alert('Ocorreu um erro ao cadastrar o usuário.');
      }
    }
  }
};
</script>

<style scoped>

input::placeholder {
  color: #999;
}
.form-check {
  display: flex;
  align-items: center;
  margin-top: 1.7rem !important;
  margin-right: 4.5rem !important;
}

.form-check-label {
  font-size: 1.3em !important;
  margin-right: 1rem;
}

.form-check-input {
  width: 3.5rem;
  height: 1.75rem;
  background-color: rgba(206, 206, 206, 1);
  border-radius: 1rem;
  position: relative;
  appearance: none;
  outline: none;
  cursor: pointer;
  transition: background-color 0.3s;
  margin-left: 6.5%;
  margin-right: 1em;
}

.form-check-input:checked {
  background-color: rgba(242, 101, 48, 1);
}

.form-check-input::before {
  content: '';
  position: absolute;
  top: 0.125rem;
  left: 0.125rem;
  width: 1.5rem;
  height: 1.5rem;
  background-color: white;
  border-radius: 50%;
  transition: transform 0.3s;
}

.form-check-input:checked::before {
  transform: translateX(1.75rem);
}

.container {
    margin-top: 50px;
    margin-bottom: 10px;
    margin-right: 15%;
    margin-left: 15%;
    display: flex;
    justify-content: center;
    align-items: center;
}

.blue-side {
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    height: 100vh;
    background: linear-gradient(
        to left bottom,
        #024A59 0%,
        #024A59 0%,
        #024A59 0%,
        #024A59 0%,
        #024A59 0%,
        #024A59 35%,
        #024A59 49%,
        #024A59 54%,
        #067057 86%,
        #068852 100%,
        #348a09 100%,
        #9e940c 100%,
        #F2E206 100%
    );
}

.full-forms{
    overflow-y: scroll;
    width: 90%;
    height: 90vh;
    border-radius: 20px;
    box-shadow: 10px 10px 25px 0px rgba(0, 0, 0, 0.5);
    font-family: 'Crete Round';
    background-color: white;
}

#title{
    margin-left: 3.5rem;
    margin-top: 2.5rem;
    margin-right:2.5rem;
    margin-bottom: 1rem;
    font-size: 2.3em;
    font-weight: bold;
}

.email-field{
    margin-top: 1.3rem;
    margin-left: 3.5rem;
    margin-right: 5.5rem;
    color: #3E3E3E;
    font-size: 1.2em;   
}

.double-field{
    width: 100% !important;
    display: flex !important;
}

.data-field{
    margin-top: 1.3rem !important; 
    margin-left: 3.5rem !important;
    margin-right: 5.5rem !important;
    color: #3E3E3E !important;
    font-size: 1.3em !important;  
}

.state-field{
    margin-top: 1.3rem !important; 
    margin-left: 3.5rem ;
    margin-right: 5.5rem !important;
    color: #3E3E3E !important;
    font-size: 1.3em !important;   
}

.phone-field{
    display: flex !important;
    flex-direction: column !important;
    margin-top: 1.3rem !important; 
    margin-left: 3.5rem !important;
    margin-right: 5.5rem !important;
    color: #3E3E3E !important;
    font-size: 1.3em !important;   
}

.city-field{
    margin-top: 1.3rem !important; 
    margin-left: 3.5rem !important;
    margin-right: 5.5rem !important;
    color: #3E3E3E !important;
    font-size: 1.3em !important;    
}

.phone{
    display: block !important;
    margin-top: 0.5rem !important;
    padding: 0.25rem !important;
    height: 3.5rem !important;
    width: 12.5rem !important;
    border-radius: 10px !important;
    border: 0.1rem solid #C0C0C0 !important;
    box-sizing: border-box !important;
    background-color: #FFF !important;
    font-size: 0.9em !important;
}

.phone-input {
  width: 80%;
  padding: 0.25rem !important;
  height: 3.5rem !important;
  border-radius: 10px !important;
  border: 0.1rem solid #C0C0C0 !important;
  box-sizing: border-box !important;
  background-color: #FFF !important;
  font-size: 0.9em !important;
}
.phone-input::placeholder {
    color: #888;
}


.state{
    display: block !important;
    margin-top: 0.5rem !important;
    padding: 0.25rem !important;
    height: 3.5rem !important;
    width: 12.5rem !important;
    border-radius: 10px !important;
    border: 0.1rem solid #C0C0C0 !important;
    box-sizing: border-box !important;
    background-color: #FFF !important;
    font-size: 0.9em !important;
}

.city{
    display: block !important;
    margin-top: 0.5rem !important;
    padding: 0.25rem !important;
    height: 3.5rem !important;
    width: 12.5rem !important;
    border-radius: 10px !important;
    border: 0.1rem solid #C0C0C0 !important;
    box-sizing: border-box !important;
    background-color: #FFF !important;
    font-size: 0.9em !important;
}

.data{
    display: block;
    margin-top: 0.5rem !important;
    padding: 0.25rem !important;
    height: 3.5rem !important;
    width: 12.5rem !important;
    border-radius: 10px !important;
    border: 0.1rem solid #C0C0C0 !important;
    box-sizing: border-box !important;
    background-color: #FFF !important;
    font-size: 0.9em !important;
}

#email{
    display: block;
    margin-top: 0.5rem;
    padding: 0.25rem;
    height: 3.5rem;
    width: 100%;
    border-radius: 10px;
    border: 0.1rem solid #C0C0C0;
    box-sizing: border-box;
    background-color: #FFF ;
    font-size: 0.9em;
}

.name-field{
    margin-top: 3.3rem;
    margin-left: 3.5rem;
    margin-right: 5.5rem;
    color: #3E3E3E;
    font-size: 1.3em;   
}

#name{
    display: block;
    margin-top: 0.5rem;
    padding: 0.25rem;
    height: 3.5rem;
    width: 100%;
    border-radius: 10px;
    border: 0.1rem solid #C0C0C0;
    box-sizing: border-box;
    background-color: #FFF ;
    font-size: 0.9em;
}

.password-field{
    position: relative;
    margin-top: 1.3rem;
    margin-left: 3.5rem;
    margin-right: 2.5%;
    border-radius: 10px;
    color: #3E3E3E;
    font-size: 1.3em;
}

#password{
    display: block;
    margin-top: 0.3em;
    padding: 0.25rem;
    width: 22rem;
    height: 3.5rem;
    border-radius: 10px;
    border: 0.1rem solid #C0C0C0;
    box-sizing: border-box;
    background-color: #FFF ;
    font-size: 0.9em;
}

.confirmation{
    position: relative;
    margin-top: 1.3rem;
    margin-left: 3.5rem;
    margin-right: 7.5rem;
    border-radius: 10px;
    color: #3E3E3E;
    font-size: 1.3em;
}

#confirmation{
    display: block;
    margin-top: 0.4rem;
    padding: 0.25rem;
    width: 22rem;
    height: 3.5rem;
    border-radius: 10px;
    border: 0.1rem solid #C0C0C0;
    box-sizing: border-box;
    background-color: #FFF ;
}

a{
    text-decoration: none;
    color: #1570EF;
    margin-top: 5.7rem;
    font-family: 'Inter';
}

a:visited{
    color: #1570EF;
}


.button-sign2{
    align-items: center;
}

#button-sign2{
    width: 50%;
    margin-top: 2.5em;
    margin-left:25%;
    padding: 0.25rem;
    background-color: #F26530;
    height: 3.2rem;
    border-radius: 7px;
    color: #FFF;
    font-weight: bold;
    font-size: 1.5em;
    cursor: pointer;
    border: none;
}


.login_text, a{
    margin-top: 0.5rem;
    font-family: 'Inter';
    font-size: 1.1em;
    color: #3E3E3E;
    text-align: center;
    margin-bottom: 3%;
}

.select2-container--default .select2-selection--single {
    background-color: #FFF;
    border: 0.1rem solid #C0C0C0;
    border-radius: 10px;
    height: 3.5rem;
    box-shadow: none;
    font-size: 0.9em;
    margin-top: 0.5rem;
    margin-left: 3.5rem;
    margin-right: 5.5rem;
}

footer {
    margin-top: 2em;
    padding: 1em;
    background-color: #024A59;
    color: white;
    font-family: 'Crete Round';
    font-size: 0.9em;
    text-align: center;
}

footer a {
    color: white;
}

footer a:hover {
    text-decoration: underline;
}

#password_icon{
    position: absolute;
    right: 1.25rem;
    top: 4.35rem;
    cursor: pointer;
}

#password_icon_confirmation{
    position: absolute;
    right: 1.25rem;
    top: 4.35rem;
    cursor: pointer;
}

#file-directory {
    display: inline-block;
    margin-left: 3.5rem;
    margin-top: 1.3rem;
    font-size: 1.2em;
    color: #3E3E3E;
}

#file-button {
    margin: auto;
    background-color: #F26530;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

#generate-button {
    display: block;
    margin: 2rem auto;
    padding: 0.75rem 2rem;
    background-color: #F26530;
    color: white;
    border: none;
    border-radius: 7px;
    font-size: 1.3em;
    cursor: pointer;
}

#digit-size {
    display: inline-block;
    width: 3rem;
    margin-left: 3.5rem;
    margin-top: 1.3rem;
    padding: 0.25rem;
    border-radius: 5px;
    border: 0.1rem solid #C0C0C0;
    box-sizing: border-box;
    background-color: #FFF;
}

#filter-options {
    display: inline-block;
    margin-left: 1rem;
    margin-top: 1.3rem;
}

#status-message {
    display: block;
    margin-top: 1.5rem;
    text-align: center;
    font-size: 1.2em;
    color: #3E3E3E;
}

#file-size-input {
    display: inline-block;
    margin-left: 1rem;
    width: 3rem;
    padding: 0.25rem;
    border-radius: 5px;
    border: 0.1rem solid #C0C0C0;
    box-sizing: border-box;
    background-color: #FFF;
}

</style>