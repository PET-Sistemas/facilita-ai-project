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
  <nav class="sidebar">
    <div class="filter-section">
      <!-- Filtro por Tipo de Serviço -->
      <div class="filter-container">
        <h3>TIPO DE SERVIÇO</h3>
        <div>
          <label>
        <input type="checkbox" value="limpeza" v-model="selectedServices"> Limpeza
          </label>
          <label>
        <input type="checkbox" value="construcao" v-model="selectedServices"> Construção
          </label>
          <label>
        <input type="checkbox" value="manutencao" v-model="selectedServices"> Manutenção
          </label>
          <label>
        <input type="checkbox" value="eletrica" v-model="selectedServices"> Elétrica
          </label>
          <label>
        <input type="checkbox" value="hidraulica" v-model="selectedServices"> Hidráulica
          </label>
          <label>
        <input type="checkbox" value="pintura" v-model="selectedServices"> Estética
          </label>
        </div>
      </div>

      <!-- Filtro por Distância -->
      <div class="filter-container">
        <h3>DISTÂNCIA: {{ distance }} km</h3>
        <input
          type="range"
          v-model="distance"
          min="0"
          max="50"
          step="1"
          @input="updateSlider($event, 'distance')"
          :style="getSliderBackground(distance, 50)"
        />
      </div>

      <!-- Filtro por Preço -->
      <div class="filter-container">
        <h3>PREÇO: R$ {{ formatPrice(price) }}</h3>
        <input
          type="range"
          v-model="price"
          min="0"
          max="3000"
          step="50"
          @input="updateSlider($event, 'price')"
          :style="getSliderBackground(price, 3000)"
        />
      </div>
    </div>
  </nav>
  
</template>


  
<script>
export default {
  data() {
    return {
      address: 'Endereço Atual', 
      newAddress: '', 
      isEditing: false,
      serviceType: '',
      distance: 25,
      price: 500
    };
  },
  watch: {
    serviceType() {
      this.filterResults();
    },
    distance() {
      this.filterResults();
    },
    price() {
      this.filterResults();
    }
  },
  methods: {
    getSliderBackground(value, max) {
      const percentage = (value / max) * 100;
      return {
        '--value': percentage + '%',
      };
    },
    updateSlider(event, type) {
      if (type === 'distance') {
        this.distance = parseInt(event.target.value);
      } else {
        this.price = parseInt(event.target.value);
      }
    },
    filterResults() {
      console.log('Filtrando por:', this.serviceType, this.distance, this.price);
    },
    formatPrice(price) {
      return price.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
    },
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
  display: flex;
  justify-content: space-between;
  padding: 10px 20px;
  height: 7rem;
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
  margin-right: 2rem;
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

.filter-section {
  display: flex;
  flex-direction: column;
  gap: 25px;
  font-family: 'inter', sans-serif;
  font-size: 15px;
}

.filter-container {
  background-color: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 10px 10px 10px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  align-items: center;
}

input[type="range"] {
  -webkit-appearance: none;
  appearance: none;
  width: 100%;
  height: 6px;
  border-radius: 5px;
  background: transparent; /* Fundo transparente */
  outline: none;
  position: relative; /* Necessário para o alinhamento */
}

input[type="range"]::-webkit-slider-runnable-track {
  height: 6px;
  background: #ccc; /* Cor da linha */
  border-radius: 5px;
  position: relative; /* Necessário para o alinhamento */
}

input[type="range"]::-webkit-slider-thumb {
  -webkit-appearance: none;
  appearance: none;
  width: 15px;
  height: 15px;
  background: #024A59;
  border-radius: 50%;
  cursor: pointer;
  position: relative;
  top: -4.5px; /* Ajuste para centralizar o thumb */
  z-index: 2; /* Garante que o thumb fique sobre o track */
}

input[type="range"]::-moz-range-track {
  height: 6px;
  background: #ccc; /* Cor da linha */
  border-radius: 5px;
}

input[type="range"]::-moz-range-thumb {
  width: 15px;
  height: 15px;
  background: #024A59;
  border-radius: 50%;
  cursor: pointer;
  position: relative;
  z-index: 2; /* Garante que o thumb fique sobre o track */
}

input[type="range"]::-ms-track {
  width: 100%;
  height: 6px;
  background: transparent; /* Necessário para o track no IE */
  border-color: transparent;
  color: transparent;
}

input[type="range"]::-ms-fill-lower {
  background: #024A59; /* Cor preenchida */
  border-radius: 5px;
}

input[type="range"]::-ms-fill-upper {
  background: #ccc; /* Cor além do valor do slider */
  border-radius: 5px;
}

/* Adiciona gradiente ao fundo da linha com base no valor do slider */
input[type="range"]::-webkit-slider-runnable-track {
  background: linear-gradient(
    to right,
    #024A59 0%,
    #024A59 var(--value, 0%),
    #ccc var(--value, 0%),
    #ccc 100%
  );
}

input[type="range"]::-moz-range-track {
  background: linear-gradient(
    to right,
    #024A59 0%,
    #024A59 var(--value, 0%),
    #ccc var(--value, 0%),
    #ccc 100%
  );
}

</style>
