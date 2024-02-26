# playWithMongoDB

Created a application with REST APIs to store/retrieve messages for a web chat application using mongoRepository and mongoTemplate for advanced operation on db.

<img width="725" alt="Screenshot 2024-02-26 at 12 59 08 AM" src="https://github.com/RJ2523/playWithMongoDB/assets/33192364/6cb0c5ad-8aa8-4111-adaa-c40f0fb45338">


Useful Mongo DB Commands:
mongosh // to interact with mongodb
use <db_name>; // switches to db_name if exists else creates a new one
show dbs; // lists all databases;
db.<collection_name>.insertOne(object) //inserts data into a collection, if not exists create a new one
db.<collection_name>.find() // finds all records inside a collection, can pass in condition for filtering
