package genericSerDeser.util;

import genericSerDeser.fileOperations.Logger;


public class Second{
	private double DoubleValue;
	private double DoubleValue2;
	private long LongValue;
	private long LongValue2;
	private short ShortValue;
	private short ShortValue2;
	private String StringValue;
	
	
	

	public Second(){
		//Logger constructor here thats about it
		Logger.writeMessage("Second constructor called ", Logger.DebugLevel.C_TOR);
		StringValue = "";
	}

	@Override
	public boolean equals(Object obj){
		boolean sameObj = true;
		final Second otherObj = (Second) obj;
		
		/*
		System.out.println("=-=--=-=Equals overide=-=--==-");
		String str1 = toString();
		String str2 = otherObj.toString();
		
		System.out.println("Str 1 " + "\n" + str1);

		System.out.println("Str 2 " + "\n" + str2);		

		*/
		if(ShortValue != otherObj.getShortValue()){
			sameObj = false;
		}
		if(LongValue != otherObj.getLongValue()){
			sameObj = false;
		}
		if(DoubleValue - otherObj.getDoubleValue() > 1.0){
			sameObj = false;
		}
		if(!StringValue.equals(otherObj.getStringValue())){
			sameObj = false;
		}

		if(ShortValue2 != otherObj.getShortValue2()){
			sameObj = false;
		}

		if(LongValue2 != otherObj.getLongValue2()){
			sameObj = false;
		}

		if(DoubleValue2 - otherObj.getDoubleValue2() > 1.0){
			sameObj = false;
		}


		return sameObj;
	}
		
	@Override
	public int hashCode(){
		int result = 19;
		result = 40 * result; 
		result = 5 * ShortValue + ShortValue2; 
		result = 19 + (int)DoubleValue + (int)LongValue; 
		result = result * 15 + (int)DoubleValue2 + (int)LongValue2;
		return result;
	}

	//Getters

	public double getDoubleValue(){
		return DoubleValue;
	}

	public double getDoubleValue2(){
		return DoubleValue2;
	}

	public long getLongValue(){
		return LongValue;
	}

	public long getLongValue2(){
		return LongValue2;
	}

	public short getShortValue(){
		return ShortValue;
	}

	public short getShortValue2(){
		return ShortValue2;
	}

	public String getStringValue(){
		return StringValue;
	}

	//Setters

	public void setShortValue(short shVal){
		ShortValue = shVal;
	}

	public void setLongValue(long lVal){
		LongValue = lVal;
	}
	
	public void setDoubleValue(double dVal){
		DoubleValue = dVal;
	}

	public void setStringValue(String sVal){
		StringValue = sVal;
	}

	
	public void setShortValue2(short shVal){
		ShortValue2 = shVal;
	}
	

	public void setLongValue2(long lVal){
		LongValue2 = lVal;
	}

	public void setDoubleValue2(double dVal){
		DoubleValue2 = dVal;
	}

	public String toString(){
		String data = "ShortVal " + ShortValue + "\n"+ "ShortVal2 " + ShortValue2 + "\n" + "LongVal " + LongValue + "\n" + "Long2Val " + LongValue2 + "\n" + "\n" + "Double " + DoubleValue + "\n" + "Double2 " + DoubleValue2 + "\n" +"String " + StringValue + "\n";
		return data;
	}

}
