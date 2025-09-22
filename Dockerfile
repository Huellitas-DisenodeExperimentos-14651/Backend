# Usa una imagen con JDK
FROM eclipse-temurin:21-jdk

# Establece el directorio de trabajo
WORKDIR /app

# Copia el wrapper y el pom
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Dale permisos de ejecución al wrapper
RUN chmod +x mvnw

# Descarga las dependencias
RUN ./mvnw dependency:go-offline

# Copia todo el proyecto y compílalo
COPY . .
RUN ./mvnw clean package -DskipTests

# Expone el puerto (Render lo ignora, pero ayuda a documentar)
EXPOSE 8080

# Copiamos el JAR generado y lo renombramos a un nombre fijo
RUN cp target/*.jar app.jar

# Comando para correr el JAR
CMD ["java", "-jar", "app.jar"]
