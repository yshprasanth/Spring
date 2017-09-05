package core.autowire;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CircularDepB {

    private Logger logger = LogManager.getLogger(getClass());

    private CircularDepA beanA;

    public CircularDepB() {
        logger.info("constructor 1..");
    }

    public CircularDepA getBeanA() {
        return beanA;
    }

    @PostConstruct
    public void init() {
        logger.info("init ..");
        beanA.setBeanB(this);
    }

    @Autowired
    public void setBeanA(CircularDepA beanA) {
        logger.info("setter..");
        this.beanA = beanA;
    }

    @Override
    public String toString() {
        return "CircularDepB{" +
                "beanA=" + beanA.getClass() +
                '}';
    }


}
