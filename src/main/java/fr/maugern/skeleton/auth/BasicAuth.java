package fr.maugern.skeleton.auth;

import javax.xml.bind.DatatypeConverter;

public class BasicAuth {
	
	/*
	 * Utility classes should not have public constructors
	 */
	private BasicAuth() {
		throw new IllegalAccessError("Utility class");
	}
	
	public static String[] decode(String auth) {

		byte[] decodedBytes = DatatypeConverter.parseBase64Binary(extractBase64(auth));

		if (decodedBytes == null || decodedBytes.length == 0) {
			return null;
		}

		return new String(decodedBytes).split(":", 2);
	}

	private static String extractBase64(String auth) {
		return auth.replaceFirst("[B|b]asic ", "");
	}
}
