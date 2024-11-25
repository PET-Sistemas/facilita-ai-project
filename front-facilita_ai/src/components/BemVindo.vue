<template>
  <div class="background-default">
    <div class="blue-side">
      <div class="logo">
        <img src="./icons/logo.png" alt="Logo">
      </div>
      <div class="welcome-text">
        Olá.<br />
        Bem-Vindo de Volta! <br />
      </div>
    </div>
  </div>
</template>

<style scoped>
body {
  margin: 0 !important;
}
.background-default {
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: flex-start;
  align-items: flex-start;
  background-color: transparent;
}

.blue-side {
  width: 55vw; 
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
  position: relative;
  padding: 1rem; 
}

.logo img {
  width: 100px;
  height: auto;
  position: absolute;
  top: 20px;
  left: 20px;
}

.welcome-text {
  position: absolute;
  bottom: 30px;
  left: 30px;
  font-family: 'crete round', serif;
  font-size: 3.5em;
  line-height: 1em;
  color: white;
  margin-bottom: 1em;
}
</style>
