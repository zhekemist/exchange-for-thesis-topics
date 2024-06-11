FROM mariadb:11.3
ENV MARIADB_ROOT_PASSWORD=imse24
COPY create.sql /home/topic-exchange/create.sql
CMD ["mariadbd", "--init_file=/home/topic-exchange/create.sql"]
