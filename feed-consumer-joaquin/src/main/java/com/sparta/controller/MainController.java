package com.sparta.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@RequestMapping("/")
	public String index() {
		return "Hola mundo!";
	}
	long total;
	
	@PostMapping("/load/{provider}")
	public int load(@PathVariable("provider") String provider, @RequestBody byte[] content) throws IOException {
		int result = -1;
		byte[] resultArray = Arrays.copyOfRange(content, 0, 8);
		this.total = convertByteArrayToLong(resultArray);
//		System.out.println("load: " + this.total);
//		System.out.println(content.length);
//		
//		System.out.println(bigIntToByteArray(360).length);
		System.out.println(convertByteArrayToLong(resultArray));
		result = (int) this.total;
		return result;
	}

	@GetMapping("/data/{provider}/total")
	public int total(@PathVariable("provider") String provider) {
		int result = -1;
		
		System.out.println("total: " + this.total);
		result = (int) this.total;
		
		return result;
	}
	
	private static byte[] getSubArray(byte[] array, int init, int end) {
		byte[] result = new byte[(end - init)];
		int j = 0;
		
		for(int i = init ; i < end ; i++) {
			result[j++] = array[i];
		}
		
		return result;
	}
	
	private static int getInt64(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getInt();
	}
	
	public static int byteArrayToInt(byte[] b) 
	{
	    return   b[0] & 0xFF |
	            (b[1] & 0xFF) << 8 |
	            (b[2] & 0xFF) << 16 |
	            (b[3] & 0xFF) << 24;
	}
	
	private byte[] bigIntToByteArray( final int i ) {
	    BigInteger bigInt = BigInteger.valueOf(i);      
	    return bigInt.toByteArray();
	}
	
	private long convertByteArrayToLong(byte[] intBytes){
	    ByteBuffer byteBuffer = ByteBuffer.wrap(intBytes);
	    return byteBuffer.getLong();
	}

}
