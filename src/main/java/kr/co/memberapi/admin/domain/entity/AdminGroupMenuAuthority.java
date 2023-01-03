package kr.co.memberapi.admin.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "ADMIN_GROUP_MENU_AUTHORITY")
public class AdminGroupMenuAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    @ManyToOne
    @JoinColumn(name = "ADMIN_GROUP_SEQ")
    private AdminGroup adminGroup;
    @ManyToOne
    @JoinColumn(name = "ADMIN_MENU_AUTHORITY_SEQ_SEQ")
    private AdminMenuAuthority adminMenuAuthority;
    private String createdId;
    private LocalDateTime createdDatetime;
    private String modifiedId;
    private LocalDateTime modifiedDatetime;

    @Builder
    public AdminGroupMenuAuthority(Long seq, AdminGroup adminGroup, AdminMenuAuthority adminMenuAuthority, String createdId, LocalDateTime createdDatetime, String modifiedId, LocalDateTime modifiedDatetime) {
        this.seq = seq;
        this.adminGroup = adminGroup;
        this.adminMenuAuthority = adminMenuAuthority;
        this.createdId = createdId;
        this.createdDatetime = createdDatetime;
        this.modifiedId = modifiedId;
        this.modifiedDatetime = modifiedDatetime;
    }
}
