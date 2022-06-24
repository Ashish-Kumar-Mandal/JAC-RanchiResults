package com.qa.scripts;

import java.io.IOException;

import org.testng.annotations.Test;

import net.sourceforge.tess4j.TesseractException;

public class ISRO_SDSC_Login_Script extends BaseScript {

	@Test
	public void login() throws InterruptedException, TesseractException, IOException {
		isro.setUserId("21800062022019");
		isro.setPassword("06061996");
		isro.setCaptcha(isro.getCaptchaValue());
		
		System.out.println(isro.getCaptchaValue());
		
//		isro.goLogin();
	}
}
