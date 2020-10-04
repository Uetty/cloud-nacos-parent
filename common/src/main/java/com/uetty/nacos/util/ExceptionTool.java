package com.uetty.nacos.util;

@SuppressWarnings("unused")
public class ExceptionTool {

	public static String getStackTraceOnPackage(String packageName, Throwable e) {
		StringBuilder sb = new StringBuilder();
		getStackTraceOnPackage0(packageName, e, sb);
		return sb.toString();
	}
	
	public static void printStackTraceOnPackage(String packageName, Throwable e) {
		StringBuilder sb = new StringBuilder();
		getStackTraceOnPackage0(packageName, e, sb);
		System.out.println(sb);
	}
	
	/**
	 * 只打印starnet有关的异常
	 */
	private static void getStackTraceOnPackage0(String packageName, Throwable e, StringBuilder sb) {
		if (e == null || sb == null) return;
		
		sb.append(e.getClass().getName()).append(" : ").append(e.getMessage()).append("\n");
		StackTraceElement[] stackTraces = e.getStackTrace();
		for (StackTraceElement trace : stackTraces) {
			if (trace.getClassName().startsWith(packageName)) {
				sb.append("\tat ").append(trace.toString()).append("\n");
			}
		}
		Throwable[] suppressed = e.getSuppressed();
		for (Throwable throwable : suppressed) {
			getStackTraceOnPackage0(packageName, throwable, sb);
		}
		Throwable cause = e.getCause();
		getStackTraceOnPackage0(packageName, cause, sb);
	}
}
