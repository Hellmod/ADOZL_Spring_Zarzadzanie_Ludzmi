package pl.rafalmiskiewicz.ADOZL.rest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.rafalmiskiewicz.ADOZL.user.User;
import pl.rafalmiskiewicz.ADOZL.user.UserLogin;

import javax.ws.rs.GET;
import java.util.Date;

@RestController
public class LoginApi {

    @PostMapping("/api/login")
    public String getLogin(@RequestBody UserLogin user) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("roles", "user")
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + 60 * 60000))
                .signWith(SignatureAlgorithm.HS512, user.getPassword())
                .compact();
    }


}
