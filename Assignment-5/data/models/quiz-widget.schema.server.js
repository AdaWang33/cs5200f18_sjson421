const mongoose = require('mongoose')
const questionSchema = require('./question.schema.server')
module.exports  = mongoose.Schema({
    questions: [{
        type: mongoose.Schema.Types.ObjectId,
        ref: 'QuestionModel'
    }]
}, {collection: 'question-widgets'});