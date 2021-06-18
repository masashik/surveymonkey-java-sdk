package com.surveymonkey;

import java.util.List;
import com.surveymonkey.SurveyService;

public class SurveySDKClientApp {

  /**
   * This is a client application using the Java Web SDK of SurveyMonkey v3 APIs.
   */

  public static void main(String[] args) {
    SurveyService service = new SurveyService("API ACCESS KEY GOES HERE");
    List<Survey> filteredResult = service.getSurveyWithFilter("survey");// Filter surveys with title.
    filteredResult.forEach(survey -> System.out.println(survey.getTitle()));// Print filtered surveys.
    System.out.println("Program exit on success");
  }
}
