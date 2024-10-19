# Usa una imagen base oficial de openjdk
# FROM oraclelinux:7-slim
FROM openjdk:21-ea-24-oracle

WORKDIR /app

# Instalar el Oracle Instant Client
# RUN yum update -y && \
#     yum install -y java-21-openjdk-devel && \
#     yum install -y oracle-instantclient19.3-basiclite libaio

# Configurar las variables de entorno
# ENV JAVA_HOME /usr/lib/jvm/java-21-openjdk 
# ENV PATH $JAVA_HOME/bin:$PATH
# ENV ORACLE_HOME /usr/lib/oracle/19.3/client64
ENV TNS_ADMIN /app/oracle_wallet

# Copia el archivo jar al contenedor
COPY ./target/usuarios-0.0.1-SNAPSHOT.jar app.jar

# Copia el wallet de oracle al contenedor
COPY ./Wallet_VOCGYI3VV1P3NVR8 /app/oracle_wallet

# Exponer el puerto del servicio
EXPOSE 8091

# Ejecutar el servicio
CMD [ "java", "-jar", "app.jar" ]