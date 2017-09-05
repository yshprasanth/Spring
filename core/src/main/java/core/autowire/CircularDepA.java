package core.autowire;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CircularDepA implements ApplicationContextAware, InitializingBean {

    private ApplicationContext context;

    public void afterPropertiesSet() throws Exception {
        logger.info("after properties set...");
        beanB = context.getBean(CircularDepB.class);
        logger.info("after properties set, beanB: " + beanB);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("set Application context...");
        this.context = applicationContext;
    }

    private Logger logger = LogManager.getLogger(getClass());

    private CircularDepB beanB;

    public CircularDepA() {
        logger.info("constructor 1..");
    }

    public CircularDepB getBeanB() {
        return beanB;
    }

    @Autowired
    public void setBeanB(CircularDepB beanB) {
        logger.info("setter..");
        this.beanB = beanB;
    }

    @Override
    public String toString() {
        return "CircularDepA{" +
                "beanB=" + beanB.getClass() +
                '}';
    }
}
