#  Main Repository für das IMSE-Projekt

## MariaDB bei der Entwicklung

Die folgenden Schritte ermögliche es MariaDB ohne Installation zu verwenden.

**Installation**
1. [Docker Desktop](https://www.docker.com/products/docker-desktop/) installieren
2. MariaDB Docker-Image herunterladen:
```cmd
docker pull mariadb:11.3
```

**Container mit DB-Image verwenden**
1. Container erstellen (muss nur einmal gemacht werden, wird auch gleich gestartet)
```cmd
docker run --name imse_testdb -e MYSQL_ROOT_PASSWORD=imse24 -p 3306:3306 -d mariadb:11.3
```
2. Container starten/stoppen/neustarten
```cmd
docker start imse_testdb
docker stop imse_testdb
docker restart imse_testdb
```
3. Container pausieren (stoppt die Datenbank, aber ohne sie quasi ganz zu beenden, kann nützlich sein,
wenn man sie einfach kurz nicht braucht und Docker aber den PC gerade zu sehr verlangsamt
```cmd
docker pause imse_testdb
docker unpause imse_testdb
```

Wenn der Docker-Container läuft sollte die Datenbank dann über `localhost:3306` erreichbar sein.

**Verbindung mit der Datenbank**

Um sich mit der DB zu verbinden, braucht man wieder einen Client, z.B. den über die [Command-Line](https://mariadb.com/docs/server/connect/clients/mariadb-client/), 
es gibt aber auch graphische Tools, z.B [DBeaver](https://dbeaver.io/).

Mit dem Command-Line-Client, kann man sich dann z.B. so verbinden:
```cmd
mariadb -h 127.0.0.1 -P 3306 -u root --password=imse24
```