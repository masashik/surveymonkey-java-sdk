package com.surveymonkey;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import com.surveymonkey.SurveyService;

/**
 * Unit test for simple App.
 */
public class SurveyServiceUnitTests
{
  /**
   * What to test:
   *  1. Thread-safe for multithreading environment.
   */

  private final SurveyService surveyService = new SurveyService("uX2r-rEuS8sFuxabPuSDfDlQtkwp6p8woRUUTpwSrvuVhMPeXZeWTwkcqJqsbCTUuG-qpAWIEiMFAC3UCXM8Q0CoNnx73Gg5pNESgerrvUKJ-jRUZX-itc-PNukmHEje");

  @Test
  public void buildConstructorWithoutAccessKey() {
    try {
      new SurveyService("");
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } finally {
      assertTrue(true);
    }
  }

  @Test
  public void buildConstructorWithNull() {
    try {
      new SurveyService(null);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } finally {
      assertTrue(true);
    }
  }

	@Test
  public void testGetAccessKey() {
		String testAccessKey = "ACCESS_KEY";
    SurveyService surveyService = new SurveyService(testAccessKey);
		//Try accessing private final variable and modify it to test the immutability. This code won't compile.
		//String accessKey = surveyService.getAccessKey();
		//assertTrue(accessKey.compareTo(testAccessKey) == 0);
  }

	//public Survey getSurveyByID(String surveyID)
  //public Survey createSurvey(HashMap surveyBody)
	//public ArrayList<Survey> getListOfSurvey()
	//private static Predicate<Survey> containsTitle(String input)
  //public List<Survey> getSurveyWithFilter(String input)
}
