package com.asu.ser515.team20.mathboard.controller;

import com.asu.ser515.team20.mathboard.model.QuizWrapper;
import com.asu.ser515.team20.mathboard.service.TeacherPortalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Api(tags = {"Teacher Quiz Storage"})
@SwaggerDefinition(tags = {
        @Tag(name = "Swagger Resource", description = "Quiz Storage Portal")})
public class TeacherPortalController {

    @Autowired
    private TeacherPortalService teacherPortalService;

    @CrossOrigin
    @RequestMapping(value = "/PostQuiz", method = RequestMethod.POST)
    @ApiOperation(value = "Provide a array of question and answers in JSON format", response = ResponseEntity.class)
    public ResponseEntity<String> getUser(@RequestBody QuizWrapper arrayOfQuizzes) {
        return teacherPortalService.addQuizzes(arrayOfQuizzes) ? new ResponseEntity<>("Quiz questions stored Successfully", HttpStatus.OK) : new ResponseEntity<>("Failed store quiz questions.", HttpStatus.EXPECTATION_FAILED);
    }

    @CrossOrigin
    @RequestMapping(value = "/GetQuiz/{grade}", method = RequestMethod.GET)
    @ApiOperation(value = "Provided a grade, fetch all the question and answers of a quiz for that grade", response = QuizWrapper.class)
    public QuizWrapper getQuizzes(@PathVariable(value = "grade") String grade) {
        return teacherPortalService.getQuizBasedOnGrade(grade);
    }


}
