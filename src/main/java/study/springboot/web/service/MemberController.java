package study.springboot.web.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import study.springboot.web.model.Member;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/member/api")
public class MemberController {
    
    /**
     * 회원 등록 (Map으로 처리)
     */
    @PostMapping(value = "/create")
    // Map<K,V>
    // String Type Key에 String type value를 Mapping
    public String registerMember(@RequestParam Map<String, String> params) {
        // 로그로 전체 데이터 출력
        log.info("Received Data: {}", params);

        // 필수값 검증
        if (!params.containsKey("id") || !params.containsKey("name") || !params.containsKey("gender")) {
            return "Error: Missing required fields (id, name, gender).";
        }

        // Gender 유효성 검증
        String genderInput = params.get("gender").toUpperCase();
        Member.Gender gender;
        try {
            gender = Member.Gender.valueOf(genderInput);
        } catch (IllegalArgumentException e) {
            return "Error: Invalid gender value. Allowed values: MALE, FEMALE, NONBINARY.";
        }
        // 응답 메시지 생성
        return createResponse(
                params.get("id"),
                params.get("name"),
                gender.name(),
                params.get("email"),
                params.get("domain")
        );
    }

    /**
     * 회원 등록 (@ModelAttribute로 처리)
     */
    @PostMapping(value = "/create2")
    public String registerMember2(@ModelAttribute Member member) {
        // 로그로 Member 객체 출력
        log.info("Received Member: {}", member);

        // Gender 유효성 검증
        Member.Gender gender = member.getGender();
        if (gender == null) {
            return "Error: Invalid gender value. Allowed values: MALE, FEMALE, NONBINARY.";
        }

        // 응답 메시지 생성
        return createResponse(
                member.getId(),
                member.getName(),
                gender.name(),
                member.getEmail(),
                member.getDomain()
        );
    }

    /**
     * 응답 메시지 생성
     */
    private String createResponse(String id, String name, String gender, String email, String domain) {
        return String.format(
                "Registration successful! \nID: %s\nName: %s\nGender: %s\nEmail: %s@%s",
                id, name, gender, email, domain
        );
    }
}
