## Author
Rose Mogusu.

## Description
 wildlife_tracker allows Rangers to track wildlife sightings in the given area.
 

## Installations
* SDK.
* JRE.
* IntelliJ.
* Jenkins.
* Heroku CLI.
* Maven.

## Setup
* Launch postgres
* Type psql 
# Run the following commands:
* CREATE DATABASE wildlife_tracker;
* \c wildlife_tracker;
* CREATE TABLE animals (id serial PRIMARY KEY, name varchar, health varchar, age varchar, type varchar);
* CREATE TABLE sightings (id serial PRIMARY KEY, animal_id int, location varchar, ranger_name varchar, timestamp timestamp);
* CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;



## Technologies Used
* Junit.
* Gradle.
* Java.
* spark.


## BDD(Behaviour Driven Development)
* Record a sighting by filling the required form.
* Make sure to specify whatever species it belongs to.
* Submit your information.
* They will be recorded successfully.

	

## License
MIT License &copy 2019 Rose
Copyright (c) [2019][Wildlife_Tracker]

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

## Collaborate