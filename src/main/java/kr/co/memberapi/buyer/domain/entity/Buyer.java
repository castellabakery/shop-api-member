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
@Table(name = "BUYER")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String buyerCode;
    private String buyerType;
    private String buyerState;
    private String healthcareFacilityCode;
    private String pharmacyNo;
    private String corpStaffName;
    private String corpTelNo;
    private String corpFaxNo; ///!!!!! 수정 중 테이블
    private String corpEmail;
    private String corpName;
    private String corpNo;
    private String corpAddress;
    private String addressPostNo;
    private String addressDetail;
    private String corpShippingAddress;
    private String shippingAddressPostNo;
    private String shippingAddressDetail;
    private String recommender;
    private String ci;
    private String createdId;
    private String modifiedId;
    private LocalDateTime createdDatetime;
    private LocalDateTime approveDatetime;
    private LocalDateTime modifiedDatetime;

    @Builder
    public Buyer(Long seq, String buyerCode, String buyerType, String buyerState, String healthcareFacilityCode, String pharmacyNo, String corpStaffName, String corpTelNo, String corpFaxNo, String corpEmail, String corpName, String corpNo, String corpAddress, String addressPostNo, String addressDetail, String corpShippingAddress, String shippingAddressPostNo, String shippingAddressDetail, String ci, String recommender, String createdId, String modifiedId, LocalDateTime createdDatetime, LocalDateTime approveDatetime, LocalDateTime modifiedDatetime) {
        this.seq = seq;
        this.buyerCode = buyerCode;
        this.buyerType = buyerType;
        this.buyerState = buyerState;
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
        this.recommender = recommender;
        this.createdId = createdId;
        this.modifiedId = modifiedId;
        this.createdDatetime = createdDatetime;
        this.approveDatetime = approveDatetime;
        this.modifiedDatetime = modifiedDatetime;
    }

}
