package spms.bind;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;


//클라어인트가 보낸 매개변수 값을 자바 객체에 담아 주는 역할을 수행합니다.
public class ServletRequestDataBinder {
	
	//프런트 컨트롤러에서 호출한느 메서드입니다.
	//요청 매개변수의 값과 데이터 이름, 데이터 타입을 받아서 데이터 객체(member,string,date,integer)를 만드는 일을 함
	public static Object bind(HttpServletRequest request, Class<?> dataType, String dataName) throws Exception {
		// TODO Auto-generated method stub
		
		//dataType 기본 타입인지 아닌지 검사
		//만약 기본 타입이라면 즉시 객체를 생성하여 반환
		if (isPrimitiveType(dataType)) {
			return createValueObject(dataType, request.getParameter(dataName));

		}
		Set<String> paramNames = request.getParameterMap().keySet();
		Object dataObject = dataType.newInstance();
		Method m = null;

		for (String paramName : paramNames) {
			m = findSetter(dataType, paramName);
			if (m != null) {
				m.invoke(dataObject, createValueObject(m.getParameterTypes()[0], request.getParameter(paramName)));
			}
		}
		return dataObject;
	}

	private static boolean isPrimitiveType(Class<?> type) {
		// TODO Auto-generated method stub
		if (type.getName().equals("int") || type == Integer.class || type.getName().equals("long") || type == Long.class
				|| type.getName().equals("float") || type == Float.class || type.getName().equals("double")
				|| type == Double.class || type.getName().equals("boolean") || type == Boolean.class
				|| type == Date.class || type == String.class) {
			return true;
		}
		return false;

	}
	
	//기본 타입의 객체 생성할 때 호출
	private static Object createValueObject(Class<?> type, String value) {
		if (type.getName().equals("int") || type == Integer.class) {
			return new Integer(value);
		} else if (type.getName().equals("float") || type == Float.class) {
			return new Float(value);
		} else if (type.getName().equals("double") || type == Double.class) {
			return new Double(value);
		} else if (type.getName().equals("long") || type == Long.class) {
			return new Long(value);
		} else if (type.getName().equals("boolean") || type == Boolean.class) {
			return new Boolean(value);
		} else if (type == Date.class ) {
			return java.sql.Date.valueOf(value);
		} else {
			return value;
		}
	}

	private static Method findSetter(Class<?> type, String name) {
		Method[] methods = type.getMethods();

		String propName = null;
		for (Method m : methods) {
			if (!m.getName().startsWith("set"))
				continue;
			propName = m.getName().substring(3);
			if (propName.toLowerCase().equals(name.toLowerCase())) {
				return m;
			}
		}
		return null;
	}

}
