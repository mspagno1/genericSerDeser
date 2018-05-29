
package  genericSerDeser.fileOperations;

public class Logger{
    /*DEBUG_VALUE=8 [Print result string from an object in DMPL]
      DEBUG_VALUE=7 [Prints classNames in DMPL]
      DEBUG_VALUE=6 [Print method names in DMPL]
      DEBUG_VALUE=5 [Print types of information of an objects field type, fieldName and value]
      DEBUG_VALUE=4 [Print how many duplicates for each counter location]
      DEBUG_VALUE=3 [Print toString of objects data before we add it to data structure]
      DEBUG_VALUE=2 [Print every input line]
      DEBUG_VALUE=1 [Print constructor call]
      DEBUG_VALUE=0 [No output should be printed from the application, except normal output of the number of objects and the number of duplicates]
    */



    public static enum DebugLevel { EMPTY ,C_TOR, INPUT_LINE, OBJECT_DATA, COUNTER, FIELD_DATA, METHOD, CLASS_NAME, RESULT};

    private static DebugLevel debugLevel;


    public static void setDebugValue (int levelIn) {
	switch (levelIn) {
	  //case 4: debugLevel = DebugLevel.CONSTRUCTOR; break;
	      // add code for other cases
	  //case 0: debugLevel = DebugLevel.RELEASE; break;
	  case 0: debugLevel = DebugLevel.EMPTY; break;
	  case 1: debugLevel = DebugLevel.C_TOR; break;
	  case 2: debugLevel = DebugLevel.INPUT_LINE; break;
	  case 3: debugLevel = DebugLevel.OBJECT_DATA; break;
	  case 4: debugLevel = DebugLevel.COUNTER; break;
	  case 5: debugLevel = DebugLevel.FIELD_DATA; break;
	  case 6: debugLevel = DebugLevel.METHOD; break;
	  case 7: debugLevel = DebugLevel.CLASS_NAME; break;
	  case 8: debugLevel = DebugLevel.RESULT; break;
	}
    }

    public static void setDebugValue (DebugLevel levelIn) {
	debugLevel = levelIn;
    }

    // @return None
    public static void writeMessage (String  message  ,
                                     DebugLevel levelIn ) {
	if (levelIn == debugLevel)
	    System.out.println(message);
    }

    /**
	 * @return String
	 */
    public String toString() {
	return "Debug Level is " + debugLevel;
    }
}
