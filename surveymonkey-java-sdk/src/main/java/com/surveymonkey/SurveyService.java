package com.surveymonkey;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.net.URI;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.function.Predicate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import com.surveymonkey.Survey;

public final class SurveyService {

  /**
   * This is a service class that holds survey controlling/managing methods.
   */

  private ArrayList<Survey> responseBody = null;
  //Cache placeholder to optimize/reduce number of HTTP RESTful API call
  private HashMap surveys_hashmap = new HashMap();

  // Private fields
  // {
  private final String accessKey;//accessKey cannot be updated once this class is instantiated.
  // }

  // Constructor
  public SurveyService(String _accessKey) {
		if (_accessKey != null && _accessKey.length() > 0) {
    	this.accessKey = _accessKey;
		} else {
			throw new IllegalArgumentException("Exception: Empty access key is provided.");
			// This line does not compile by Java compiler error. I left this line as a bad practice example.
			//throw new Exception("Exception: Empty access key is provided.");
		}
  }

  // Methods
  // {

    // Get Access Key
    private String getAccessKey() {
      return accessKey;
    }

    // Get a survey with provided ID
    public Survey getSurveyByID(String surveyID) {

      //(TEST)1.Test if surveyID is malformed.

       //(Optimization) Get the jCache the retrieved result from the HTTP RESTful API request
      Survey survey_in_cache = (Survey) surveys_hashmap.get(surveyID);
      if (survey_in_cache != null) {
          return survey_in_cache;
      }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.surveymonkey.com/v3/surveys"+"/"+surveyID))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + this.getAccessKey())
                .build();

        HttpResponse<String> response = null;
        Survey responseSurvey = new Survey();

        try {

            response = client.send(request, BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode >= 200 && statusCode < 300) {
                System.out.println(response.body());
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.body());
                Survey survey = objectMapper.treeToValue(jsonNode, Survey.class);

                 //(Optimization) Cache the retrieved result from the HTTP RESTful Web API request  
                surveys_hashmap.put(surveyID, survey);

                responseSurvey = survey;
            } else {
                System.out.println("Unexpected response...further investigation is required");
            }
        } catch (java.lang.InterruptedException error) {
          error.printStackTrace();
        } catch (java.io.IOException error) {
          error.printStackTrace();
        } finally {
          System.out.println("Successfully executed the synchronous HTTP request and got the response.");
        }
        return responseSurvey;
    }

    // Create new survey 
    public Survey createSurvey(HashMap surveyBody) {

      HashMap<String, String> values = new HashMap<String, String>();

      if (surveyBody == null) {
        values = new HashMap<String, String>() {{
          put("name", "John Doe");
          put ("occupation", "gardener");
        }};
      } else {
        values = surveyBody;
      }

      var objectMapper = new ObjectMapper();
      String requestBody = null;
      try {
        requestBody = objectMapper.writeValueAsString(values);
      } catch (Exception e) {
        e.printStackTrace();
      }

      HttpClient client = HttpClient.newHttpClient();

      HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create("https://api.surveymonkey.com/v3/surveys"))
              .timeout(Duration.ofMinutes(1))
              .header("Content-Type", "application/json")
              .header("Authorization", "Bearer " + this.getAccessKey())
              .POST(HttpRequest.BodyPublishers.ofString(requestBody))
              .build();

      HttpResponse<String> response = null;
      Survey responseSurvey = new Survey();

      try {
          response = client.send(request, BodyHandlers.ofString());
          int statusCode = response.statusCode();
          if (statusCode >= 200 && statusCode < 300) {
              System.out.println(response.body());
              JsonNode jsonNode = objectMapper.readTree(response.body());
              Survey survey = objectMapper.treeToValue(jsonNode, Survey.class);
              responseSurvey = survey;
          } else {
              System.out.println("HTTP POST Request...further investigation is required");
          }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        System.out.println("HTTP POST Request executed successfully");
          //(Optimization) Cache the retrieved result from the HTTP RESTful Web API request
          surveys_hashmap.put(responseSurvey.getID(), responseSurvey);
      }
      return responseSurvey;
    }

    // Get a list of surveys
    public ArrayList<Survey> getListOfSurvey() {

      // This line exists to optimize the redundant request.
        if (responseBody != null) {
            return responseBody;
        }

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.surveymonkey.com/v3/surveys"))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + this.getAccessKey())
                .build();

        HttpResponse<String> response = null;

        try {
            response = client.send(request, BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode >= 200 && statusCode < 300) {
                System.out.println(response.body());
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.body());
                ArrayNode dataNode = (ArrayNode) jsonNode.get("data");

                ArrayList<Survey> surveys = new ArrayList<>();

                if (dataNode.isArray()) {
                    for (JsonNode node : dataNode) {
                        Survey survey = objectMapper.treeToValue(node, Survey.class);
                        surveys.add(survey);
                    }
                }
                responseBody = surveys;
            } else {
                System.out.println("Unexpected response...further investigation is required");
            }
        } catch (java.lang.InterruptedException error) {
            error.printStackTrace();
        } catch (java.io.IOException error) {
            error.printStackTrace();
        } finally {
            System.out.println("Successfully executed the synchronous HTTP request and got the response.");
        }
        return responseBody;
    }

		// A method to store lambda expression
    private static Predicate<Survey> containsTitle(String input) {
      return survey -> survey.getTitle().contains(input);
    }
    
    // Get the result by filtering the list
    public List<Survey> getSurveyWithFilter(String input) {

      ArrayList<Survey> surveys = this.getListOfSurvey();

      //Filter surveys by user input - Pickup surveys containing the input text.
      //final Predicate<Survey> containsTitle = survey -> survey.getTitle().contains(input);
      List<Survey> result = surveys.stream().filter(containsTitle(input)).collect(Collectors.toList());

      /**
       * final Predicate<String> startsWithN = name -> name.startsWith("N");
       *
       * final Predicate<String> containsTitle = survey -> survey.getTitle().contains(input)
       * List<Survey> result = surveys.stream().filter(containsTitle).collect(Collectors.toList());
       */


      //List<Survey> result = surveys.stream().filter(survey -> !survey.getTitle().contains(input)).collect(Collectors.toList());

      return result;
    }

// }
}
