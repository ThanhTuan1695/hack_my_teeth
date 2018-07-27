package com.mgmsec.HackMyTeeth.HackMyTeeth.setting;

public class SecurityEnum {
	public static enum UseCookie { Base64, Secure };
	public static enum CookieParam { True, False };
	public static enum PwdStorage { Clear, Hashed, PBKDF}
	public static enum SessFix { Yes, No };
	public static enum LoginError { Yes, No, Timebased  };
	public static Boolean Sqli;
	public static enum ResetPassword { True, False };
}
