package study.springboot.web;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class RequestParamController {

    // Single parameter(Default value)
    // @RequestParam(defaultValue = value) is set default value
    // @RequestParam(required = false) is input null value
    @GetMapping(value = "/requestParameter")
    public String requestParameter(@RequestParam(defaultValue = "default") String parameter) {
        log.info("Received parameter: { parameter : {} }", parameter);
        return "parameter: " + parameter;
    }

    // Multiple parameters
    @GetMapping(value = "/requestParameters")
    public String requestParameter(@RequestParam String parameter, @RequestParam String parameter2) {
        log.info("Received parameters: { parameter : {} },{ parameter2 : {} }", parameter, parameter2);
        return "parameter: " + parameter + ", parameter2: " + parameter2;
    }

    // 경로변수(Path Variable)
    @GetMapping(value = "/{id}")
    public String pathVariable(@PathVariable(name = "id") Long id) {
        log.info("id:{}", id);
        return id.toString();
    }
}
