#!/bin/bash

echo "Waiting for mysql"
until mysql -h hackmyteeth_db -P 3306 -u root  &> /dev/null
do
  printf "."
  sleep 1
done

echo -e "\nmysql ready"
