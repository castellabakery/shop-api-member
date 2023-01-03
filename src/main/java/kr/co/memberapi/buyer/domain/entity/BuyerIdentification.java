package kr.co.memberapi.buyer.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@Entity
@NoArgsConstructor
@DynamicUpdate
@Table(name = "BUYER_IDENTIFICATION")
public class BuyerIdentification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BUYER_SEQ", nullable = false)
    private Buyer buyer;
    private String buyerIdentificationCode;
    private String identificationType;
    private String buyerIdentificationState;
    private String buyerIdentificationId;
    private String password;
    private String staffName;
    private String staffPhoneNo;
    private String staffEmail;
    private String createdId;
    private String modifiedId;
    private LocalDateTime createdDatetime;
    private LocalDateTime modifiedDatetime;

    @Builder
    public BuyerIdentification(Long seq, Buyer buyer, String buyerIdentificationCode, String identificationType, String buyerIdentificationState, String buyerIdentificationId, String password, String staffName, String staffPhoneNo, String staffEmail, String createdId, String modifiedId, LocalDateTime createdDatetime, LocalDateTime modifiedDatetime) {
        this.seq = seq;
        this.buyer = buyer;
        this.buyerIdentificationCode = buyerIdentificationCode;
        this.identificationType = identificationType;
        this.buyerIdentificationState = buyerIdentificationState;
        this.buyerIdentificationId = buyerIdentificationId;
        this.password = password;
        this.staffName = staffName;
        this.staffPhoneNo = staffPhoneNo;
        this.staffEmail = staffEmail;
        this.createdId = createdId;
        this.modifiedId = modifiedId;
        this.createdDatetime = createdDatetime;
        this.modifiedDatetime = modifiedDatetime;
    }

}
