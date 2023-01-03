package kr.co.memberapi.admin.domain.dto;

import kr.co.memberapi.admin.domain.entity.Admin;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AdminDto {

    private Long seq;
    private AdminGroupDto adminGroup;
    private String adminCode;
    private String adminId;
    private String password;
    private String name;
    private String telNo;
    private String phoneNo;
    private String email;
    private String createdId;
    private LocalDateTime createdDatetime;
    private String modifiedId;
    private LocalDateTime modifiedDatetime;

    @Builder(builderClassName = "byCreate", builderMethodName = "byCreate")
    public AdminDto(Long seq, AdminGroupDto adminGroup, String adminCode, String adminId, String password, String name, String telNo, String phoneNo, String email, String createdId, LocalDateTime createdDatetime, String modifiedId, LocalDateTime modifiedDatetime) {
        this.seq = seq;
        this.adminGroup = adminGroup;
        this.adminCode = adminCode;
        this.adminId = adminId;
        this.password = password;
        this.name = name;
        this.telNo = telNo;
        this.phoneNo = phoneNo;
        this.email = email;
        this.createdId = createdId;
        this.createdDatetime = createdDatetime;
        this.modifiedId = modifiedId;
        this.modifiedDatetime = modifiedDatetime;
    }

    @Builder(builderClassName = "selectByEntity", builderMethodName = "selectByEntity")
    public AdminDto(Admin admin) {
        this.seq = admin.getSeq();
        this.adminGroup = AdminGroupDto.byCreate().seq(admin.getAdminGroup().getSeq()).build();
        this.adminCode = admin.getAdminCode();
        this.adminId = admin.getAdminId();
        this.name = admin.getName();
        this.telNo = admin.getTelNo();
        this.phoneNo = admin.getPhoneNo();
        this.email = admin.getEmail();
        this.password = admin.getPassword();
        this.createdId = admin.getCreatedId();
        this.createdDatetime = admin.getCreatedDatetime();
        this.modifiedId = admin.getModifiedId();
        this.modifiedDatetime = admin.getModifiedDatetime();
    }

    public AdminDto removePassword() {
        this.setPassword("");
        return this;
    }

}
