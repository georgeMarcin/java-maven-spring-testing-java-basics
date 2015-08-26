package org.mbiczak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Simple Spring application, which is autocinfigured and automatically searchers for application components,
 * int this case CharGrouperController.
 */
@EnableAutoConfiguration
@ComponentScan
public class CharGrouperApp {
    public static void main(String[] args) {
     SpringApplication.run(CharGrouperApp.class, args);
    }
}
