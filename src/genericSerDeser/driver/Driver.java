
package genericSerDeser.driver;

import java.lang.IllegalArgumentException;
import genericSerDeser.fileOperations.FileProcessor;
import genericSerDeser.fileOperations.Logger;
import genericSerDeser.util.PopulateObjects;
import genericSerDeser.util.DMPL;
import genericSerDeser.util.First;

import java.util.ArrayList;


public class Driver{

	
	public static void main(String args[]) {
	    Driver dr = new Driver();
	    Logger logger = new Logger();
	    dr.validateArgs(args, logger);
	    
	    FileProcessor inputFile = new FileProcessor(args[0],true);
	    FileProcessor outputFile = new FileProcessor(args[1],false);

	    PopulateObjects objects = new PopulateObjects(inputFile);
	    objects.deserObjects();

	    ArrayList<Object> objectArray = objects.getObjectList();

	    DMPL dpml = new DMPL();
	
	    String outPutData;
	    outPutData = dr.processObjects(objectArray,dpml);
	    
	    //System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-==--==-=-=-=-=-=--=-=\n\n");
	    //System.out.println(outPutData);

	    outputFile.writeOneLine(outPutData);

	    inputFile.closeReadFile();
	    outputFile.closeWriteFile();

	} // end main(...)
	
	private void validateArgs(String args[], Logger logger){
		//validate number of arguments
		if(args.length==3){
		    // get file names

			int debugValue = 0;
			try{
				debugValue = Integer.parseInt(args[2]);
				logger.setDebugValue(debugValue);
			}catch(NumberFormatException e){
				System.err.println("Debugvalue is not an int");
				e.printStackTrace();
				System.exit(1);
			}finally{
				if(debugValue < 0 || debugValue > 8){
					System.err.println("Debug value is not between 0 and 8");
					System.exit(1);
				}
			}
		}else{
			System.err.println("Invalid number of arguments. Expected [FIXME: provide details here]");
			System.exit(1);
		}
		
		
	}
	
	public String processObjects(ArrayList<Object> objArray, DMPL dmpl){
		String retStr = "";
		for(int i = 0; i < objArray.size(); i++){
			String val = dmpl.createDPMLFormat(objArray.get(i));
			if(i != objArray.size() -1){
				retStr = retStr + val+"\n";
			}
			else{
				retStr = retStr + val;
			}	
		}
		//System.out.println(retStr);
		return retStr;

	}
	
} // end public class Driver

