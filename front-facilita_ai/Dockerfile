# Use a imagem oficial do Node.js como base
FROM node:18

# Defina o diretório de trabalho
WORKDIR /app

# Copie o package.json e o package-lock.json
COPY package*.json ./

# Instale as dependências
RUN npm install
RUN npm install -g @vue/cli
RUN npm install axios

# Copie o restante da aplicação
COPY . .

# Construa a aplicação
RUN npm run build

# Exponha a porta em que o aplicativo será executado
EXPOSE 3001

# Defina o comando para iniciar a aplicação
CMD ["npm", "run", "serve"]
