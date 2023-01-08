package com.bbhurtel.springboot.learnrest.survey;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class SurveyService {

        private static List<Survey> surveys = new ArrayList<>();

        static {
                Question question1 = new Question("Question1",
                                "Most Popular Cloud Platform Today", Arrays.asList(
                                                "AWS", "Azure", "Google Cloud", "Oracle Cloud"),
                                "AWS");
                Question question2 = new Question("Question2",
                                "Fastest Growing Cloud Platform", Arrays.asList(
                                                "AWS", "Azure", "Google Cloud", "Oracle Cloud"),
                                "Google Cloud");
                Question question3 = new Question("Question3",
                                "Most Popular DevOps Tool", Arrays.asList(
                                                "Kubernetes", "Docker", "Terraform", "Azure DevOps"),
                                "Kubernetes");

                List<Question> questions = new ArrayList<>(Arrays.asList(question1,
                                question2, question3));

                Survey survey = new Survey("Survey1", "My Favorite Survey",
                                "Description of the Survey", questions);
                surveys.add(survey);
        }

        public List<Survey> retrieveAllSurvey() {
                return surveys;
        }

        public Survey retriveSurveyById(String surveyId) {
                Optional<Survey> survey = surveys.stream().filter(s -> s.getId().equals(surveyId)).findFirst();
                if (survey.isEmpty()) {
                        return null;
                }
                return survey.get();
        }

        public List<Question> retrieveAllSurveyQuestions(String surveyId) {
                Optional<Survey> survey = surveys.stream().filter(s -> s.getId().equals(surveyId)).findFirst();
                if (survey.isEmpty()) {
                        return null;
                }
                return survey.get().getQuestions();
        }

        public Question retrieveSurveyQuestionsById(String surveyId, String questionId) {
                List<Question> surveyQuestions = retrieveAllSurveyQuestions(surveyId);
                if (surveyQuestions.isEmpty()) {
                        return null;
                }
                Optional<Question> question = surveyQuestions.stream()
                                .filter(q -> q.getId().equals(questionId)).findFirst();
                if (question.isEmpty()) {
                        return null;
                }
                return question.get();
        }

        public String generateRandomId() {
                SecureRandom secureRandom = new SecureRandom();
                String randomId = new BigInteger(32, secureRandom).toString();
                return randomId;
        }

        public String addNewSurveyQuestions(String surveyId, Question question) {
                List<Question> questions = retrieveAllSurveyQuestions(surveyId);
                question.setId(generateRandomId());
                questions.add(question);
                return question.getId();
        }

        public String deleteSurveyQuestion(String surveyId, String questionId) {
                List<Question> surveyQuestions = retrieveAllSurveyQuestions(surveyId);
                if (surveyQuestions.isEmpty()) {
                        return null;
                }
                boolean removed = surveyQuestions.removeIf(q -> q.getId().equals(questionId));
                if (!removed) {
                        return null;
                }
                return questionId;
        }

        public void updateSurveyQuestion(String surveyId, String questionId, Question question) {
                List<Question> surveyQuestions = retrieveAllSurveyQuestions(surveyId);
                surveyQuestions.removeIf(q -> q.getId().equals(questionId));
                surveyQuestions.add(question);
        }
}
