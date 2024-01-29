needs database, example for creating with Docker:

docker run --name mariadb-calendar -e MARIADB_ROOT_PASSWORD=root -p 3306:3306 -d mariadb:latest