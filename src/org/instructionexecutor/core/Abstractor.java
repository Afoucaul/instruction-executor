package org.instructionexecutor.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Abstractor {
	protected static Abstractor instance;
	
	protected Abstractor() {
		if (instance == null)
			instance = this;
	}
	
	private static boolean isOperation(Method method) {
		Annotation[] annotations = method.getDeclaredAnnotations();

		for(Annotation annotation: annotations){
		    if(annotation instanceof Operation){
		    	return true;
		    }
		}
		
		return false;
	}
	
	private static Class getCurrentClass() {
		return instance.getClass();
	}
	
	public static List<Method> getOperations() {
		List<Method> operations = new ArrayList<Method>();
		for (Method method: getCurrentClass().getMethods()) {
			if (isOperation(method)) {
				operations.add(method);
			}
		}
		
		return operations;
		
		/*
		Iterator<Method> it = operations.iterator();
		while (it.hasNext()) {
			Method method = it.next();
			if (!isOperation(method))
				it.remove();
		}
		
		return (Method[]) operations.toArray();
		*/
	}
	
	public static void printMethods(Method[] methods) {
		for (Method method: methods) {
			StringBuilder sb = new StringBuilder();
			sb.append(method.getModifiers());
			sb.append(" ");
			sb.append(method.getReturnType());
			sb.append(" ");
			sb.append(method.getName());
			sb.append("(");
			Class[] parameters = method.getParameterTypes();
			if (parameters.length > 0) {
				sb.append(parameters[0].getName());
				for (Class parameter: Arrays.copyOfRange(parameters, 1, parameters.length)) {
					sb.append(parameter.getName());
					sb.append(";");
				}
			}
			sb.append(")");
			
			System.out.println(sb.toString());
		}
	}
	
	
}
