# Etapa de Build
FROM eclipse-temurin:21-jdk-alpine as builder
WORKDIR /app
COPY . .  
RUN chmod +x ./mvnw
RUN ./mvnw package -DskipTests

# Etapa de Execução
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 4001
ENTRYPOINT ["java", "-jar", "app.jar"]
