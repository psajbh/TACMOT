package gov.va.vba.vbms.common.framework.performance.impl;

public final class Util {
    private Util() {
    }

	public static String trimField(String field, int max) {
		if(null != field){
			int len = field.length();
			if(len > max){
				return field.substring(0, max);
			}
		}
		return field;
	}

}
