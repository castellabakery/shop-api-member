package kr.co.memberapi.buyer.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.co.memberapi.buyer.domain.entity.TmpBuyer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TmpBuyerDto {

    private Long seq;
    private Long buyerSeq;
    private String buyerCode;
    private String buyerType;
    private String state;
    private String healthcareFacilityCode;
    private String pharmacyNo;
    private String corpTelNo;
    private String corpFaxNo;
    private String corpEmail;
    private String corpName;
    private String corpStaffName;
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

    public TmpBuyerDto() {
    }

    // 기본

    @Builder(builderClassName = "byCreate", builderMethodName = "byCreate")
    public TmpBuyerDto(Long seq, Long buyerSeq, String buyerCode, String buyerType, String state, String healthcareFacilityCode, String pharmacyNo, String corpTelNo, String corpFaxNo, String corpEmail, String corpName, String corpStaffName, String corpNo, String corpAddress, String addressPostNo, String addressDetail, String corpShippingAddress, String shippingAddressPostNo, String shippingAddressDetail, String ci, String buyerIdentificationCode, String buyerIdentificationId, String password, String staffName, String staffPhoneNo, String staffEmail, String recommender, String rejectedMsg, String createdId, String modifiedId, LocalDateTime createdDatetime, LocalDateTime modifiedDatetime) {
        this.seq = seq;
        this.buyerSeq = buyerSeq;
        this.buyerCode = buyerCode;
        this.buyerType = buyerType;
        this.state = state;
        this.healthcareFacilityCode = healthcareFacilityCode;
        this.pharmacyNo = pharmacyNo;
        this.corpTelNo = corpTelNo;
        this.corpFaxNo = corpFaxNo;
        this.corpEmail = corpEmail;
        this.corpName = corpName;
        this.corpStaffName = corpStaffName;
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

    @Builder(builderClassName = "bySelectEntity", builderMethodName = "bySelectEntity")
    public TmpBuyerDto(TmpBuyer tmpBuyer) {
        this.seq = tmpBuyer.getSeq();
        this.buyerSeq = tmpBuyer.getBuyerSeq();
        this.buyerCode = tmpBuyer.getBuyerCode();
        this.buyerType = tmpBuyer.getBuyerType();
        this.state = tmpBuyer.getState();
        this.healthcareFacilityCode = tmpBuyer.getHealthcareFacilityCode();
        this.pharmacyNo = tmpBuyer.getPharmacyNo();
        this.corpStaffName = tmpBuyer.getCorpStaffName();
        this.corpTelNo = tmpBuyer.getCorpTelNo();
        this.corpFaxNo = tmpBuyer.getCorpFaxNo();
        this.corpEmail = tmpBuyer.getCorpEmail();
        this.corpName = tmpBuyer.getCorpName();
        this.corpNo = tmpBuyer.getCorpNo();
        this.corpAddress = tmpBuyer.getCorpAddress();
        this.addressPostNo = tmpBuyer.getAddressPostNo();
        this.addressDetail = tmpBuyer.getAddressDetail();
        this.corpShippingAddress = tmpBuyer.getCorpShippingAddress();
        this.shippingAddressPostNo = tmpBuyer.getShippingAddressPostNo();
        this.shippingAddressDetail = tmpBuyer.getShippingAddressDetail();
        this.buyerIdentificationCode = tmpBuyer.getBuyerIdentificationCode();
        this.buyerIdentificationId = tmpBuyer.getBuyerIdentificationId();
        this.staffName = tmpBuyer.getStaffName();
        this.staffPhoneNo = tmpBuyer.getStaffPhoneNo();
        this.staffEmail = tmpBuyer.getStaffEmail();
        this.recommender = tmpBuyer.getRecommender();
        this.rejectedMsg = tmpBuyer.getRejectedMsg();
        this.createdId = tmpBuyer.getCreatedId();
        this.modifiedId = tmpBuyer.getModifiedId();
        this.createdDatetime = tmpBuyer.getCreatedDatetime();
        this.modifiedDatetime = tmpBuyer.getModifiedDatetime();
    }

    public TmpBuyer insertTemplate() {
        return TmpBuyer
                .builder()
                .buyerSeq(this.buyerSeq)
                .buyerType(this.buyerType)
                .buyerCode(this.buyerCode)
                .state(this.state)
                .healthcareFacilityCode(this.healthcareFacilityCode)
                .pharmacyNo(this.pharmacyNo)
                .corpStaffName(this.corpStaffName)
                .corpTelNo(this.corpTelNo)
                .corpFaxNo(this.corpFaxNo)
                .corpEmail(ObjectUtils.isEmpty(this.corpEmail) ? this.staffEmail : this.corpEmail)
                .corpName(this.corpName)
                .corpNo(this.corpNo)
                .corpAddress(this.corpAddress)
                .addressPostNo(this.addressPostNo)
                .addressDetail(this.addressDetail)
                .corpShippingAddress(ObjectUtils.isEmpty(this.corpShippingAddress) ? this.corpAddress : this.corpShippingAddress)
                .shippingAddressPostNo(ObjectUtils.isEmpty(this.shippingAddressPostNo) ? this.addressPostNo : this.shippingAddressPostNo)
                .shippingAddressDetail(ObjectUtils.isEmpty(this.shippingAddressDetail) ? this.addressDetail : this.shippingAddressDetail)
                .ci(this.ci)
                .buyerIdentificationCode(this.buyerIdentificationCode)
                .buyerIdentificationId(this.buyerIdentificationId)
                .password(this.password)
                .staffName(this.staffName)
                .staffPhoneNo(this.staffPhoneNo)
                .staffEmail(this.staffEmail)
                .recommender(this.recommender)
                .createdId(this.createdId)
                .createdDatetime(LocalDateTime.now())
                .build();
    }

    public TmpBuyer updateTemplate(BuyerIdentificationDto oldDto) {
        return TmpBuyer
                .builder()
                // 변경되면 안되는 정보
                .buyerSeq(oldDto.getBuyer().getSeq())
                .buyerCode(oldDto.getBuyer().getBuyerCode())
                .buyerType(oldDto.getBuyer().getBuyerType())
                .buyerIdentificationCode(oldDto.getBuyerIdentificationCode())
                .buyerIdentificationId(oldDto.getBuyerIdentificationId())
                .password(oldDto.getPassword())
                .createdId(oldDto.getBuyerIdentificationCode())
                .createdDatetime(LocalDateTime.now())
                
                // 변경하는 정보
                .state(this.state)
                
                // 사업자 정보
                .corpStaffName(ObjectUtils.isEmpty(this.corpStaffName) ? oldDto.getBuyer().getCorpStaffName() : this.corpStaffName)
                .corpTelNo(ObjectUtils.isEmpty(this.corpTelNo) ? oldDto.getBuyer().getCorpTelNo() : this.corpTelNo)
                .corpFaxNo(ObjectUtils.isEmpty(this.corpFaxNo) ? oldDto.getBuyer().getCorpFaxNo() : this.corpFaxNo)
                .corpName(ObjectUtils.isEmpty(this.corpName) ? oldDto.getBuyer().getCorpName() : this.corpName)
                .corpNo(ObjectUtils.isEmpty(this.corpNo) ? oldDto.getBuyer().getCorpNo() : this.corpNo)
                .corpEmail(ObjectUtils.isEmpty(this.corpEmail) ? oldDto.getBuyer().getCorpEmail() : this.corpEmail)
                .healthcareFacilityCode(ObjectUtils.isEmpty(this.healthcareFacilityCode) ? oldDto.getBuyer().getHealthcareFacilityCode() : this.healthcareFacilityCode)
                .pharmacyNo(ObjectUtils.isEmpty(this.pharmacyNo) ? oldDto.getBuyer().getPharmacyNo() : this.pharmacyNo)
                .ci(ObjectUtils.isEmpty(this.ci) ? oldDto.getBuyer().getCi() : this.ci)

                // 사업장 주소.
                .corpAddress(ObjectUtils.isEmpty(this.corpAddress) ? oldDto.getBuyer().getCorpAddress() : this.corpAddress)
                .addressPostNo(ObjectUtils.isEmpty(this.addressPostNo) ? oldDto.getBuyer().getAddressPostNo() : this.addressPostNo)
                .addressDetail(ObjectUtils.isEmpty(this.addressDetail) ? oldDto.getBuyer().getAddressDetail() : this.addressDetail)
                // 배송지 주소 입력 안들어오면, 사업장 주소.
//                .corpShippingAddress(ObjectUtils.isEmpty(this.corpShippingAddress) ? oldDto.getBuyer().getCorpShippingAddress() : this.corpShippingAddress)
//                .shippingAddressPostNo(ObjectUtils.isEmpty(this.shippingAddressPostNo) ? oldDto.getBuyer().getShippingAddressPostNo() : this.shippingAddressPostNo)
//                .shippingAddressDetail(ObjectUtils.isEmpty(this.shippingAddressDetail) ? oldDto.getBuyer().getShippingAddressDetail() : this.shippingAddressDetail)

                .corpShippingAddress(ObjectUtils.isEmpty(this.corpShippingAddress) ? this.corpAddress : this.corpShippingAddress)
                .shippingAddressPostNo(ObjectUtils.isEmpty(this.shippingAddressPostNo) ? this.addressPostNo : this.shippingAddressPostNo)
                .shippingAddressDetail(ObjectUtils.isEmpty(this.shippingAddressDetail) ? this.addressDetail : this.shippingAddressDetail)

                // 담당자 정보
                .staffName(ObjectUtils.isEmpty(this.staffName) ? oldDto.getStaffName() : this.staffName)
                .staffPhoneNo(ObjectUtils.isEmpty(this.staffPhoneNo) ? oldDto.getStaffPhoneNo() : this.staffPhoneNo)
                .staffEmail(ObjectUtils.isEmpty(this.staffEmail) ? oldDto.getStaffEmail() : this.staffEmail)
                .recommender(ObjectUtils.isEmpty(this.recommender) ? oldDto.getBuyer().getRecommender() : this.recommender)
                .build();
    }

    public TmpBuyer updateStateTmpBuyer(BuyerIdentificationDto buyerIdentificationDto, String state) {
        return TmpBuyer
                .builder()
                .seq(this.seq)
                .buyerSeq(buyerIdentificationDto.getBuyer().getSeq())
                .buyerCode(buyerIdentificationDto.getBuyer().getBuyerCode())
                .buyerType(this.buyerType)
                .state(state)
                .healthcareFacilityCode(this.healthcareFacilityCode)
                .pharmacyNo(this.pharmacyNo)
                .corpStaffName(this.corpStaffName)
                .corpTelNo(this.corpTelNo)
                .corpFaxNo(this.corpFaxNo)
                .corpEmail(this.corpEmail)
                .corpName(this.corpName)
                .corpNo(this.corpNo)
                .corpAddress(this.corpAddress)
                .addressPostNo(this.addressPostNo)
                .addressDetail(this.addressDetail)
                .corpShippingAddress(this.corpShippingAddress)
                .shippingAddressPostNo(this.shippingAddressPostNo)
                .shippingAddressDetail(this.shippingAddressDetail)
                .ci(this.ci)
                .buyerIdentificationCode(buyerIdentificationDto.getBuyerIdentificationCode())
                .buyerIdentificationId(this.buyerIdentificationId)
                .password(this.password)
                .staffName(this.staffName)
                .staffPhoneNo(this.staffPhoneNo)
                .staffEmail(this.staffEmail)
                .recommender(this.recommender)
                .rejectedMsg(this.rejectedMsg)
                .createdId(this.createdId)
                .createdDatetime(this.createdDatetime)
                .modifiedId(this.modifiedId)
                .modifiedDatetime(LocalDateTime.now())
                .build();
    }

}
