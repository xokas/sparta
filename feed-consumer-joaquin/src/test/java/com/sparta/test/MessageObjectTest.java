package com.sparta.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sparta.SpartaApplication;
import com.sparta.message.objects.Sensor;
import com.sparta.message.objects.SensorCollection;

//@SpringBootTest
@SpringBootTest(classes={SpartaApplication.class})
public class MessageObjectTest {

	//private static Logger log = LogManager.getLogger(MessageObjectsTest.class);
	
	@Test
	void sensorCollectionConstructorShouldWork() {
		SensorCollection sensors = new SensorCollection(2);
		int max = 6;
		int min = 4;
		int average = (max + min) / 2;
		
		Sensor sMax = new Sensor("max",max);
		Sensor sMin = new Sensor("min",min);
		sensors.add(sMax);
		sensors.add(sMin);
		
		assertTrue(sensors.getMax().intValue() == max, "max");
		assertTrue(sensors.getMin().intValue() == min, "min");
		assertTrue(sensors.calculateAverage().intValue() == average, "average");
	}
	
}
