package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j

public class JWTUtil {
	// Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
	private final String JWT_SECRET = "lodaaaaaa";

	// Thời gian có hiệu lực của chuỗi jwt
	private final long JWT_EXPIRATION = 604800000L;

	// Tạo ra jwt từ thông tin user
	public String generateToken(String id) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
		// Tạo chuỗi json web token từ id của user.

		System.out.println(Jwts.builder().setSubject(id).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS256, JWT_SECRET).compact());
		return Jwts.builder().setSubject(id).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS256, JWT_SECRET).compact();
	}

	// Lấy thông tin user từ jwt
	public void getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
		System.out.println(claims.getSubject());
//		return Long.parseLong(claims.getSubject());
	}

	public boolean validateToken(String authToken) {
		Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
//        try {
//           
//            return true;
//        } catch (MalformedJwtException ex) {
//            log.error("Invalid JWT token");
//        } catch (ExpiredJwtException ex) {
//            log.error("Expired JWT token");
//        } catch (UnsupportedJwtException ex) {
//            log.error("Unsupported JWT token");
//        } catch (IllegalArgumentException ex) {
//            log.error("JWT claims string is empty.");
//        }
		return false;
	}

}
