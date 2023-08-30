ARG tomcat_image_version=9.0.55

FROM tomcat:${tomcat_image_version}

WORKDIR /usr/local/tomcat 

COPY ./target/myFirstServlet.war .
COPY ./server.xml ./conf/

RUN apt-get update
RUN apt-get install zip -y 
RUN unzip myFirstServlet.war
RUN ls -ltra
RUN cp -r ./myFirstServlet ./webapps
RUN rm -r ./myFirstServlet && rm ./myFirstServlet.war

EXPOSE 8080
