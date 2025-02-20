# Etapa de Build
FROM eclipse-temurin:21-jdk-alpine as builder
WORKDIR /app

# Instala dependências necessárias para o Maven Wrapper funcionar
RUN apk add --no-cache bash

COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Etapa de Execução
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 4001
ENTRYPOINT ["java", "-jar", "app.jar"]