<template>
    <header>
      <router-link to = "/">
      <img src="./icons/logo.png" alt="Logo Facilita aí" id="logo">
      </router-link>
      <div class="main-content">
        <div class="search-input">
          <div class="search-container">
            <input type="text" id="search" placeholder="Descreva o que precisa...">
            <img src="./icons/lupa.png" alt="Search Icon" class="search-icon">
          </div>
        </div>
        <div class="address-section">
          <div class="address-text" @click="toggleEdit">  
            <span>{{ address }}</span>
            <svg class="arrow-icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24" fill="#ffffff">
              <path d="M12 15.41L16.59 10.83 18 12.24 12 18.24 6 12.24 7.41 10.83 12 15.41z"/>
            </svg>
          </div>
          <div v-if="isEditing" class="address-edit">
            <input 
              v-model="newAddress" 
              type="text" 
              placeholder="Digite o novo endereço..."
              @keyup.enter="saveAddress"
              ref="addressInput"
            >
          </div>
        </div>
      </div>
      <div class="header-right">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="40" height="40" fill="#ffffff">
          <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.33-8 4v2h16v-2c0-2.67-5.33-4-8-4z"/>
        </svg>
      </div>
    </header>  
</template>

<script>
  export default {
    data() {
      return {
        address: 'Endereço Atual', 
        newAddress: '', 
        isEditing: false, 
      };
    },
    methods: {
      toggleEdit() {
        this.isEditing = !this.isEditing;
        if (this.isEditing) {
          this.$nextTick(() => {
            this.$refs.addressInput.focus(); 
          });
        }
      },
      saveAddress() {
        if (this.newAddress.trim()) {
          this.address = this.newAddress;
          this.newAddress = '';
          this.isEditing = false;
        }
      }
    }
  };
</script>


<style scoped>

header {
  position:relative;
  display: flex;
  justify-content: space-between;
  padding: 10px 20px;
  height: 7rem;
  width: 100%;
  background: linear-gradient(
    to left,
    #024A59 0%,
    #024A59 35%,
    #067057 100%,
    #068852 100%
  );
}

.main-content {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.search-container {
  position: relative;
  display: flex;
  align-items: center;
}

#search {
  padding-right: 40px; /* Espaço para o ícone */
}

.search-icon {
  position: absolute;
  padding-top: 25px;
  right: 10px;
  width: 20px;
  height: 20px;
}

.search-input {
  margin-bottom: 20px;
}

.header-right {
  display: flex;
  align-items: center;
}

#logo {
  margin-left: 3rem;
  width: 100px;
  height: auto;
}

svg {
  cursor: pointer;
  margin-right: 5rem;
}

#search {
  margin-top: 1.8rem;
  display: flex;
  padding: 12px;
  border-radius: 5px;
  border: none;
  font-size: 1rem;
  width: 50rem;
}

.address-section {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.address-text {
  display: flex;
  align-items: center;
  cursor: pointer;
  color: #ffffff;
}

.arrow-icon {
  margin-left: 8px;
}

.address-edit input {
  padding: 8px;
  border-radius: 5px;
  border: none;
  font-size: 1rem;
  margin-bottom: 10px;
  max-width: 400px;
}

.sidebar {
  width: 250px;
  height: 100vh;
  padding: 20px;
  display: flex;
  flex-direction: column;
}


</style>
