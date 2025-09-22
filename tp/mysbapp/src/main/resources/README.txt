profils de l'application:
==========================
dev_h2 : base de donnees "mysbdb" h2 en mode embedded sans serveur
dev_sgbd : base de donnees "mysbdb" geree par un serveur (ex: MySql/MariaDB , Postgres ou Oracle ou ...)
ddl_auto : pour recreation automatique des tables (en mode dev)
reinit : pour ajouter quelques donnees en mode dev/main (mais pas avec tests)
prod : production (a definir ulterieurement)