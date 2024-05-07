package com.swd.agri.fmt.impl;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.swd.agri.fmt.FmtTransform;

@Component
public class FmtTransformImpl implements FmtTransform {
	
	@Override
	public Map<String, Object> transformToMap(Object obj) {
		
		return Arrays.stream(obj.getClass().getDeclaredFields())
				.peek(field ->field.setAccessible(true))
				.collect(Collectors.toMap(Field::getName, field -> {
					try {
						return field.get(obj);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						return null;
					}
				}));
		
	}
	
	
	
}
