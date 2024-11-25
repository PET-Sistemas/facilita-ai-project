<template>
  <header>
    <HeaderPage />
  </header>
  
  <body>
    <div class="profile-container">
      <div class="profile-card">
        <div class="picture-container">
          <img 
            :src="user.profilePicture" 
            alt="Foto do Usuário" 
            class="profile-picture" 
          />
          <div v-if="isEditing">
            <label for="profilePictureUpload" class="button"><img src="./icons/lapis.png"></label>
            <input 
              id="profilePictureUpload" 
              type="file" 
              accept="image/*" 
              @change="uploadProfilePicture"
              style="display: none;" 
            />
          </div>
        </div>
      
        <div class="profile-header">
          <div class="title">{{ user.name }}</div>
          <p class="type">Prestador de Serviço</p>
          
          <div class="stars">
            <span 
              v-for="n in user.stars" 
              :key="n" 
              class="star filled">★</span>
            <span 
              v-for="n in (5 - user.stars)" 
              :key="n + 5" 
              class="star">★</span>
          </div>

          <div class="profile-info">
            <h3>Dados</h3>
            <div v-if="!isEditing">
              <p><strong>E-mail:</strong> {{ user.email }}</p>
              <p><strong>Telefone:</strong> {{ user.phone }}</p>
              <p><strong>Localização:</strong> {{ user.location }}</p>
              <button @click="toggleEdit">Editar Dados</button>
            </div>
            <div v-else>
              <label for="email">E-mail:</label>
              <input type="email" id="email" v-model="user.email" />

              <label for="phone">Telefone:</label>
              <input type="text" id="phone" v-model="user.phone" />

              <label for="location">Localização:</label>
              <input type="text" id="location" v-model="user.location" />

              <button @click="toggleEdit">Salvar Dados</button>
              <button @click="cancelEdit">Cancelar</button>
            </div>
          </div>
        </div>
      </div>   
      
      <center>
        <div class="service-card">
          <div class="services">
            <h3>Serviços</h3>
            <div class="carousel">
              <div class="carousel-track-container">
                <button class="carousel-button prev" @click="prevSlide">&lt;</button>
                <div class="carousel-track" :style="{ transform: trackTransform }">
                  <img 
                    v-for="(image, index) in images" 
                    :key="index" 
                    :src="image" 
                    alt="Serviço" 
                    class="carousel-image" />
                </div>
                <button class="carousel-button next" @click="nextSlide">&gt;</button>
              </div>
            </div>
          </div>
        </div>
      </center>
    </div>
  </body>
</template>
  
<script>
  import HeaderPage from '../components/HeaderPage.vue';
  import userData from '../assets/teste.json';
  import axios from 'axios';
  export default {
    name: 'ProfilePage',
    components: {
      HeaderPage,
    },
    data() {
      return {
        user: {}, // Dados do usuário
        originalUser: {}, // Para reverter alterações
        isEditing: false, // Controle de edição
        images: [
          "https://via.placeholder.com/800x400",
          "https://via.placeholder.com/800x400",
          "https://via.placeholder.com/800x400"
        ],
        currentIndex: 0,
      };
    },    
    created() {
      // Carregar dados do JSON ao iniciar o componente
      this.user = { ...userData };
      this.originalUser = { ...userData };
    },
    computed: {
      trackTransform() {
        const imageWidth = 100; // Cada imagem ocupa 100% da largura
        return `translateX(-${this.currentIndex * imageWidth}%)`;
      }
    },
      methods: {
        async saveData() {
          try {
            const response = await axios.put('http://localhost:3000/user', this.user); // Salvar no backend
            this.originalUser = { ...this.user }; // Atualiza os dados locais
            this.isEditing = false;
            alert(response.data.message); // Confirmação do backend
          } catch (error) {
            console.error('Erro ao salvar os dados:', error);
            alert('Erro ao salvar os dados.');
          }
        },

        toggleEdit() {
          if (this.isEditing) {
            // Salvar alterações
            this.originalUser = { ...this.user };
          }
          this.isEditing = !this.isEditing;
        },
        cancelEdit() {
          // Reverter alterações
          this.user = { ...this.originalUser };
          this.isEditing = false;
        },
        nextSlide() {
            if (this.currentIndex < this.images.length - 1) {
              this.currentIndex++;
            }
          },
        prevSlide() {
          if (this.currentIndex > 0) {
            this.currentIndex--;
          }
        },
        uploadProfilePicture(event) {
          const file = event.target.files[0];
          if (file) {
            const reader = new FileReader();
            reader.onload = (e) => {
              this.user.profilePicture = e.target.result; // Carregar a imagem como base64 temporariamente
            };
            reader.readAsDataURL(file);
          }
        }
      }
  };
</script>
  
<style>
  html, body {
    height: 100%;
    margin: 0;
  }

  body {
    overflow-y: scroll; /* Força a barra de rolagem vertical */
  }

  ::-webkit-scrollbar {
    width: 0px;  /* Para barra de rolagem vertical */
    height: 0px; /* Para barra de rolagem horizontal */
  }

  .profile-container {
    background: linear-gradient(1.87deg, rgba(2, 74, 89, 0.3) 0%, rgba(0, 0, 0, 0) 100%);
    height: 100%;
    width: 100%;
  }
  
  .profile-card {
    display: flex;
    justify-content: center; 
    align-items: center; 
  }

  .picture-container {
    height: auto;
    width: 20%;
    justify-content: center;
    align-items: center;
  }

  .profile-header {
    margin: 5%;
  }

  .title {
    font-size: 2.1em;
    font-weight: bold;
    font-family: 'crete round', serif;
  }

  .type {
    font-family: 'crete round', serif;
    font-size: 1.3em;
  }

  h3 {
    font-family: 'crete round', serif;
  }

  .profile-picture {
    width: 300px; /* Largura fixa */
    height: 300px; /* Altura fixa */
    border-radius: 50%; /* Para um formato circular */
    object-fit: cover; /* Ajusta a imagem para preencher o espaço sem distorção */
    box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2); /* Opcional: sombra */
}

  .profile-info {
    padding: 25px;
    background-color: white;
    border-radius: 10px;
    box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2); /* Sombra */
    width: 500px;
    height: auto;
  }

  .services {
    padding: 25px;
    background-color: white;
    border-radius: 10px;
    box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2); /* Sombra */
    width: 900px;
    height: auto;
  }

  .service-card {
    justify-content: center; 
    align-items: center; 
  }

  .carousel {
    position: relative;
    width: 80%;
    max-width: 800px;
    overflow: visible; /* Permite que os botões fiquem fora do contêiner */
    margin: 0 auto; /* Centraliza o carrossel */
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  }

  .carousel-track-container {
    overflow: hidden;
    width: 100%;
  }

  .carousel-track {
    display: flex;
    transition: transform 0.5s ease-in-out;
  }

  .carousel-image {
    width: 100%;
    flex: 0 0 100%;
  }

  .carousel-button {
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    background-color: white;
    color: #F26530;
    border: none;
    font-size: 2rem;
    cursor: pointer;
    z-index: 10;
    border-radius: 50%;
  }

  /* Ajustando os botões para fora da imagem */
  .carousel-button.prev {
    left: -50px; /* Move o botão para fora à esquerda */
  }

  .carousel-button.next {
    right: -50px; /* Move o botão para fora à direita */
  }

  .carousel-button:focus {
    outline: none;
  }

  .stars {
    display: flex;
    margin-bottom: 10%;
  }

  .star {
    font-size: 2rem;
    color: #ccc; /* Cor padrão */
  }

  .star.filled {
    color: #F26530; /* Cor das estrelas preenchidas */
  }

  input {
    width: 100%;
    padding: 10px;
    margin: 5px 0;
    border-radius: 5px;
    border: 1px solid #ccc;
  }

  button {
    margin: 5px;
    background-color: #F26530;
    padding: 5px;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }

</style>
