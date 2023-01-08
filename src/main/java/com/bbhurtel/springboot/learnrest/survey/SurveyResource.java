package com.bbhurtel.springboot.learnrest.survey;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class SurveyResource {

    private SurveyService surveyService;

    public SurveyResource(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @RequestMapping("/surveys")
    public List<Survey> retrieveAllSurvey() {
        return surveyService.retrieveAllSurvey();
    }

    @RequestMapping("/surveys/{surveyId}")
    public Survey retriveSurveyById(@PathVariable String surveyId) {
        Survey survey = surveyService.retriveSurveyById(surveyId);
        if (survey == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return survey;
    }

    @RequestMapping("/surveys/{surveyId}/questions")
    public List<Question> retrieveAllSurveyQuestions(@PathVariable String surveyId) {
        List<Question> questions = surveyService.retrieveAllSurveyQuestions(surveyId);
        if (questions == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return questions;
    }

    @RequestMapping("/surveys/{surveyId}/questions/{questionId}")
    public Question retrieveSurveyQuestionsById(@PathVariable String surveyId, @PathVariable String questionId) {
        Question question = surveyService.retrieveSurveyQuestionsById(surveyId, questionId);
        if (question == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return question;
    }

    @RequestMapping(value = "/surveys/{surveyId}/questions", method = RequestMethod.POST)
    public ResponseEntity<Object> addNewSurveyQuestions(@PathVariable String surveyId,
            @RequestBody Question question) {
        String questionId = surveyService.addNewSurveyQuestions(surveyId, question);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{questionId}").buildAndExpand(questionId)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    // TODO : return question id instead of noContent()
    @RequestMapping(value = "/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSurveyQuestion(@PathVariable String surveyId,
            @PathVariable String questionId) {
        surveyService.deleteSurveyQuestion(surveyId, questionId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateSurveyQuestion(@PathVariable String surveyId,
            @PathVariable String questionId,
            @RequestBody Question question) {
        surveyService.updateSurveyQuestion(surveyId, questionId, question);
        return ResponseEntity.noContent().build();
    }

}
