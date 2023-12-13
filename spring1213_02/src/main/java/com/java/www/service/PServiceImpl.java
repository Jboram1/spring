package com.java.www.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PServiceImpl implements PService {

	@Override
	public void execute() {
		System.out.println(" 'service버전1'을 호출합니다. ");

	}
}