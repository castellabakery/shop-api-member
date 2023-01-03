package kr.co.memberapi.admin.domain.dto;

import kr.co.memberapi.admin.domain.entity.AdminMenuAuthority;
import kr.co.memberapi.common.util.StringUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AdminMenuAuthorityDto {

    private Long seq;
    private String menuUrl;
    private String menuName;
    private String useYn;
    private String createdId;
    private LocalDateTime createdDatetime;
    private String modifiedId;
    private LocalDateTime modifiedDatetime;

    public AdminMenuAuthority insertEntity() {

        StringUtils.notBlank(this.menuUrl, "menuUrl must not be null");
        StringUtils.notBlank(this.menuName, "menuName must not be null");
        StringUtils.notBlank(this.useYn, "useYn must not be null");

        return AdminMenuAuthority
                .builder()
                .menuUrl(this.menuUrl)
                .menuName(this.menuName)
                .useYn(this.useYn)
                .createdId(this.createdId)
                .createdDatetime(LocalDateTime.now())
                .build();
    }

}
