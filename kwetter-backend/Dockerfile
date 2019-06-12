FROM payara/server-full

MAINTAINER Arnoud Bevers "arnoud@arnoudbevers.com"

VOLUME [ "/java" ]  
WORKDIR /java
COPY . /java

EXPOSE 4848
EXPOSE 8080

USER root

RUN apt-get update && \
  apt-get install -y maven && \
  wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java_8.0.15-1debian9_all.deb && \
  dpkg -i mysql-connector-java_8.0.15-1debian9_all.deb && \
  rm mysql-connector-java_8.0.15-1debian9_all.deb && \
  mvn install && \
  mvn compile && \
  cp ./target/kwetter.war ${DEPLOY_DIR}/kwetter.war

USER payara

ENTRYPOINT echo 'add-library /usr/share/java/mysql-connector-java-8.0.15.jar' >> ${POSTBOOT_COMMANDS} && \
  echo 'create-jdbc-connection-pool --ping --datasourceclassname com.mysql.cj.jdbc.MysqlDataSource --restype javax.sql.DataSource --property user='$DATABASE_USER':password='$DATABASE_PASS':databaseName='$DATABASE_NAME':serverName='$DATABASE_HOST':portNumber='$DATABASE_PORT':useSSL=false '$JDBC_NAME'_pool' >> ${POSTBOOT_COMMANDS} && \
  echo 'create-jdbc-resource --connectionpoolid '$JDBC_NAME'_pool '$JDBC_NAME'_jndi_local' >> ${POSTBOOT_COMMANDS} && \
  ${SCRIPT_DIR}/init_1_generate_deploy_commands.sh --properties=contextroot='/' && \
  ${PAYARA_DIR}/bin/asadmin start-domain -v --postbootcommandfile ${POSTBOOT_COMMANDS} production  
