const mongoose = require('mongoose');
const TrueFalseSchema = require('./true-false.schema.server.js');
const MultipleChoiceSchema = require('./multiple-choice.schema.server.js');

module.exports = mongoose.Schema({
    _id: Number,
    question: String,
    points: Number,
    questionType: {type: String, enums: ['MULTIPLE_CHOICE','TRUE_FALSE']},
    multipleChoice: MultipleChoiceSchema,
    trueFalse: TrueFalseSchema
}, {collection: 'questions'});
