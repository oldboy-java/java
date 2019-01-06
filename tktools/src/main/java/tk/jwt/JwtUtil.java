package tk.jwt;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.joda.time.DateTime;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;

import static java.security.KeyRep.Type.SECRET;


/***
 * Jwt工具类：处理生成token\解析token
 */
public class JwtUtil {

	private static String secret="www.cnccss.cn*+[09xddd";  //定义秘钥
	private static String issuer="www.cnccss.cn";//定义颁发者
	private static final  String TOKEN_NAME="token";


	 public static  class Builder{
	 	 private String secret;
		 private String issuer;

		 public Builder secret(String secret){
		 	 this.secret = secret;
			 return this;
		 }

		 public Builder issuer(String issuer){
			 this.issuer = issuer;
			 return this;
		 }

		 public JwtUtil build(){
		 	return new JwtUtil(this);
		 }
	}

	private JwtUtil(Builder builder){
		this.secret = builder.secret;
		this.issuer = builder.issuer;
	}


	/**
	 * 获取签名信息：包括token和失效时间
	 * @param claims 公共申明参数信息
	 * @return
	 */
	public static Map<String,String> getSign(Map<String,String> claims) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("alg", "HS256");
		map.put("type", "JWT");
		Algorithm algorithm = null;
		try {
			algorithm = Algorithm.HMAC256(SECRET);//根据秘钥构造算法
		} catch (IllegalArgumentException  e) {
			e.printStackTrace();
		} 
		DateTime dateTime = new DateTime();
		dateTime = dateTime.plusDays(7);//设置过期时间7天
		//dateTime = dateTime.plusMinutes(60);//设置过期时间为60分钟
		JWTCreator.Builder builder = JWT.create().withHeader(map).withIssuer(ISSUER).withExpiresAt(dateTime.toDate());//生成JWTCreator
		claims.forEach((k,v)->builder.withClaim(k, v));//设置claims
		String sign = builder.sign(algorithm).toString();//生成签名

		Map<String, String> result = Maps.newHashMap();
		result.put("token", sign);
		result.put("expires", dateTime.toDate().getTime()+"");
		return result;
	}
	
	
	/**
	 * 验证Token，返回Claims
	 * @param token 
	 * @return
	 */
	public static Map<String,String> verifyToken(String token) throws Exception{
		Algorithm  algorithm = Algorithm.HMAC256(SECRET); //根据秘钥构造算法
		JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();//生成JWTCreator
		DecodedJWT dj = null;
		try {
			dj = verifier.verify(token);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TokenExpireException("凭证已过期");
		}
		
		Map<String,Claim> claims = dj.getClaims();//获取Claims
		Map<String,String> resultMap = Maps.newHashMap();//构造Map集合
		claims.forEach((k,v)->resultMap.put(k, v.asString()));//将Claims转成Map
		resultMap.put("expires", dj.getExpiresAt().getTime()+"");
		return resultMap;//返回结果
	}
}

