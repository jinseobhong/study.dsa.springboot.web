package study.springboot.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import study.springboot.web.model.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class TemplateController {

    // 웹 브라우저에서 /text-basic url을 요청하면 text-basic.html을 보여준다
    @GetMapping(path = "/text-basic")
    public String viewTextBasic(Model model) {
        String message = "Hello Thymeleaf";
        model.addAttribute("data", message);
        return "thymeleaf/text-basic";
    }

    @GetMapping(path = "/text-unescaped")
    public String viewTextUnescaped(Model model) {
        model.addAttribute("data", "Hello <br>Thymeleaf!</b>");
        return "thymeleaf/text-unescaped";
    }

    @GetMapping(path = "variable")
    public String viewVariable(Model model) {
        Member memberA = new Member();
        memberA.setId("user1");
        memberA.setName("유저2");
        Member memberB = new Member();
        memberB.setId("user2");
        memberB.setName("유저2");
        model.addAttribute("userA", memberA);
        model.addAttribute("userB", memberB);
        List<Member> list = new ArrayList<>();
        list.add(memberA);
        list.add(memberB);
        model.addAttribute("users", list);
        Map<String, Member> userMap = new HashMap<>();
        userMap.put("memberA", memberA);
        userMap.put("memberB", memberB);
        model.addAttribute("users2", userMap);
        return "thymeleaf/variable";
    }

    @GetMapping(value = "date")
    public String date(Model model) {
        model.addAttribute("now", LocalDateTime.now());
        return "thymeleaf/date";
    }

    @GetMapping(value = "link")
    public String link(Model model) {
        model.addAttribute("param1","data1");
        model.addAttribute("param2","data2");
        model.addAttribute("path","1");
        model.addAttribute("url","/api");
        return "thymeleaf/link";
    }
}
