package kr.co.memberapi.admin.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "ADMIN")
@ToString
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    @ManyToOne
    @JoinColumn(name = "ADMIN_GROUP_SEQ", nullable = false)
    private AdminGroup adminGroup;
    private String adminCode;
    private String adminId;
    private String password;
    private String name;
    private String telNo;
    private String phoneNo;
    private String email;
    private String state;
    private String createdId;
    private LocalDateTime createdDatetime;
    private String modifiedId;
    private LocalDateTime modifiedDatetime;

    @Builder
    public Admin(Long seq, AdminGroup adminGroup, String adminCode, String adminId, String password, String name, String telNo, String phoneNo, String email, String state, String createdId, LocalDateTime createdDatetime, String modifiedId, LocalDateTime modifiedDatetime) {
        this.seq = seq;
        this.adminGroup = adminGroup;
        this.adminCode = adminCode;
        this.adminId = adminId;
        this.password = password;
        this.name = name;
        this.telNo = telNo;
        this.phoneNo = phoneNo;
        this.email = email;
        this.state = state;
        this.createdId = createdId;
        this.createdDatetime = createdDatetime;
        this.modifiedId = modifiedId;
        this.modifiedDatetime = modifiedDatetime;
    }
}
