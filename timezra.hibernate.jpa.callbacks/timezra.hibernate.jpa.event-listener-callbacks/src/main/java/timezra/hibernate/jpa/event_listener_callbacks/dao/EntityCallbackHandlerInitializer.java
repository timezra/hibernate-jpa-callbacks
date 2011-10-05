package timezra.hibernate.jpa.event_listener_callbacks.dao;

import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.annotations.common.reflection.ReflectionManager;
import org.hibernate.cfg.Configuration;
import org.hibernate.ejb.event.EntityCallbackHandler;
import org.hibernate.mapping.PersistentClass;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class EntityCallbackHandlerInitializer {

    @Resource
    private AnnotationSessionFactoryBean annotationSessionFactory;

    @Resource
    private EntityCallbackHandler entityCallbackHandler;

    @PostConstruct
    public void init() throws ClassNotFoundException {
        final Configuration configuration = annotationSessionFactory.getConfiguration();
        final ReflectionManager reflectionManager = configuration.getReflectionManager();
        final Iterator<PersistentClass> classMappings = configuration.getClassMappings();
        while (classMappings.hasNext()) {
            entityCallbackHandler.add(
                    reflectionManager.classForName(classMappings.next().getClassName(), this.getClass()),
                    reflectionManager);
        }
    }
}