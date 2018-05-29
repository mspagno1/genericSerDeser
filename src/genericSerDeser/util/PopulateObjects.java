package genericSerDeser.util;


import genericSerDeser.fileOperations.Logger;
import genericSerDeser.fileOperations.FileProcessor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class PopulateObjects{

	/**
     	* FileProccessor inputfile
     	*/
	private FileProcessor inputFile = null;
	
	//Data structure to hold first 
	ArrayList<First> firstList;
	ArrayList<Integer> firstCounter;
	
	//Data structures to hold second
	ArrayList<Second> secondList;
	ArrayList<Integer> secondCounter;	

	//Data structure
	ArrayList<Object> objectArray;

	//Count of the objects of each type and a different count of how many unique objects 
	
	public PopulateObjects(FileProcessor in){
		Logger.writeMessage("PopulateObjects constructor called ", Logger.DebugLevel.C_TOR);
		inputFile = in;
		//Initalialize data structures here
		firstList = new ArrayList<First>();
		firstCounter = new ArrayList<Integer>();
		
		secondList = new ArrayList<Second>();
		secondCounter = new ArrayList<Integer>();
		
		objectArray = new ArrayList<Object>();
	}

	//Reads line by line
	public void deserObjects(){
		String line  = inputFile.readOneLine();
		while(line != null){
			Logger.writeMessage("Current line being read is " + line, Logger.DebugLevel.INPUT_LINE);
			int start = line.indexOf(":") +1;
			int end = line.indexOf(">");
			String className = line.substring(start,end);
			//System.out.println(className);
			//Find out which object im creating
			Class cls = null;
			try{
				cls = Class.forName(className);
			}catch(ClassNotFoundException ex){
				System.err.println("Class :" + className + "could not be found therefore could not be created");
				ex.printStackTrace();
				System.exit(1);
			}finally{
		
			}
			line  = inputFile.readOneLine();

			Object obj = null;
			try{
				obj = cls.newInstance();
			}catch(InstantiationException iste){
				System.out.println("InstantiantionException : " + iste.getMessage());
				System.exit(1);	
			}catch(IllegalAccessException acc){
				System.out.println("IllegalAccessException : " + acc.getMessage());
				System.exit(1);	
			}finally { }
		
			Object result = null;

	
			while(!line.contains("/fqn")){
				Logger.writeMessage("Current line being read is " + line, Logger.DebugLevel.INPUT_LINE);
				
				

				String[] dataTokens = line.split("\\W+");
				Class[] signature = new Class[1];
				
				
				//System.out.println("New line being added here");
				//System.out.println(line);
						
				
				
				
				

				//Get the primitive data type so we can autobox it 
				String dataType = dataTokens[2];
				if(dataType.equals("int")){
					signature[0] = Integer.TYPE;
				}else if(dataType.equals("byte")){
					signature[0] = Byte.TYPE;
				}else if(dataType.equals("boolean")){
					signature[0] = Boolean.TYPE;
				}else if(dataType.equals("char")){
					signature[0] = Character.TYPE;
				}else if(dataType.equals("float")){
					signature[0] = Float.TYPE;
				}else if(dataType.equals("long")){
					signature[0] = Long.TYPE;
				}else if(dataType.equals("short")){
					signature[0] = Short.TYPE;
				}else if(dataType.equals("double")){
					signature[0] = Double.TYPE;
				}
							
				else if(dataType.equals("String")){
					//System.out.println("String type is :" + dataType.getClass());
					signature[0] = dataType.getClass();
				}
				
				
				//Get the method name we are calling
				String methodName = "set" + dataTokens[4];
				Method meth = null;
				//System.out.println("Method name :" + methodName);
				try{
					meth = cls.getMethod(methodName,signature);
				}catch(NoSuchMethodException me){
					System.out.println("No such method found " + me.getMessage());
					System.exit(1);
				}finally{ } 
				
				
				//Get the parameters of the object
				String valueStr = dataTokens[6];
				
				
				Object[] params = new Object[1];


				if(dataType.equals("int")){
					try{
						if(line.contains("-")){
							valueStr = "-"+valueStr;
						}
						Integer value = Integer.parseInt(valueStr);
						params[0] = new Integer(value);
					}catch (NumberFormatException e){
  						System.out.println("Cant convert to integer" + e.getMessage());
						System.exit(1);		
					}finally{}
				}else if(dataType.equals("byte")){
					try{
						if(line.contains("-")){
							valueStr = "-"+valueStr;
						}
						Byte value = Byte.parseByte(valueStr);
						params[0] = new Byte(value);
					}catch (NumberFormatException eb){
  						System.out.println("Cant convert to byte" + eb.getMessage());
						System.exit(1);		
					}finally{}
				}else if(dataType.equals("boolean")){
					Boolean value = Boolean.valueOf(valueStr);
					params[0] = new Boolean(value);
				}else if(dataType.equals("char")){
					try{
						Character value = valueStr.charAt(0);
						params[0] = new Character(value);
					}catch (IndexOutOfBoundsException ce){
  						System.out.println("Cant convert to character" + ce.getMessage());
						System.exit(1);		
					}finally{}
				}else if(dataType.equals("float")){
					try{
						if(line.contains("-")){
							valueStr = "-"+valueStr;
						}
						Float value = Float.parseFloat(valueStr +"." + dataTokens[7]);
						params[0] = new Float(value);
					}catch (NumberFormatException e){
  						System.out.println("Cant convert to float" + e.getMessage());
						System.exit(1);		
					}finally{}
				}else if(dataType.equals("long")){
					try{
						if(line.contains("-")){
							valueStr = "-"+valueStr;
						}
						Long value = Long.parseLong(valueStr);
						params[0] = new Long(value);
					}catch (NumberFormatException e){
  						System.out.println("Cant convert to long" + e.getMessage());
						System.exit(1);		
					}finally{}
				}else if(dataType.equals("short")){
					try{
						if(line.contains("-")){
							valueStr = "-"+valueStr;
						}
						Short value = Short.parseShort(valueStr);
						params[0] = new Short(value);
					}catch (NumberFormatException e){
  						System.out.println("Cant convert to short" + e.getMessage());
						System.exit(1);		
					}finally{}
				}else if(dataType.equals("double")){
					try{
						if(line.contains("-")){
							valueStr = "-"+valueStr;
						}
						Double value = Double.parseDouble(valueStr);
						params[0] = new Double(value);
					}catch (NumberFormatException e){
  						System.out.println("Cant convert to double" + e.getMessage());
						System.exit(1);		
					}finally{}
				}else if(dataType.equals("String")){
					params[0] = new String(valueStr);
				}

				//Invoke the method
				try{
					result = meth.invoke(obj,params);
				}catch(IllegalAccessException ile){
					System.out.println("Illegal access: " + ile.getMessage());
					System.exit(1);
				}catch (InvocationTargetException e){
      				        System.out.println("Invocation target exception: " + e.getMessage());
                                        System.out.println("Exception: " + e.getTargetException().getMessage());
                                        System.exit(1);
                                }finally{}

				line  = inputFile.readOneLine();
			}
			Logger.writeMessage("Current line being read is " + line, Logger.DebugLevel.INPUT_LINE);
			//Add to list if not clone
			//System.out.println("ClassName were checking is " + className);
			if(className.contains("First")){
				Logger.writeMessage("First object toString " + "\n" + ((First)obj).toString() +"\n", Logger.DebugLevel.OBJECT_DATA);
				boolean duplicate = false;
				int dupSpot = 0;
				for(int i = 0; i < firstList.size();i++){
					if((firstList.get(i)).equals(obj)){
						duplicate = true;
						dupSpot = i;
						break;
					}
				}
				if(true == duplicate){
					int newValue = firstCounter.get(dupSpot) + 1;
					firstCounter.set(dupSpot,newValue);
				}
				else{
					firstList.add((First)obj);
					firstCounter.add(1);
				}
				objectArray.add((First)obj);
			}
			else if(className.contains("Second")){
				Logger.writeMessage("Second object toString " + "\n" + ((Second)obj).toString() + "\n", Logger.DebugLevel.OBJECT_DATA);
				boolean duplicate = false;
				int dupSpot = 0;
				for(int i = 0; i < secondList.size();i++){
					if((secondList.get(i)).equals(obj)){
						duplicate = true;
						dupSpot = i;
						break;
					}
				}
				if(true == duplicate){
					int newValue = secondCounter.get(dupSpot) + 1;
					secondCounter.set(dupSpot,newValue);
				}
				else{
					secondList.add((Second)obj);
					secondCounter.add(1);
				}
				objectArray.add((Second)obj);
			}	
			
			//Two whiles for each object that keep checking for until they hit a /fqn
			line  = inputFile.readOneLine();
		}
		int totalFirst = 0;
		for(int i = 0; i < firstCounter.size();i++){
			totalFirst = totalFirst + firstCounter.get(i);
		}
		int totalSecond = 0;
		for(int i = 0; i < secondCounter.size();i++){
			 totalSecond = totalSecond + secondCounter.get(i);
		}
		//System.out.println("Number of unique First objects is: " + firstList.size());
		//System.out.println("Total number of First objects: " + totalFirst);
		
		//System.out.println("Number of unique Second objects is: " + secondList.size());
		//System.out.println("Total number of Second objects: " + totalSecond);
		
		Logger.writeMessage("\nDuplcates for first objects: \n", Logger.DebugLevel.COUNTER);
		for(int i = 0; i < firstCounter.size();i++){
			Logger.writeMessage("Number of duplicates the object stored at location " + i + " is : " + firstCounter.get(i), Logger.DebugLevel.COUNTER);
		}
		Logger.writeMessage(" \nDuplcates for second objects: \n", Logger.DebugLevel.COUNTER);
		for(int i = 0; i < secondCounter.size();i++){
			Logger.writeMessage("Number of duplicates the object stored at location " + i + " is : " + secondCounter.get(i), Logger.DebugLevel.COUNTER);
		}
		

	}
	

	public ArrayList<Object> getObjectList(){
		return objectArray;
	}

}
