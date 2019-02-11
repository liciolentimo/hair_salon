# Beauty Hair Salon

## Description

This is a simple web app developed using Java and Spark as the framework. The owner/user can basically add a stylist and assign a client to the stylist. The stylist can have multiple clients but the client can only belong to one stylist. The owner can also update both the stylist and client's details as well as delete them.

## Prerequisites

* Java `jdk`
* Text editor such as `Visual Studio Code` or IDE such as `IntelliJ IDEA Community Edition`.
* Postgres server `sudo apt-get install postgresql postgresql-contrib libpq-dev`

## Installation

* Clone the repo: `https://github.com/liciolentimo/hair_salon.git`
Make sure Postgres server is installed and run the following commands:
* `sudo -u postgres createuser --superuser $USER` to create your role. Enter your desired password when prompted.
* `sudo -u postgres createdb $USER` to create a database with your username.
* Run `psql`
* `\c your_username` to change into your database.
* Run `CREATE DATABASE hair_salon;`
* `\c hair_salon` to enter into hair_salon database.
* `CREATE TABLE stylists(id serial PRIMARY KEY, stylistName varchar, stylistPhone varchar, stylistEmail varchar);`
* `CREATE TABLE clients(id serial PRIMARY KEY, clientName varchar, clientPhone varchar, clientEmail varchar);`
* `ALTER TABLE clients ADD stylistId int;` 
* `CREATE DATABASE hair_salon_test;`
* `CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;`

## Deployment
This app is deployed on heroku: https://quiet-plateau-37764.herokuapp.com/

## Author 

[MIT](LICENSE) © Licio Lentimo