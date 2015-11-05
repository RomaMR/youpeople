# Youpeople

## A social media analysis service

### How to build
#### Back-end
	gradle clean build
#### Front-end
	cd youpeople-ui
	gulp

### How to deploy
To deploy project on remote server, log into it by SSH as user `tomcatadmin`.

1. Stop Tomcat
2. Remove previous youpeople deployments from Tomcat's webapps directory.
3. Rename `youpeople-ui/dist` to `youpeople` and put it into Tomcat's webapps directory.
4. Rename `youpeople-controllers-<version>.war` to `youpeople-controllers.war` and put it into Tomcat's webapps directory.
5. Restart Tomcat.

### How to update database schema on remote server
1. Prepare a dump of source database.
2. Log into the server as user `postgres`.
3. `dropdb youpeople && createdb youpeople`
4. `psql --set ON_ERROR_STOP=on youpeople < source-dump-file.sql`

### Helpful links
* [man ssh](http://linuxcommand.org/man_pages/ssh1.html)
* [man scp](http://linuxcommand.org/man_pages/scp1.html)
* [Postgresql dump](http://www.postgresql.org/docs/current/static/backup-dump.html)
