package com.sparta.message.builders;

import java.util.Random;
import com.sparta.utils.Converters;
import com.sparta.utils.Utils;

public class StringBuilder extends MessageBuilder<String>{
		
	public StringBuilder(byte[] array, int pointer) {
		super(array, pointer);
	}
	
	@Override 
	public String construct() {
		IntegerBuilder builder;
		Integer size;
		builder = new IntegerBuilder(this.getArray(), this.getPointer());
		size = builder.construct();
		this.setPointer(builder.getPointer());
		
		return Converters.convertByteArrayToString(this.getData(size));
	}

	public static String constructTestData(Random ran) {
		byte[] array = new byte[7];
	    new Random().nextBytes(array);
	    String result = Converters.convertByteArrayToString(array);
		return result;
	}

	public static byte[] constructMessage(String id) {
		byte[] result;
		byte[] sizeArray;
		byte[] stringArray;
		
		stringArray = Converters.convertStringToByteArray(id);
		sizeArray = IntegerBuilder.constructMessage(stringArray.length);
		result = Utils.mergeArrays(sizeArray, stringArray);
			
		return result;
	}
}
