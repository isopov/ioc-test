package com.sopovs.moradanen.ioctest;

import javax.inject.Named;

@Named
public class DependencyBean {

	public void doSmth() {
		System.out.println("Hello!");
	}

}
