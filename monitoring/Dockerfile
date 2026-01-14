# --- Aşama 1: Build (Maven ile derleme) ---
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Testleri atlayarak paketle (Hız kazandırır)
RUN mvn clean package -DskipTests

# --- Aşama 2: Run (Çalıştırma) ---
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
# İlk aşamada oluşan .jar dosyasını al
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]