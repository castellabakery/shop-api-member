package kr.co.memberapi.admin.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AdminGroupMenuAuthorityDto {

    private Long seq;
    private AdminGroupDto adminGroup;
    private AdminMenuAuthorityDto adminMenuAuthority;
    private String createdId;
    private LocalDateTime createdDatetime;
    private String modifiedId;
    private LocalDateTime modifiedDatetime;

}
