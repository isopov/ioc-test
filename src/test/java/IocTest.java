package com.sopovs.moradanen.ioctest;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.junit.Assert;
import org.junit.Test;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.injectors.AnnotatedFieldInjection;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

/**
 * Created by isopov on 2/24/14.
 */
public class IocTest {

    @Test
    public void testGuice() {
        Injector injector = Guice.createInjector(new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bind(MainBean.class);
                binder.bind(DependencyBean.class);
            }
        });
        assertEquals(DependencyBean.VALUE, injector.getInstance(MainBean.class).requestDep());
    }

    @Test
    public void testPicoContainer() {
        MutablePicoContainer pico = new DefaultPicoContainer(new AnnotatedFieldInjection(Inject.class));
        pico.addComponent(MainBean.class);
        pico.addComponent(DependencyBean.class);
        assertEquals(DependencyBean.VALUE, pico.getComponent(MainBean.class).requestDep());
    }

    @Test
    public void testSpring(){
        ApplicationContext appConext = new AnnotationConfigApplicationContext("com.sopovs.moradanen.ioctest");
        assertEquals(DependencyBean.VALUE, appConext.getBean(MainBean.class).requestDep());
    }
}
