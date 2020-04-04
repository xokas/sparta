package com.sparta.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.message.builders.LoadBatchBuilder;
import com.sparta.message.objects.Record;

@RestController
public class MainController {

	@RequestMapping("/")
	public String index() {
		return "Hola mundo!";
	}
	long total;
	
	@PostMapping("/load/{provider}")
	public int load(@PathVariable("provider") String provider, @RequestBody byte[] content) throws IOException {
		//int result = -1;
		LoadBatchBuilder builder = new LoadBatchBuilder(content);
		List<Record> list = builder.construct();
		this.total = list.size();
		
		return (int) this.total;
	}

	@GetMapping("/data/{provider}/total")
	public int total(@PathVariable("provider") String provider) {
		int result = -1;
		
		System.out.println("total: " + this.total);
		result = (int) this.total;
		
		return result;
	}

}
