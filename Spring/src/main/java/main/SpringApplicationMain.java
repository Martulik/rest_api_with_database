package main;

import main.entity.Groups;
import main.repository.GroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringApplicationMain {


    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationMain.class,args);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


//    private static final Logger log = LoggerFactory.getLogger(SpringApplicationMain.class);
//    @Bean
//    public CommandLineRunner test(GroupRepository repository){
//        return args -> {
//
//            for (Groups grp: repository.findAll()){
//                log.info("The group is: "+ grp.toString());
//            }
//        };
//    }
}
