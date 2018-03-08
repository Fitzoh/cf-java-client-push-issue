package com.example.cfjavaclientpushissue;

import org.cloudfoundry.operations.CloudFoundryOperations;
import org.cloudfoundry.operations.applications.PushApplicationRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class CfJavaClientPushIssueApplication implements CommandLineRunner {

    private final CloudFoundryOperations cfOperations;

    public CfJavaClientPushIssueApplication(CloudFoundryOperations cfOperations) {
        this.cfOperations = cfOperations;
    }

    public static void main(String[] args) {
        SpringApplication.run(CfJavaClientPushIssueApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Path path = Paths.get("src/main/web");
        PushApplicationRequest pushApplicationRequest = PushApplicationRequest.builder()
                .name("whatever")
                .path(path)
                .build();
        cfOperations.applications().push(pushApplicationRequest).block();
    }

}
