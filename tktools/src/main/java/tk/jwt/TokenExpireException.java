package tk.jwt;

/***
 * Token失效异常
 */
public class TokenExpireException extends RuntimeException {

	public TokenExpireException() {}

	public TokenExpireException(String message) {
		super(message);
	}
	
}
