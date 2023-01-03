package kr.co.memberapi.admin.domain.dto;

import kr.co.memberapi.admin.domain.entity.AdminGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AdminGroupDto {

    private Long seq;
    private String defaultGroupYn;
    private String groupName;
    private String createdId;
    private LocalDateTime createdDatetime;
    private String modifiedId;
    private LocalDateTime modifiedDatetime;

    @Builder(builderClassName = "byCreate", builderMethodName = "byCreate")
    public AdminGroupDto(Long seq, String defaultGroupYn, String groupName, String createdId, LocalDateTime createdDatetime, String modifiedId, LocalDateTime modifiedDatetime) {
        this.seq = seq;
        this.defaultGroupYn = defaultGroupYn;
        this.groupName = groupName;
        this.createdId = createdId;
        this.createdDatetime = createdDatetime;
        this.modifiedId = modifiedId;
        this.modifiedDatetime = modifiedDatetime;
    }

    @Builder(builderClassName = "selectByEntity", builderMethodName = "selectByEntity")
    public AdminGroupDto(AdminGroup adminGroup) {
        this.seq = adminGroup.getSeq();
        this.defaultGroupYn = adminGroup.getDefaultGroupYn();
        this.groupName = adminGroup.getGroupName();
        this.createdId = adminGroup.getCreatedId();
        this.createdDatetime = adminGroup.getCreatedDatetime();
        this.modifiedId = adminGroup.getModifiedId();
        this.modifiedDatetime = adminGroup.getModifiedDatetime();
    }


}
