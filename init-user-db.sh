set -e
mysql -u root -p
mysql -e "CREATE DATABASE dentist;"
#mysql -u root -p dentist < /docker-entrypoint-initdb.d/dentist.sql
