package kz.iitu.itse1909.amirlan.kernel.config;

//import com.atomikos.icatch.config.UserTransactionService;
//import com.atomikos.icatch.config.UserTransactionServiceImp;
//import com.atomikos.icatch.jta.UserTransactionImp;
//import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.Properties;

//@Configuration
//@EnableTransactionManagement
//@ComponentScan(basePackages = "kz.iitu.itse1909.amirlan.application.user.service")
public class GlobalTransactionsConfig {

//    @Bean
//    public UserTransactionService userTransactionService() {
//        System.out.println("TESTINGS");
//        Properties atProps = new Properties();
//        atProps.put("com.atomikos.icatch.service",
//                "com.atomikos.icatch.standalone.UserTransactionServiceFactory");
//        return new UserTransactionServiceImp(atProps);
//    }
//
//    @Bean
//    @DependsOn("userTransactionService")
//    public UserTransactionManager atomikosTransactionManager(){
//        UserTransactionManager utm = new UserTransactionManager();
//        utm.setStartupTransactionService(false);
//        utm.setForceShutdown(true);
//        return utm;
//    }
//
//    @Bean
//    @DependsOn("userTransactionService")
//    public UserTransaction userTransaction(){
//        UserTransactionImp ut = new UserTransactionImp();
//        try {
//            ut.setTransactionTimeout(300);
//        } catch (SystemException se) {
//            return null;
//        }
//        return ut;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(){
//        JtaTransactionManager ptm = new JtaTransactionManager();
//        ptm.setTransactionManager(atomikosTransactionManager());
//        ptm.setUserTransaction(userTransaction());
//        ptm.setAllowCustomIsolationLevels(true);
//        return ptm;
//    }
}
