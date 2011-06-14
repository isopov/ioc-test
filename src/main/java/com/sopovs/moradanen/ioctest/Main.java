package com.sopovs.moradanen.ioctest;

import javax.inject.Inject;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.injectors.AnnotatedFieldInjection;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class Main {
	public static void main(String[] args) {
		// Spring
		ApplicationContext appConext = new AnnotationConfigApplicationContext("com.sopovs.moradanen.ioctest");
		System.out.println("Invoking from Spring");
		appConext.getBean(MainBean.class).requestDep();

		// Guice
		Injector injector = Guice.createInjector(new Module() {
			@Override
			public void configure(Binder binder) {
				binder.bind(MainBean.class);
				binder.bind(DependencyBean.class);
			}
		});
		System.out.println("Invoking from Guice");
		injector.getInstance(MainBean.class).requestDep();

		// PicoContainer
		MutablePicoContainer pico = new DefaultPicoContainer(new AnnotatedFieldInjection(Inject.class));
		pico.addComponent(MainBean.class);
		pico.addComponent(DependencyBean.class);
		System.out.println("Invoking from Pico");
		pico.getComponent(MainBean.class).requestDep();
	}
}
