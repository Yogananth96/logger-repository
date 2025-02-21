package com.Project.Dailymock;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.Project.Dailymock.Entity")

public class DailymockApplication {

	public static void main(String[] args) {
		 
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		SpringApplication.run(DailymockApplication.class, args);

		System.out.println("OpenCV Loaded Successfully: " + Core.VERSION);

		Mat mat = new Mat();
		System.out.println("OpenCV Mat loaded successfully: " + mat);
	}

}
