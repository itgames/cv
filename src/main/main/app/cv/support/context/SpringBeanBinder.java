package app.cv.support.context;

import com.google.gwt.thirdparty.guava.common.collect.Lists;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author: sk_y
 */
public class SpringBeanBinder implements BeanFactoryAware {

    private static SpringBeanBinder instance=null;

    private ConfigurableListableBeanFactory beanFactory;

    public void init(){
        instance=this;
    }

    public void configureBean(Object bean){
       for (BeanPostProcessor postProcessor :  beanFactory.getBeansOfType(BeanPostProcessor.class).values()){
            postProcessor.postProcessBeforeInitialization(bean, null);
        }
        beanFactory.autowireBeanProperties(bean, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
        for (BeanPostProcessor postProcessor :  beanFactory.getBeansOfType(BeanPostProcessor.class).values()){
            postProcessor.postProcessAfterInitialization(bean, null);
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (!(beanFactory instanceof ConfigurableListableBeanFactory)){
            throw new IllegalArgumentException();
        }
        this.beanFactory=(ConfigurableListableBeanFactory)beanFactory;
    }

    public static final SpringBeanBinder getInstance(){
        if (instance==null){
            return new SpringBeanBinder();
        }
        return instance;
    }
}
