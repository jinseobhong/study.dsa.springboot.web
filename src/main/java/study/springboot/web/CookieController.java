package study.springboot.web;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/cookies")
public class CookieController {

    private static final String COOKIE_NAME = "data";

    /**
     * 쿠키 설정
     */
    @GetMapping("/set")
    public String setCookie(HttpServletResponse response, @RequestParam(defaultValue = "default") String cookieValue) {
        Cookie cookie = new Cookie(COOKIE_NAME, cookieValue);
        cookie.setPath("/cookies");
        cookie.setHttpOnly(true); // 보안 향상: 자바스크립트 접근 제한
        cookie.setMaxAge(60 * 60); // 1시간 유효

        response.addCookie(cookie);
        log.info("Cookie '{}' has been set with value '{}'", COOKIE_NAME, cookieValue);

        return String.format("Cookie '%s' has been set with value '%s'", COOKIE_NAME, cookieValue);
    }

    /**
     * 쿠키 조회
     */
    @GetMapping("/get")
    public String getCookie(@CookieValue(name = COOKIE_NAME, required = false) String cookieValue) {
        if (cookieValue == null) {
            log.warn("Cookie '{}' not found", COOKIE_NAME);
            return String.format("Cookie '%s' not found", COOKIE_NAME);
        }

        log.info("Cookie '{}' has value '{}'", COOKIE_NAME, cookieValue);
        return String.format("Cookie '%s' has value '%s'", COOKIE_NAME, cookieValue);
    }

    /**
     * 쿠키 삭제
     */
    @GetMapping("/remove")
    public String removeCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(COOKIE_NAME, null);
        cookie.setPath("/cookies");
        cookie.setMaxAge(0); // 즉시 만료
        cookie.setHttpOnly(true); // 보안 향상

        response.addCookie(cookie);
        log.info("Cookie '{}' has been removed", COOKIE_NAME);

        return String.format("Cookie '%s' has been removed", COOKIE_NAME);
    }
}
