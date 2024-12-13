package org.example.practice_board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PracticeBoardApplication {

  public static void main(String[] args) {
    SpringApplication.run(PracticeBoardApplication.class, args);
  }

}
