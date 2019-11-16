const mongoose = require('mongoose');
const express = require('express');
var cors = require('cors');
const bodyParser = require('body-parser');
const logger = require('morgan');
const Data = require('./data');

const API_PORT = 3001;
const app = express();
app.use(cors());
const router = express.Router();

// Get specific datacalls:
var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/";
var carNames;

// this is our MongoDB database
// const dbRoute =
//  'mongodb://<your-db-username-here>:<your-db-password-here>@ds249583.mlab.com:49583/fullstack_app';
const dbRoute =
        'mongodb://localhost:27017/fullstack_app';


// connects our back end code with the database
mongoose.connect(dbRoute, { useNewUrlParser: true });

let db = mongoose.connection;

db.once('open', () => console.log('connected to the database'));

// checks if connection with the database is successful
db.on('error', console.error.bind(console, 'MongoDB connection error:'));

// (optional) only made for logging and
// bodyParser, parses the request body to be a readable json format
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(logger('dev'));

// this is our get method
// this method fetches all available data in our database
router.get('/getData', (req, res) => {
    Data.find((err, data) => {
        if (err) return res.json({ success: false, error: err });
        return res.json({ success: true, data: data });
    });
});

// this is our update method
// this method overwrites existing data in our database
router.post('/updateData', (req, res) => {
    const { id, update } = req.body;
    Data.findByIdAndUpdate(id, update, (err) => {
        if (err) return res.json({ success: false, error: err });
        return res.json({ success: true });
    });
});

// this is our delete method
// this method removes existing data in our database
router.delete('/deleteData', (req, res) => {
    const { id } = req.body;
    Data.findByIdAndRemove(id, (err) => {
        if (err) return res.send(err);
        return res.json({ success: true });
    });
});

// this is our create methid
// this method adds new data in our database
router.post('/putData', (req, res) => {
    let data = new Data();
    
    const { id, message } = req.body;
    
    if ((!id && id !== 0) || !message) {
        return res.json({
            success: false,
            error: 'INVALID INPUTS',
        });
    }
    data.message = message;
    data.id = id;
    data.save((err) => {
        if (err) return res.json({ success: false, error: err });
        return res.json({ success: true });
    });
});

// this is our get method
// this method fetches all available data in our database
router.get('/getCarData', (req, res) => {
    Data.find((err, data) => {
        if (err) return res.json({ success: false, error: err });
        return res.json({ success: true, data: data });
    });
});


// append /api for our http requests
app.use('/api', router);

// launch our backend into a port
app.listen(API_PORT, () => console.log(`LISTENING ON PORT ${API_PORT}`));

function Car(model, color) {
    this.model = model;
    this.color = color;
}
var c1 = new Car('BMW', 'red');
console.log(c1.model);


var c1 = new Car('BMW', 'red');
alert(c1.model);

function Car(model, color) {
    this.model = model;
    this.color = color;

    // testdb = the name of the database
    // cars = table name
    // name = brand of the cars
    // price = price of the cars 
    // the table contains 2 columns:name,price
    // the table contains 5 rows


    this.url = "mongodb://localhost:27017/";
    this.carNames;
    
    
}



var testCar = MongoClient.connect(url, function(err, db) {
    if (err) throw err;
    var dbo = db.db("testdb");
    var myobj = [
	{ name: 'Audi', price: '52642'},
        { name: 'Mercedes', price: '57127'},
        { name: 'Skoda', price: '9000'},
        { name: 'Volvo', price: '29000'},
        { name: 'Bentley', price: '350000'},
    ];
    
    function insertManyCars() {
        dbo.collection("cars").insertMany(myobj, function(err, res) {
            if (err) throw err;
            db.close();
        });
    };
    
    // function: find all cars("documents") in cars collection and ONLY LIST THEIR NAMES!
    function findCarNames() {
        dbo.collection("cars").find({}, { projection: { _id: 0, name: 1} }).toArray(function(err, result) {
            if (err) throw err;
            console.log("New results0");
            car_and_name = result;
            console.log(car_and_name);
            return car_and_name;
            db.close();
        });
    };
    
    // delete all entries:
    function deleteAllCars() {
        dbo.collection("cars").deleteMany({}, function(err, result) {
            if (err) throw err;
            db.close();
        });
    };
});

testCar.insertManyCars();