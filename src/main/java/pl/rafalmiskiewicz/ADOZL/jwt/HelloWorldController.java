package pl.rafalmiskiewicz.ADOZL.jwt;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.rafalmiskiewicz.ADOZL.hours.Hour;
import pl.rafalmiskiewicz.ADOZL.hours.HourService;
import pl.rafalmiskiewicz.ADOZL.jwt.models.AuthenticationRequest;
import pl.rafalmiskiewicz.ADOZL.jwt.models.AuthenticationResponse;
import pl.rafalmiskiewicz.ADOZL.jwt.util.JwtUtil;
import pl.rafalmiskiewicz.ADOZL.user.UserService;
import pl.rafalmiskiewicz.ADOZL.utilities.UserUtilities;

import java.util.List;

@RestController
class HelloWorldController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @RequestMapping({ "/api/hello" })
    public String firstPage() {
        return "Hello World";
    }

    @RequestMapping(value = "/api/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
