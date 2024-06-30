#Imagem de template que será usada para a criação da imagem do backend
FROM maven:3.8.5-openjdk-17-slim AS build

#Diretório de trabalho
WORKDIR /app

#Copia os arquivos do diretório atual para o diretório de trabalho
COPY pom.xml .
COPY src ./src

#Executa o comando para instalar as dependências do projeto
RUN mvn clean install -DskipTests

#Imagem de template que será usada para a criação da imagem do backend
FROM openjdk:17-slim

#Diretório de trabalho
WORKDIR /siad

#Copia o arquivo .jar gerado na imagem anterior para o diretório de trabalho
COPY --from=build /app/target/desafioSIAD-1.0.0.jar .

ENTRYPOINT ["java", "-jar", "desafioSIAD-1.0.0.jar"]