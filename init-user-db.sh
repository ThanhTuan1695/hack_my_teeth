set -e

mysql -u root -p
mysql -e "CREATE DATABASE dentist;"
mysql -e "CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admindentist';"
mysql -e "GRANT ALL PRIVILEGES ON *.* TO ‘admin’@’%’ IDENTIFIED BY ‘admindentist’ WITH GRANT OPTION;"
mysql -e "GRANT ALL PRIVILEGES ON *.* TO ‘admin’@’localhost’ IDENTIFIED BY ‘admindentist’ WITH GRANT OPTION;"
mysql -e "FLUSH PRIVILEGES;"

