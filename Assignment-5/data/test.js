require('./db.js')();
const assert = require('assert');
const uniDao = require('./daos/university.dao.server.js');

function testStudentsInitialCount(callback) {
    uniDao.findAllStudents().exec()
        .then(function (response) {
            if (response.length == 2) {
                console.log("testStudentsInitialCount success!")
            }else {
                console.log("testStudentsInitialCount failed! " + response.length);
            }
            callback();
        })
        .catch(function (err) {
            console.log(err.message);
        });
}

function testQuestionsInitialCount(callback) {
    uniDao.findAllQuestions().exec()
        .then(function (response) {
            if (response.length == 4) {
                console.log("testQuestionsInitialCount success!")
            }else {
                console.log("testQuestionsInitialCount failed! " + response.length);
            }
            callback();
        })
        .catch(function (err) {
            console.log(err.message);
        });
}

function testAnswersInitialCount(callback) {
    uniDao.findAllAnswers().exec()
        .then(function (response) {
            if (response.length == 8) {
                console.log("testAnswersInitialCount success!")
            }else {
                console.log("testAnswersInitialCount failed! "+ response.length);
            }
            callback();
        })
        .catch(function (err) {
            console.log(err.message);
        });
}

function testDeleteAnswer(callback) {
    uniDao.deleteAnswer(890).exec()
        .then(function () {
            uniDao.findAllAnswers().exec()
                .then(function (promise) {
                    if (promise.length == 7) {
                        console.log("testDeleteAnswer (all answers) success!")
                    }else {
                        console.log("testDeleteAnswer (all answers) failed! "+ promise.length);
                    }
                }).catch(function (err) {
                        console.log(err.message);
                });
            uniDao.findAnswersByStudent(234).exec()
                .then(function (promise) {
                    if (promise.length == 3) {
                        console.log("testDeleteAnswer (Bob's answers) success!")
                    }else {
                        console.log("testDeleteAnswer (Bob's answers) failed! "+ promise.length);
                    }
                    callback();
                }).catch(function (err) {
                    console.log(err.message);
                });
        })
        .catch(function (err) {
            console.log(err.message);
        });
}

function testDeleteQuestion(callback) {
    uniDao.deleteQuestion(321).exec()
        .then(function () {
            uniDao.findAllQuestions().exec()
                .then(function (promise) {
                    if (promise.length == 3) {
                        console.log("testDeleteQuestion success!")
                    }else {
                        console.log("testDeleteQuestion failed! "+ promise.length);
                    }
                    callback();
                })
        })
}

function testDeleteStudent() {
    uniDao.deleteStudent(234).exec()
        .then(function () {
            uniDao.findAllStudents().exec()
                .then(function (promise) {
                    if (promise.length == 1) {
                        console.log("testDeleteStudent success!")
                    }else {
                        console.log("testDeleteStudent failed!"+ promise.length);
                    }
                })
        })
}
uniDao.truncateDatabase()
    .then(() => uniDao.populateDatabase()
        .then(function () {
            testStudentsInitialCount(function() {
                testQuestionsInitialCount(function() {
                    testAnswersInitialCount(function() {
                        testDeleteAnswer(function() {
                            testDeleteQuestion(function() {
                                testDeleteStudent()
                            })
                        })
                    })
                })
            })
        }));