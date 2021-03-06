// testdb = the name of the database
// cars = table name
// name = brand of the cars
// price = price of the cars 
// the table contains 2 columns:name,price
// the table contains 5 rows

var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/";
var carNames;



function create() {
    MongoClient.connect(url, function(err, db) {
        if (err) throw err;
        var dbo = db.db("testdb");
        var myobj = [
            { name: 'Audi', price: '52642'},
            { name: 'Mercedes', price: '57127'},
            { name: 'Skoda', price: '9000'},
            { name: 'Volvo', price: '29000'},
            { name: 'Bentley', price: '350000'},
        ];
        
        dbo.collection("cars").insertMany(myobj, function(err, res) {
            if (err) throw err;
            //console.log("inserted cars");
            db.close();
        }); 
    });
};

async function listCars() { 
 try{
    db = await MongoClient.connect(url);
    var dbo = db.db("testdb");
    car_and_name_parent = await dbo.collection("cars").find({}, { 
      projection: { _id: 0, name: 1} }).toArray();
   return car_and_name_parent
}catch(err){
  throw err;
 }
}; 

function deleteCars(){
    MongoClient.connect(url, function(err, db) {
        if (err) throw err;
        var dbo = db.db("testdb");
        
        // delete all entries:
        dbo.collection("cars").deleteMany({}, function(err, result) {
            if (err) throw err;
            console.log("Deleted cars");
            db.close();
        });
    });
};



//var globalVar = listCars();
//var globalVar = await listCars();
await listCars(globalVar);
//var globalVar = eval(listCars());
console.log(globalVar);