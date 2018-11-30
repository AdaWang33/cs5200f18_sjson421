module.exports = function () {
    const mongoose = require('mongoose');
    const databaseName = 'white-board';
    var connectionString = "mongodb://localhost/";
    connectionString += databaseName;
    if (!mongoose.connect(connectionString)) {
        console.log("Connection failed!");
    }
};
