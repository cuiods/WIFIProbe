'use strict';

module.exports = {

  'GET /api/question/type': function (req, res) {
    setTimeout(function () {
      res.json(["编程题","改错题"]);
    }, 500);
  },

  'POST /api/user/auth': function (req, res) {

    setTimeout(function () {
      res.json({
        "username":"sbin",
        "type":"student",
        "avatar":"image url",
        "gender":"male",
        "email":"xxx@xxx.com",

        //student attribute
        "gitId":999,
        "number":"141250123",

      });
    }, 500);
  },

  'GET /api/user/*/course': function (req, res) {
    res.json([
      {
        "id":123,
        name: '软件工程与计算三',
        grade: '2015级',
        description: '这里是软工三课程的介绍，可选填',
        teachers: ['刘嘉'],
        //exams: ['1','2','3'],
        //homework: ['1','2','3']
      },
      {
        "id":1,
        name: '软件工程与计算二',
        grade: '2015级',
        description: '这里是软工二课程的介绍，可选填',
        teachers: ['丁二玉','刘钦'],
      },
      {
        "id":2,
        name: '人机交互的软件工程方法',
        grade: '2014级',
        description: '这里是人机交互的软件工程方法的介绍，可选填',
        teachers: ['冯桂焕'],
      }
    ])
  },

  'GET /api/course/*/exam': function (req, res) {
    res.json([
      {
        "id":1,
        "title":"软工三考试",
        "description":"string",
        "startAt":"2016-12-30 00:00:00",
        "endAt":"2016-12-30 00:01:00",
        "course":{
          "id":123,
          "name":"软件工程与计算三",
          "description":"string"
        },
        "status":"initSuccess",
      },

      {
        "id":2,
        "title":"软工三考试",
        "description":"string",
        "startAt":"2016-12-31 00:00:00",
        "endAt":"2016-12-31 00:01:00",
        "course":{
          "id":123,
          "name":"软件工程与计算三",
          "description":"string"
        },
        "status":"timeup",
      }
    ])
  },

  'GET /api/course/*/homework': function (req, res) {
    res.json([
      {
        "id":1,
        "title":"软工三作业",
        "description":"string",
        "startAt":"2016-12-31 00:00:00",
        "endAt":"2016-12-31 00:01:00",
        "course":{
          "id":123,
          "name":"软件工程与计算三",
          "description":"string"
        },
        "status":"timeup",
      },
      {
        "id":2,
        "title":"软工三作业",
        "description":"string",
        "startAt":"2016-12-31 00:00:00",
        "endAt":"2016-12-31 00:01:00",
        "course":{
          "id":123,
          "name":"软件工程与计算三",
          "description":"string"
        },
        "status":"timeup",
      },

    ])
  },

  'GET /api/user/*/exam/*': function (req, res) {
    return res.json({
      "id":123,
      "title":"软工三考试",
      "description":"string",
      "startAt":"yyyy-MM-dd hh:mm:ss",
      "endAt":"yyyy-MM-dd hh:mm:ss",
      "course":{
        "id":123,
        "name":"14软工II2班",
        "description":"string"
      },
      "status":"timeup",

      //假如考试已经结束,才能看到具体题目及成绩
      "score":[
        {
          "score":10,    //假如未出成绩,则没有这一项
          "analysisReportUrl" : "url",   //未分析完则没有这一项
          "scoreReportUrl" : "url",   ///未分析完则没有这一项
          "question":{
            "id":123,
            "title":"string",
            "description":"string" ,
            "type":"编程题"
          }
        },
      ]

    })
  },

  'GET /api/user/*/homework/*': function (req, res) {
    return res.json({
      "id":123,
      "title":"软工二考试",
      "description":"string",
      "startAt":"yyyy-MM-dd hh:mm:ss",
      "endAt":"yyyy-MM-dd hh:mm:ss",
      "course":{
        "id":123,
        "name":"14软工II2班",
        "description":"string"
      },
      "status":"timeup",

      //假如考试已经结束,才能看到具体题目及成绩
      "score":[
        {
          "score":10,    //假如未出成绩,则没有这一项
          "analysisReportUrl" : "url",   //未分析完则没有这一项
          "scoreReportUrl" : "url",   ///未分析完则没有这一项
          "question":{
            "id":123,
            "title":"string",
            "description":"string" ,
            "type":"编程题"
          }
        },
      ]

    })
  },

  'POST /api/question/file': function (req, res) {
    return res.json({
      "msg1": "first",
      "msg2": "second",
      "msg3": "third"
    })
  }
};
