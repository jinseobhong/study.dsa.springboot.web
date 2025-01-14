package study.springboot.web.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member/view")
@Controller
public class MemberViewController {
    @GetMapping(path = "create")
    public String create() {
        return "member/view/create";
    }
}