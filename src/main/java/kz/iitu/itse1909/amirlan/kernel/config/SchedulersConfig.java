package kz.iitu.itse1909.amirlan.kernel.config;

import kz.iitu.itse1909.amirlan.application.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@EnableAsync
public class SchedulersConfig {
    private UserRepository userRepository;

    public SchedulersConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Scheduled(fixedDelay = 2000L, initialDelay = 3000L)
    public void showUsersCount() {
        System.out.println("Users count: " + userRepository.count());
    }

    @Scheduled(fixedRate = 5000L)
    public void showUsers() {
        System.out.println("Users: " + userRepository.findAll());
    }

    @Scheduled(cron = "0 * * * * *")
    public void cronTask() {
        // Every minute
        System.out.println("Some");
        System.out.println("Stuff");
        System.out.println("Going");
        System.out.println("On");
        System.out.println("Here");
    }

    @Scheduled(fixedDelayString = "${customTime}")
    public void parametrizedTask() {
        System.out.println("Stuff from parametrizedTask");
    }

    @Async
    @Scheduled(cron = "0 * * * * *")
    public void firstParallelTask() throws InterruptedException {
        for(int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println("Stuff from first task");
        }
    }

    @Async
    @Scheduled(cron = "0 * * * * *")
    public void secondParallelTask() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(500);
            System.out.println("Stuff from second task");
        }
    }
}
