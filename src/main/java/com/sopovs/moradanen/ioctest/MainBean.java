package com.sopovs.moradanen.ioctest;

import javax.inject.Inject;
import javax.inject.Named;

//@Named annotation is used only by Spring. Actually it is used not for its main 
//purpose (to give a name to the bean), but simply as an analog of the @Component Spring annotation  
@Named
public class MainBean {
	@Inject
	private DependencyBean dep;

	public String requestDep() {
		return dep.doSmth();
	}
}
