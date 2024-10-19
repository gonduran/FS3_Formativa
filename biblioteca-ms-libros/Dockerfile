FROM eclipse-temurin:22-jdk AS buildstage 

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY pom.xml .
COPY src /app/src
# Copia el wallet de oracle al contenedor
COPY Wallet_VOCGYI3VV1P3NVR8 /app/wallet

ENV TNS_ADMIN=/app/wallet

RUN mvn clean package

FROM eclipse-temurin:22-jdk 

# Copia el archivo jar al contenedor
COPY --from=buildstage /app/target/biblioteca-ms-libros-0.0.1-SNAPSHOT.jar /app/biblioteca-ms-libros.jar

# Copia el wallet de oracle al contenedor
COPY Wallet_VOCGYI3VV1P3NVR8 /app/wallet

ENV TNS_ADMIN=/app/wallet
# Exponer el puerto del servicio
EXPOSE 8091

# Ejecutar el servicio
ENTRYPOINT [ "java", "-jar","/app/biblioteca-ms-libros.jar" ]
