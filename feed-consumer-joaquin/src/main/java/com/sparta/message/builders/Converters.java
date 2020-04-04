package com.sparta.message.builders;

import java.nio.ByteBuffer;

public class Converters {

	public static Long convertByteArrayToLong(byte[] longBytes){
	    ByteBuffer byteBuffer = ByteBuffer.wrap(longBytes);
	    return Long.valueOf(byteBuffer.getLong());
	}
	
	public static Integer convertByteArrayToInt(byte[] intBytes){
	    ByteBuffer byteBuffer = ByteBuffer.wrap(intBytes);
	    return Integer.valueOf(byteBuffer.getInt());
	}
	
	public static String convertByteArrayToString(byte[] stringBytes) {
		String result = "";
		for (byte b : stringBytes) {
			result = result + b; 
	    }
		return result;
	}
}
