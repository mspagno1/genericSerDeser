package genericSerDeser.util;

import genericSerDeser.fileOperations.Logger;


public class First{
	private boolean BooleanValue;
	private byte ByteValue;
	private char CharValue;
	private double DoubleValue;
	private float FloatValue;
	private int IntValue;
	private long LongValue;
	private short ShortValue;
	private String StringValue;

	public First(){
		Logger.writeMessage("First constructor called ", Logger.DebugLevel.C_TOR);
		StringValue = "";
		
	}
	@Override
	public boolean equals(Object obj){
		boolean sameObj = true;
		
		/*
		System.out.println("=-=--=-=Equals overide=-=--==-");
		String str1 = toString();
		String str2 = obj.toString();
		
		System.out.println("Str 1 " + "\n" + str1);

		System.out.println("Str 2 " + "\n" + str2);
		*/

		final First otherObj = (First) obj;		

		if(ByteValue != otherObj.getByteValue()){
			sameObj = false;
		}
		if(ShortValue != otherObj.getShortValue()){
			sameObj = false;
		}
		if(IntValue != otherObj.getIntValue()){
			sameObj = false;
		}
		if(LongValue != otherObj.getLongValue()){
			sameObj = false;
		}
		if(FloatValue - otherObj.getFloatValue() > (float)1.0){
			sameObj = false;
		}
		if(DoubleValue - otherObj.getDoubleValue() > (double)1.0){
			sameObj = false;
		}
		if(BooleanValue != otherObj.getBooleanValue()){
			sameObj = false;
		}
		if(CharValue != otherObj.getCharValue()){
			sameObj = false;
		}
		if(!StringValue.equals(otherObj.getStringValue())){
			sameObj = false;
		}
		return sameObj;
	}
	@Override
	public int hashCode(){
		int result = 19;
		result = 40 * result; 
		result = 5 * ByteValue; 
		result = 6 + ShortValue + IntValue + (int)DoubleValue; 
		result = result * 19 + (int)FloatValue + (int)LongValue;
		return result;
	}
	
	//Getters and setters for the function
	
	//Getters
	public boolean getBooleanValue(){
		return BooleanValue;
	}

	public byte getByteValue(){
		return ByteValue;
	}

	public char getCharValue(){
		return CharValue;
	}
	
	public double getDoubleValue(){
		return DoubleValue;
	}

	public float getFloatValue(){
		return FloatValue;
	}

	public int getIntValue(){
		return IntValue;
	}

	public long getLongValue(){
		return LongValue;
	}

	public short getShortValue(){
		return ShortValue;
	}

	public String getStringValue(){
		return StringValue;
	}

	//Setters
	public void setByteValue(byte bVal){
		ByteValue = bVal;
	}

	public void setShortValue(short shVal){
		ShortValue = shVal;
	}

	public void setIntValue(int inVal){
		IntValue = inVal;
	}
	
	public void setLongValue(long lVal){
		LongValue = lVal;
	}

	public void setFloatValue(float fVal){
		FloatValue = fVal;
	}
	
	public void setDoubleValue(double dVal){
		DoubleValue = dVal;
	}

	public void setBooleanValue(boolean bVal){
		BooleanValue = bVal;
	}

	public void setCharValue(char cVal){
		CharValue = cVal;
	}

	public void setStringValue(String sVal){
		StringValue = sVal;
	}

	
	public String toString(){
		String data = "Byte " + ByteValue + "\n" + "ShortVal " + ShortValue + "\n" +"Int " + IntValue + "\n" +  "LongVal " + LongValue + "\n" + "FloatVal " + FloatValue + "\n" + "Double " + DoubleValue + "\n" + "Boolean " + BooleanValue + "\n" + "Char " + CharValue + "\n" + "String " + StringValue;
		return data;
	}

}
