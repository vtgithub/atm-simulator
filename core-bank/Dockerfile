FROM registry.vaslapp.com/docker/openjdk:11-jre
COPY ./src/main/resources/application-prod.properties /app/application.properties
WORKDIR /app
RUN echo -n '192.168.159.12 sdms.moi.ir sdmg.moi.ir apima.moi.ir apimo.moi.ir apimg.moi.ir sdma.moi.ir' >> /etc/hosts
COPY ./target/guilds-*.jar /app/guilds.jar
EXPOSE 6075
CMD ["java", "-jar", "guilds.jar", "--spring.config.location=application.properties"]
