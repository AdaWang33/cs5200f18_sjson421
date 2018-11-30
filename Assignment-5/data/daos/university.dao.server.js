const mongoose = require('mongoose');
const studentModel = require('../models/student.model.server');
const answerModel = require('../models/answer.model.server');
const questionModel = require('../models/question.model.server');
const quizWidgetModel = require('../models/quiz-widget.model.server');

truncateDatabase = () => {
    return Promise.all([
        quizWidgetModel.deleteMany({}).exec(),
        answerModel.deleteMany({}).exec(),
        questionModel.deleteMany({}).exec(),
        studentModel.deleteMany({}).exec()
    ])
};

createStudent = student => studentModel.create(student);
deleteStudent = studentId => studentModel.deleteOne({_id: studentId});

createQuestion = question => questionModel.create(question);
deleteQuestion = questionId => questionModel.deleteOne({_id: questionId});

answerQuestion = (studentId, questionId, answer) => {
    answer.student = studentId;
    answer.question = questionId;
    answerModel.create(answer);
};
deleteAnswer = answerId => answerModel.deleteOne({_id: answerId});


findAllStudents = () => studentModel.find();
findStudentById = studentId => studentModel.findById(studentId);
findAllQuestions = () => questionModel.find();
findQuestionById = questionId => questionModel.findById(questionId);
findAllAnswers = () => answerModel.find();
findAnswerById = answerId => answerModel.findById(answerId);
findAnswersByStudent = studentId => answerModel.find({student: studentId});
findAnswersByQuestion = questionId => answerModel.find({question: questionId});

populateDatabase = () => {
    return Promise.all([
    createStudent({
        _id: 123,
        username: 'alice',
        password: 'alice',
        firstName: 'Alice',
        lastName: 'Wonderland',
        gradYear: 2020,
        scholarship: 15000
    }),
    createStudent({
        _id: 234,
        username: 'bob',
        password: 'bob',
        firstName: 'Bob',
        lastName: 'Hope',
        gradYear: 2021,
        scholarship: 12000
    }),

    createQuestion({
        _id: 321,
        question: "Is the following schema valid?",
        points: 10,
        questionType: "TRUE_FALSE",
        multipleChoice: null,
        trueFalse: {
            isTrue: false
        }
    }),
    createQuestion({
        _id: 432,
        question: "DAO stands for Dynamic Access Object.",
        points: 10,
        questionType: "TRUE_FALSE",
        multipleChoice: null,
        trueFalse: {
            isTrue: false
        }
    }),
    createQuestion({
        _id: 543,
        question: "What does JPA stand for?",
        points: 10,
        questionType: "MULTIPLE_CHOICE",
        multipleChoice: {
            choices: "Java Persistence API,Java Persisted Application,JavaScript Persistence API,JSON Persistent Associations",
            correct: 1
        },
        trueFalse: null
    }),
    createQuestion({
        _id: 654,
        question: "What does ORM stand for?",
        points: 10,
        questionType: "MULTIPLE_CHOICE",
        multipleChoice: {
            choices: "Object Relational Model,Object Relative Markup,Object Reflexive Model,Object Relational Mapping",
            correct: 4
        },
        trueFalse: null
    }),


    answerQuestion(123, 321, {
        _id: 123,
        trueFalseAnswer: true,
        multipleChoiceAnswer: null,
        student: null,
        question: null
    }),
    answerQuestion(123, 432, {
        _id: 234,
        trueFalseAnswer: false,
        multipleChoiceAnswer: null,
        student: null,
        question: null
    }),
    answerQuestion(123, 543, {
        _id: 345,
        trueFalseAnswer: null,
        multipleChoiceAnswer: 1,
        student: null,
        question: null
    }),
    answerQuestion(123, 654, {
        _id: 456,
        trueFalseAnswer: null,
        multipleChoiceAnswer: 2,
        student: null,
        question: null
    }),
    answerQuestion(234, 321, {
        _id: 567,
        trueFalseAnswer: false,
        multipleChoiceAnswer: null,
        student: null,
        question: null
    }),
    answerQuestion(234, 432, {
        _id: 678,
        trueFalseAnswer: true,
        multipleChoiceAnswer: null,
        student: null,
        question: null
    }),
    answerQuestion(234, 543, {
        _id: 789,
        trueFalseAnswer: null,
        multipleChoiceAnswer: 3,
        student: null,
        question: null
    }),
    answerQuestion(234, 654, {
        _id: 890,
        trueFalseAnswer: null,
        multipleChoiceAnswer: 4,
        student: null,
        question: null
    })]);
}

module.exports = {
    truncateDatabase,
    populateDatabase,
    createStudent,
    deleteStudent,
    createQuestion,
    deleteQuestion,
    answerQuestion,
    deleteAnswer,
    findAllStudents,
    findStudentById,
    findAllQuestions,
    findQuestionById,
    findAllAnswers,
    findAnswerById,
    findAnswersByStudent,
    findAnswersByQuestion
};