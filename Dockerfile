FROM maven:3.5.3-jdk-8 as maven

RUN mkdir --parents /root/hack_my_teeth

WORKDIR /root/hack_my_teeth



ADD pom.xml /root/hack_my_teeth/

RUN mvn verify clean --fail-never



ADD . /root/hack_my_teeth

RUN mvn -Dmaven.test.skip=true install 
#CMD ["mvn", "install", "-Dmaven.test.skip=true"]





FROM java:8

RUN bash -c "cp /root/hack_my_teeth/target/Hack*.jar /root/app.jar"

CMD ["java","-jar","-Dspring.profiles.active=docker","/root/app.jar"]
#CMD ["sleep", "1d"]
