### Create database
```
create database ships;
create user ships with encrypted password 'XXX';
grant all privileges on database ships to ships;
```

### Start local database
```
docker run --name postgres -d -e POSTGRES_DB=ships -e POSTGRES_USER=ships -e POSTGRES_PASSWORD=ships -p 5432:5432 -d postgres:12.2-alpine
```
