// testdb = the name of the database
// cars = table name
// name = brand of the cars
// price = price of the cars 
// the table contains 2 columns:name,price
// the table contains 5 rows

var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/";

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
	// deal with any errors and close.
    if (err) throw err;
    console.log("1 document inserted");
    //db.close();
  });
  
  
	// function: find all cars("documents") in cars collection:
	dbo.collection("cars").find({}).toArray(function(err, result) {

		// deal with errors if occurring
		if (err) throw err;
		
		// print result
		console.log(result);
		console.log("As an array it is:==================");
		console.log(result.toArray);
		
		
		// close the database, even though no difference is registered
		db.close();
	});
	
	
	// delete all entries:
	dbo.collection("cars").deleteMany({}, function(err, result) {
		// deal with errors if occurring
		if (err) throw err;
		
		// print result
		console.log(result);
		
		// close the database, even though no difference is registered
		db.close();
	});
	
	
});

