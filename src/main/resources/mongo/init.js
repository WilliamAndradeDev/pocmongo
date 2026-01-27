db = db.getSiblingDB("pocmongo");

db.createCollection("student")

db.student.insertMany([
  {
    "_id": UUID("cc2149f6-f6fc-4582-b39a-afc89b56fda4"),
    "firstName": "string",
    "lastName": "p",
    "address": {
      "street": "string",
      "neighborhood": "string",
      "city": "string"
    },
    "classId": "1",
    "email": "string",
    "_class": "br.com.williamandradedev.pocmongo.model.Student"
  },
  {
    "_id": UUID("26f94eb4-bd5b-4cc2-9ffe-75eef07485d7"),
    "firstName": "string",
    "lastName": "q",
    "address": {
      "street": "string",
      "neighborhood": "string",
      "city": "string"
    },
    "classId": "1",
    "email": "string",
    "_class": "br.com.williamandradedev.pocmongo.model.Student"
  }
]);