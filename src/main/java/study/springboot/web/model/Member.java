package study.springboot.web.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member {
    private String id;
    private String password;
    private String name;
    private Gender gender;
    private String email;
    private String domain;
    
    public enum Gender {
        MALE,
        FEMALE,
        NONEBINARY
    }
}
