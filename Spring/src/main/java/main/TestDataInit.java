package main;

import main.repository.GroupRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class TestDataInit implements CommandLineRunner {

    @Autowired
    GroupRepository grpRep;

    @Autowired
    UserRepository usrRep;

    @Autowired
    PasswordEncoder pwdEncoded;

    @Override
    public void run(String... args) throws Exception {
//        grpRep.save(new Groups("Programmers"));

//        usrRep.save(new User("user",pwdEncoded.encode("pwd"), Collections.singletonList("ROLE_USER")));


    }
}
