FROM mariadb:11.4-ubi9
ENV MARIADB_ROOT_PASSWORD=imse24
COPY create.sql /home/topic-exchange/create.sql
CMD ["mariadbd", "--init_file=/home/topic-exchange/create.sql"]
