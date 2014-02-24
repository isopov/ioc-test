package com.sopovs.moradanen.ioctest;

import javax.inject.Named;

@Named
public class DependencyBean {
    public static final String VALUE = "Hello!";


	public String doSmth() {
		return VALUE;
	}
}
