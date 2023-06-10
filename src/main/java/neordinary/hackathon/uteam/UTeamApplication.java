package neordinary.hackathon.uteam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class UTeamApplication {

    public static void main(String[] args) {
        SpringApplication.run(UTeamApplication.class, args);
    }

}
