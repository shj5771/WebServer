package com.example.kr.stinnovation.stdomainredirector.RedirectController;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;

@Controller
public class RedirectController {

    @RequestMapping("/**")
    public ResponseEntity<Void> redirect(HttpServletRequest request) {
        String uri   = request.getRequestURI();              // 예: /test
        String query = request.getQueryString();             // 예: page=2
        String target = "http://stinnovation.co.kr" + uri + (query != null ? "?" + query : "");

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(target));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY); // 301
    }
}
