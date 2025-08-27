package com.mysite.sbb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SbbApplication {

	public static void main(String[] args) {

        Question q = new Question();
        List<Answer> answers = q.getAnswers();

		SpringApplication.run(SbbApplication.class, args);
	}

}
