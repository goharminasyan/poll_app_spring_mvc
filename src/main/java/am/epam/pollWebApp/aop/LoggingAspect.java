package am.epam.pollWebApp.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @After("execution(* am.epam.pollWebApp.controller.UsersLoginController.openLoginPage(..))")
    public void afterUserLoginExecution() {
        logger.info("Login page is open");
    }

    @AfterReturning("execution(* am.epam.pollWebApp.controller.UsersRegisterController.openRegisterPage(..))")
    public void afterUserRegisterExecution() {
        logger.info("Registration page is open");
    }

    @After("execution(* am.epam.pollWebApp.controller.PollsController.*(..))")
    public void afterPollsExecution() {
        logger.info("The polls page has been opened");
    }

    @AfterReturning("execution(* am.epam.pollWebApp.controller.LogoutController.*(..))")
    public void logout() {
        logger.info("The user logged out");
    }
}
