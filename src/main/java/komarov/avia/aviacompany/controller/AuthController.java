package komarov.avia.aviacompany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import komarov.avia.aviacompany.entity.dto.JwtAuthenticationResponse;
import komarov.avia.aviacompany.entity.dto.SignInRequest;
import komarov.avia.aviacompany.entity.dto.SignUpRequest;
import komarov.avia.aviacompany.service.AuthenticationService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @GetMapping("/sign-up")
    public String showSignUpForm() {
        return "sign-up"; // Return the Thymeleaf template for sign-up
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute @Valid SignUpRequest request) {
        JwtAuthenticationResponse response = authenticationService.signUp(request);
        return "redirect:/auth/sign-in"; // Redirect to login after successful signup
    }

    @GetMapping("/sign-in")
    public String showSignInForm() {
        return "sign-in"; // Return the Thymeleaf template for sign-in
    }

    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute @Valid SignInRequest request) {
        JwtAuthenticationResponse response = authenticationService.signIn(request);
        return "redirect:/index"; // Redirect to home after successful login
    }
}