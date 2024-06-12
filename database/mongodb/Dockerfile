FROM mongo:7.0
ENV MONGO_INITDB_ROOT_USERNAME=root
ENV MONGO_INITDB_ROOT_PASSWORD=imse24
ENV MONGO_INITDB_DATABASE=topic_exchange
COPY setup.js /docker-entrypoint-initdb.d/
