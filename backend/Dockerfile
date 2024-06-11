FROM gradle:8.8-jdk21
WORKDIR /home/topic-exchange
COPY ./ sources/
WORKDIR sources
RUN ["gradle", "bootJar"]
WORKDIR ..
RUN ["mkdir", "executable"]
RUN ["mv", "sources/build/libs/backend-1.0.0.jar", "executable/backend.jar"]
RUN ["mv", "sources/run.sh", "executable/"]
RUN ["rm", "-rf", "sources"]
CMD ["/bin/sh", "-c", "executable/run.sh"]
