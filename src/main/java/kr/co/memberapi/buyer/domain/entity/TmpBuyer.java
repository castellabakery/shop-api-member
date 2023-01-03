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
@Table(name = "TMP_BUYER")
public class TmpBuyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private Long buyerSeq;
    private String buyerType;
    private String buyerCode;
    private String state;
    private String healthcareFacilityCode;
    private String pharmacyNo;
    private String corpStaffName;
    private String corpTelNo;
    private String corpFaxNo;
    private String corpEmail;
    private String corpName;
    private String corpNo;
    private String corpAddress;
    private String addressPostNo;
    private String addressDetail;
    private String corpShippingAddress;
    private String shippingAddressPostNo;
    private String shippingAddressDetail;
    private String ci;
    private String buyerIdentificationCode;
    private String buyerIdentificationId;
    private String password;
    private String staffName;
    private String staffPhoneNo;
    private String staffEmail;
    private String recommender;
    private String rejectedMsg;
    private String createdId;
    private String modifiedId;
    private LocalDateTime createdDatetime;
    private LocalDateTime modifiedDatetime;

    @Builder
    public TmpBuyer(Long seq, Long buyerSeq, String buyerType, String buyerCode, String state, String healthcareFacilityCode, String pharmacyNo, String corpStaffName, String corpTelNo, String corpFaxNo, String corpEmail, String corpName, String corpNo, String corpAddress, String addressPostNo, String addressDetail, String corpShippingAddress, String shippingAddressPostNo, String shippingAddressDetail, String ci, String buyerIdentificationCode, String buyerIdentificationId, String password, String staffName, String staffPhoneNo, String staffEmail, String recommender, String rejectedMsg, String createdId, String modifiedId, LocalDateTime createdDatetime, LocalDateTime modifiedDatetime) {
        this.seq = seq;
        this.buyerSeq = buyerSeq;
        this.buyerType = buyerType;
        this.buyerCode = buyerCode;
        this.state = state;
        this.healthcareFacilityCode = healthcareFacilityCode;
        this.pharmacyNo = pharmacyNo;
        this.corpStaffName = corpStaffName;
        this.corpTelNo = corpTelNo;
        this.corpFaxNo = corpFaxNo;
        this.corpEmail = corpEmail;
        this.corpName = corpName;
        this.corpNo = corpNo;
        this.corpAddress = corpAddress;
        this.addressPostNo = addressPostNo;
        this.addressDetail = addressDetail;
        this.corpShippingAddress = corpShippingAddress;
        this.shippingAddressPostNo = shippingAddressPostNo;
        this.shippingAddressDetail = shippingAddressDetail;
        this.ci = ci;
        this.buyerIdentificationCode = buyerIdentificationCode;
        this.buyerIdentificationId = buyerIdentificationId;
        this.password = password;
        this.staffName = staffName;
        this.staffPhoneNo = staffPhoneNo;
        this.staffEmail = staffEmail;
        this.recommender = recommender;
        this.rejectedMsg = rejectedMsg;
        this.createdId = createdId;
        this.modifiedId = modifiedId;
        this.createdDatetime = createdDatetime;
        this.modifiedDatetime = modifiedDatetime;
    }
}
