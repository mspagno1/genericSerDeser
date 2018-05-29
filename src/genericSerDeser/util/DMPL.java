package genericSerDeser.util;

import genericSerDeser.fileOperations.Logger;
import genericSerDeser.strategy.SerStrategy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;


//First Object

public class DMPL implements SerStrategy{

		
	public DMPL(){
		Logger.writeMessage("DMPL constructor called ", Logger.DebugLevel.C_TOR);
		
	}

	public String createDPMLFormat(Object obj){
		
		//GET FIELDS TO GET FIELDS NAME HAVE TO BE IN RIGHT ORDER
		String retStr = "";
		Class clsObj = obj.getClass();
		int objectNum = 0;
		
		ArrayList<String> typeList = new ArrayList<String>();
		ArrayList<String> fieldList = new ArrayList<String>();
		ArrayList<String> valueList = new ArrayList<String>();

		String className = clsObj + "";
		
		int split = className.indexOf(" ");
		className = className.substring(split+1);
		
		Logger.writeMessage("Class name of current object in DMPL" + className, Logger.DebugLevel.CLASS_NAME);
		
		retStr = retStr + "<fqn:" + className + ">\n";
				
		try {
        		Field f[] = clsObj.getDeclaredFields();


			boolean flag = true;
			
			//Sort the fields in alphical order as i do so with the methods incase we change fields order
			String temp;
			while(flag){				
				flag = false;
				for(int i = 0; i < f.length -1; i++){
					String temp1 = f[i].toString();
					int endTemp1 = temp1.lastIndexOf(".") +1;
					temp1 = temp1.substring(endTemp1);
					String temp2 = f[i+1].toString();
					int endTemp2 = temp2.lastIndexOf(".") +1;
					temp2 = temp2.substring(endTemp2);

					if(temp1.compareToIgnoreCase(temp2) > 0){
						Field oldVal = f[i];
						f[i] = f[i+1];
						f[i+1] = oldVal;
						flag = true;
					}
				}
			}

			//System.out.println("Number of fields is " + f.length);
			for(int i = 0; i < f.length;i++){
				String fieldName = "" + f[i];
				int endField = fieldName.lastIndexOf(".") +1;
				fieldName = fieldName.substring(endField);
				fieldList.add(fieldName);
				String typeName = "" + f[i].getType();
				int endType = typeName.lastIndexOf(".") +1;
				typeName = typeName.substring(endType);
				typeList.add(typeName);

				//System.out.println(fieldName);
				//System.out.println(typeName);
			}
        		
      		} catch (Exception e) {
        		 System.out.println("Exception: " + e);
			 System.exit(1);
      		}finally{}
  		
		try{
			Method m[] = clsObj.getMethods();
			//Sort arrays alphabetically
			
			boolean flag = true;
			
			String temp;
			while(flag){				
				flag = false;
				for(int i = 0; i < m.length -1; i++){
					String temp1 = m[i].toString();
					int endTemp1 = temp1.lastIndexOf(".") +1;
					temp1 = temp1.substring(endTemp1);
					String temp2 = m[i+1].toString();
					int endTemp2 = temp2.lastIndexOf(".") +1;
					temp2 = temp2.substring(endTemp2);

					if(temp1.compareToIgnoreCase(temp2) > 0){
						Method oldVal = m[i];
						m[i] = m[i+1];
						m[i+1] = oldVal;
						flag = true;
					}
				}
			}
			
			Logger.writeMessage("New Object Set of Methods", Logger.DebugLevel.METHOD);
			for(int i = 0; i < m.length;i++){
				
				if((m[i].toString()).contains("get") && !m[i].toString().contains("Class")){
					Object result = null;
					

					try{
						result = m[i].invoke(obj);
						Logger.writeMessage("Method name is " + m[i].toString(), Logger.DebugLevel.METHOD);
						
					}catch(IllegalAccessException ile){
						System.out.println("Illegal access: " + ile.getMessage());
						System.exit(1);
					}catch (InvocationTargetException e){
      						System.out.println("Invocation target exception: " + e.getMessage());
                              	  		System.out.println("Exception: " + e.getTargetException().getMessage());
                                		System.exit(1);
                       			}finally{}

					String value = "" + result;
					valueList.add(value);

				}
				
			}
		}catch(Exception e){
			System.out.println("Exception" + e.getMessage());
			System.exit(1);
		}finally{};
		
		Logger.writeMessage("\n", Logger.DebugLevel.METHOD);
	
		if(typeList.size() == valueList.size()){ 
			Logger.writeMessage("New Object Set", Logger.DebugLevel.FIELD_DATA);
			for(int i = 0; i <  typeList.size(); i++){
				Logger.writeMessage("Type " + typeList.get(i) + " VarName " + fieldList.get(i) + " Value: "+ valueList.get(i), Logger.DebugLevel.FIELD_DATA);

				retStr = retStr + "<type="+typeList.get(i)+", var="+fieldList.get(i)+", value="+valueList.get(i)+">\n";
			}
			Logger.writeMessage("\n", Logger.DebugLevel.FIELD_DATA);
		}else{
			System.out.println("Error mismatched fields with getters");
                        System.exit(1);
		}	
		
		retStr = retStr + "</fqn:" + className + ">";
		
		Logger.writeMessage("Result string of this object is: \n" + retStr +"\n", Logger.DebugLevel.RESULT);

		
		return retStr;
	}
}
