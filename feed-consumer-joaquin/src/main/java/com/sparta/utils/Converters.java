package com.sparta.utils;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

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
		return new String(stringBytes, StandardCharsets.UTF_8);
	}
	
	public static byte[] convertStringToByteArray(String string) {
		return string.getBytes(StandardCharsets.UTF_8);
	}

	public static byte[] convertIntToByteArray(Integer number) {
	    return ByteBuffer.allocate(4).putInt(number).array();
	}
	
	public static byte[] convertLongToByteArray(Long number) {
	    return ByteBuffer.allocate(8).putLong(number).array();
	}
}
