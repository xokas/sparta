package com.sparta.controller;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sparta.message.builders.LoadBatchBuilder;
import com.sparta.message.objects.Record;
import com.sparta.service.MainService;

@RestController
public class MainController {

	public static final int REST_ERROR = -1;
	
	static Logger log = LogManager.getLogger(MainController.class);
	
	@RequestMapping("/")
	public String index() {
		return "Hola mundo!";
	}
	
	@Autowired
	private MainService mainService;
	
	@PostMapping("/load/{provider}")
	public int load(@PathVariable("provider") String provider, @RequestBody byte[] content) throws IOException {
		int result = REST_ERROR;
		log.info("Request from {}", provider);
		try {
			LoadBatchBuilder builder = new LoadBatchBuilder(content);
			List<Record> list = builder.construct();
			result = this.mainService.store(provider, list);
		}catch(Throwable e) {
			log.error("Error en el servicio load provider ", e);
		}
		log.info("Response to {} is {} ", provider, result);
		return result;
	}

	@GetMapping("/data/{provider}/total")
	public int total(@PathVariable("provider") String provider) {
		int result = REST_ERROR;
		log.info("Request from {}", provider);
		try {
			result = this.mainService.findByProvider(provider);
		}catch(Throwable e) {
			log.error("Error en el servicio total provider ", e);
		}
		log.info("Response to {} is {} ", provider, result);
		return result;
	}

}
