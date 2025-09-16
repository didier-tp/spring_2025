liste de profiles:
---------
prod : production
dev_h2 : developpement avec base de données h2 (embraqué sans serveur)
dev_sgbd : developpement avec serveur de base de données h2 (mysql ou postgres ou ...)
ddl_auto : avec option "tables crées automatiquement selon structure java"

combinaisons possibles au démarrage:
dev_h2,ddl_auto
dev_sgbd,ddl_auto