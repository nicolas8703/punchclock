package ch.zli.m223.punchclock.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder {

    @Autowired
    public DatabaseSeeder(){

    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        System.out.println("1111");
        System.out.println("1111");
        System.out.println("1111");
        System.out.println("1111");
        System.out.println("1111");
        System.out.println("1111");
        System.out.println("1111");
        System.out.println("1111");
        System.out.println("1111");

    }
}
