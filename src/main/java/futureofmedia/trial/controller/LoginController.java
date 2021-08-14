package futureofmedia.trial.controller;

import futureofmedia.trial.dto.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Belépés kezelő
 */
@RestController
public class LoginController {

    /**
     * Belépési pont az API-nál, jelszóval nem foglalkozik jelenleg, de kötelező megadni
     *
     * @param username felhasználónév
     * @param pwd      jelszó
     * @return Dolgozó adatai és a generált Token
     */
    @PostMapping("login")
    public UserDto login(@RequestParam("user") String username, @RequestParam("password") String pwd) {

        String token = getJWTToken(username);
        UserDto user = new UserDto();
        user.setUserName(username);
        user.setToken(token);
        return user;

    }

    /**
     * JWT token generálása
     *
     * @param username felhasználónév
     * @return token visszaadása
     */
    public String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}