package com.tk.annotation.app;

import java.lang.reflect.Field;

import com.tk.annotation.anno.Property;
import com.tk.annotation.anno.Table;

public class AnnotationTest {

	public static void main(String[] args) throws Exception {
		String sql = genUserQuerySql();
		System.err.println("sql=" + sql);
	}

	
	
	private static String  genUserQuerySql() throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ");
		Class cls = Class.forName("com.tk.annotation.domain.User");
		
		// 获取当前类上所有字段，不包含继承过来的字段
		Field fields[] = cls.getDeclaredFields();
		
		for (int i = 0 ;i < fields.length; i++ ) {
			Property property = fields[i].getDeclaredAnnotation(Property.class);
			builder.append(property.value());
			if (i < fields.length - 1) {
				builder.append(",");
			}
		}
		
		// 仅返回当前类上修饰的注解，不包含集成的注解
		Table table = (Table) cls.getDeclaredAnnotation(Table.class);
		builder.append(" FROM ").append(table.value());
		return builder.toString();
	}
}
