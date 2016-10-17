package sql.meowlib.teamneko.org;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Transmuter {
	public static <T> T transmute(ResultSet rs, Class<T> clazz) {
		T result = null;
		
		try {
			result = clazz.getConstructor().newInstance();
			for(Field field : clazz.getDeclaredFields()) {
				Method setter = getSetter(clazz, field);
				Object sqlResult = getResult(rs, field);
				setter.invoke(result, sqlResult);
			}
				
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
		} catch (SQLException e) {
		}
		
		return result;
	}
	
	private static <T> Method getSetter(Class<T> clazz, Field field) throws NoSuchMethodException, SecurityException {
		String setterName = field.getName().substring(0,1).toUpperCase() + field.getName().substring(1);
		return clazz.getMethod("set" + setterName, field.getType());
	}
	
	private static Object getResult(ResultSet rs, Field field) throws SQLException {
		if(field.getType() == Boolean.class) {
			return rs.getBoolean(field.getName());
		} else if(field.getType() == Double.class) {
			return rs.getDouble(field.getName());
		} else if(field.getType() == Integer.class) {
			return rs.getInt(field.getName());
		} else if(field.getType() == Date.class) {
			return rs.getDate(field.getName());
		} else if(field.getType() == String.class) {
			return rs.getString(field.getName());
		}
		return null;
	}
}
