package kr.co.memberapi.buyer.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.co.memberapi.buyer.domain.entity.Buyer;
import kr.co.memberapi.buyer.domain.entity.TmpBuyer;
import kr.co.memberapi.common.constants.StateTypeConstants;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuyerDto {

    private Long seq;
    private String buyerCode;
    private String buyerType;
    private String buyerState;
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
    private String recommender;
    private String createdId;
    private String modifiedId;
    private LocalDateTime createdDatetime;
    private LocalDateTime approveDatetime;
    private LocalDateTime modifiedDatetime;

    public BuyerDto() {
    }

    // 기본
    @Builder(builderClassName = "byCreate", builderMethodName = "byCreate")
    public BuyerDto(Long seq, String buyerCode, String buyerType, String buyerState, String healthcareFacilityCode, String pharmacyNo, String corpStaffName, String corpTelNo, String corpFaxNo, String corpEmail, String corpName, String corpNo, String corpAddress, String addressPostNo, String addressDetail, String corpShippingAddress, String shippingAddressPostNo, String shippingAddressDetail, String ci, String recommender, String createdId, String modifiedId, LocalDateTime createdDatetime, LocalDateTime approveDatetime, LocalDateTime modifiedDatetime) {
        this.seq = seq;
        this.buyerCode = buyerCode;
        this.buyerType = buyerType;
        this.buyerState = buyerState;
        this.corpName = corpName;
        this.corpAddress = corpAddress;
        this.addressPostNo = addressPostNo;
        this.addressDetail = addressDetail;
        this.corpShippingAddress = corpShippingAddress;
        this.shippingAddressPostNo = shippingAddressPostNo;
        this.shippingAddressDetail = shippingAddressDetail;
        this.corpFaxNo = corpFaxNo;
        this.corpNo = corpNo;
        this.corpTelNo = corpTelNo;
        this.corpStaffName = corpStaffName;
        this.corpEmail = corpEmail;
        this.pharmacyNo = pharmacyNo;
        this.healthcareFacilityCode = healthcareFacilityCode;
        this.recommender = recommender;
        this.ci = ci;
        this.approveDatetime = approveDatetime;
        this.createdId = createdId;
        this.createdDatetime = createdDatetime;
        this.modifiedId = modifiedId;
        this.modifiedDatetime = modifiedDatetime;
    }

    @Builder(builderClassName = "selectByEntity", builderMethodName = "selectByEntity")
    public BuyerDto(Buyer buyer) {
        this.seq = buyer.getSeq();
        this.buyerCode = buyer.getBuyerCode();
        this.buyerType = buyer.getBuyerType();
        this.buyerState = buyer.getBuyerState();
        this.corpName = buyer.getCorpName();
        this.corpAddress = buyer.getCorpAddress();
        this.addressPostNo = buyer.getAddressPostNo();
        this.addressDetail = buyer.getAddressDetail();
        this.corpShippingAddress = buyer.getCorpShippingAddress();
        this.shippingAddressPostNo = buyer.getShippingAddressPostNo();
        this.shippingAddressDetail = buyer.getShippingAddressDetail();
        this.corpFaxNo = buyer.getCorpFaxNo();
        this.corpNo = buyer.getCorpNo();
        this.corpTelNo = buyer.getCorpTelNo();
        this.corpStaffName = buyer.getCorpStaffName();
        this.corpEmail = buyer.getCorpEmail();
        this.pharmacyNo = buyer.getPharmacyNo();
        this.healthcareFacilityCode = buyer.getHealthcareFacilityCode();
        this.recommender = buyer.getRecommender();
        this.ci = buyer.getCi();
        this.approveDatetime = buyer.getApproveDatetime();
        this.createdId = buyer.getCreatedId();
        this.modifiedId = buyer.getModifiedId();
        this.createdDatetime = buyer.getCreatedDatetime();
        this.modifiedDatetime = buyer.getModifiedDatetime();
    }

    @Builder(builderClassName = "tmpBuyerDtoConvert", builderMethodName = "tmpBuyerDtoConvert")
    public BuyerDto(TmpBuyerDto tmpBuyerDto) {
        this.buyerCode = tmpBuyerDto.getBuyerCode();
        this.buyerType = tmpBuyerDto.getBuyerType();
        this.buyerState = tmpBuyerDto.getState();
        this.corpName = tmpBuyerDto.getCorpName();
        this.corpAddress = tmpBuyerDto.getCorpAddress();
        this.addressPostNo = tmpBuyerDto.getAddressPostNo();
        this.addressDetail = tmpBuyerDto.getAddressDetail();
        this.corpShippingAddress = tmpBuyerDto.getCorpShippingAddress();
        this.shippingAddressPostNo = tmpBuyerDto.getShippingAddressPostNo();
        this.shippingAddressDetail = tmpBuyerDto.getShippingAddressDetail();
        this.corpFaxNo = tmpBuyerDto.getCorpFaxNo();
        this.corpNo = tmpBuyerDto.getCorpNo();
        this.corpTelNo = tmpBuyerDto.getCorpTelNo();
        this.corpStaffName = tmpBuyerDto.getCorpStaffName();
        this.corpEmail = tmpBuyerDto.getCorpEmail();
        this.pharmacyNo = tmpBuyerDto.getPharmacyNo();
        this.healthcareFacilityCode = tmpBuyerDto.getHealthcareFacilityCode();
        this.recommender = tmpBuyerDto.getRecommender();
        this.ci = tmpBuyerDto.getCi();
        this.approveDatetime = tmpBuyerDto.getCreatedDatetime();
    }

    @Builder(builderClassName = "tmpBuyerConvert", builderMethodName = "tmpBuyerConvert")
    public BuyerDto(TmpBuyer tmpBuyer) {
        this.seq = tmpBuyer.getBuyerSeq();
        this.buyerCode = tmpBuyer.getBuyerCode();
        this.buyerType = tmpBuyer.getBuyerType();
        this.buyerState = tmpBuyer.getState();
        this.corpName = tmpBuyer.getCorpName();
        this.corpAddress = tmpBuyer.getCorpAddress();
        this.addressPostNo = tmpBuyer.getAddressPostNo();
        this.addressDetail = tmpBuyer.getAddressDetail();
        this.corpShippingAddress = tmpBuyer.getCorpShippingAddress();
        this.shippingAddressPostNo = tmpBuyer.getShippingAddressPostNo();
        this.shippingAddressDetail = tmpBuyer.getShippingAddressDetail();
        this.corpFaxNo = tmpBuyer.getCorpFaxNo();
        this.corpNo = tmpBuyer.getCorpNo();
        this.corpTelNo = tmpBuyer.getCorpTelNo();
        this.corpStaffName = tmpBuyer.getCorpStaffName();
        this.corpEmail = tmpBuyer.getCorpEmail();
        this.pharmacyNo = tmpBuyer.getPharmacyNo();
        this.healthcareFacilityCode = tmpBuyer.getHealthcareFacilityCode();
        this.recommender = tmpBuyer.getRecommender();
        this.ci = tmpBuyer.getCi();
        this.approveDatetime = tmpBuyer.getCreatedDatetime();
    }

    // 회원 등록
    public Buyer insertEntity(String userCode) {
        return Buyer
                .builder()
                .buyerCode(this.buyerCode)
                .buyerType(this.buyerType)
                .buyerState(StateTypeConstants.DONE)
                .corpName(this.corpName)
                .corpAddress(this.corpAddress)
                .addressPostNo(this.addressPostNo)
                .addressDetail(this.addressDetail)
                .corpShippingAddress(this.corpShippingAddress)
                .shippingAddressPostNo(this.shippingAddressPostNo)
                .shippingAddressDetail(this.shippingAddressDetail)
                .corpFaxNo(this.corpFaxNo)
                .corpNo(this.corpNo)
                .corpTelNo(this.corpTelNo)
                .corpStaffName(this.corpStaffName)
                .corpEmail(this.corpEmail)
                .pharmacyNo(this.pharmacyNo)
                .healthcareFacilityCode(this.healthcareFacilityCode)
                .recommender(this.recommender)
                .ci(this.ci)
                .approveDatetime(this.approveDatetime)
                .createdId(userCode)
                .createdDatetime(LocalDateTime.now())
                .build();
    }

    // 회원 갱신.
    public Buyer updateEntity(BuyerIdentificationDto newDto, String state) {
        return Buyer
                .builder()
                .seq(this.seq)
                .buyerCode(this.buyerCode)
                .buyerType(this.buyerType)
                .buyerState(state)
                .corpName(newDto.getBuyer().getCorpName())

                .corpAddress(newDto.getBuyer().getCorpAddress())
                .addressPostNo(newDto.getBuyer().getAddressPostNo())
                .addressDetail(newDto.getBuyer().getAddressDetail())

                .corpShippingAddress(newDto.getBuyer().getCorpShippingAddress())
                .shippingAddressPostNo(newDto.getBuyer().getShippingAddressPostNo())
                .shippingAddressDetail(newDto.getBuyer().getShippingAddressDetail())

                .corpFaxNo(newDto.getBuyer().getCorpFaxNo())
                .corpNo(newDto.getBuyer().getCorpNo())
                .corpTelNo(newDto.getBuyer().getCorpTelNo())
                .corpStaffName(newDto.getBuyer().getCorpStaffName())
                .corpEmail(newDto.getBuyer().getCorpEmail())
                .pharmacyNo(newDto.getBuyer().getPharmacyNo())
                .healthcareFacilityCode(newDto.getBuyer().getHealthcareFacilityCode())
                .recommender(newDto.getBuyer().getRecommender())
                .ci(this.ci)
                .approveDatetime(this.approveDatetime)
                .createdId(this.createdId)
                .createdDatetime(this.createdDatetime)
                .modifiedId(newDto.getModifiedId())
                .modifiedDatetime(LocalDateTime.now())
                .build();
    }


    public Buyer updateByState(String state) {
        return Buyer
                .builder()
                .seq(this.seq)
                .buyerCode(this.buyerCode)
                .buyerType(this.buyerType)
                .buyerState(state)
                .corpName(this.corpName)
                .corpAddress(this.corpAddress)
                .addressPostNo(this.addressPostNo)
                .addressDetail(this.addressDetail)
                .corpShippingAddress(this.corpShippingAddress)
                .shippingAddressPostNo(this.shippingAddressPostNo)
                .shippingAddressDetail(this.shippingAddressDetail)
                .corpFaxNo(this.corpFaxNo)
                .corpNo(this.corpNo)
                .corpTelNo(this.corpTelNo)
                .corpStaffName(this.corpStaffName)
                .corpEmail(this.corpEmail)
                .pharmacyNo(this.pharmacyNo)
                .healthcareFacilityCode(this.healthcareFacilityCode)
                .recommender(this.recommender)
                .ci(this.ci)
                .approveDatetime(this.approveDatetime)
                .createdId(this.createdId)
                .createdDatetime(this.createdDatetime)
                .modifiedId(this.modifiedId)
                .modifiedDatetime(LocalDateTime.now())
                .build();
    }

    // 회원 삭제 처리.
    public Buyer deleteEntity(String requestUserCode) {
        return Buyer
                .builder()
                .seq(this.seq)
                .buyerCode(this.buyerCode)
                .buyerType(this.buyerType)
                .buyerState(StateTypeConstants.EXPIRED)
                .corpName("")
                .corpAddress("")
                .addressPostNo("")
                .addressDetail("")
                .corpShippingAddress("")
                .shippingAddressPostNo("")
                .shippingAddressDetail("")
                .corpFaxNo("")
                .corpNo("")
                .corpTelNo("")
                .corpStaffName("")
                .corpEmail("")
                .pharmacyNo("")
                .healthcareFacilityCode("")
                .recommender(this.recommender)
                .ci("")
                .approveDatetime(this.approveDatetime)
                .createdId(this.createdId)
                .createdDatetime(this.createdDatetime)
                .modifiedId(requestUserCode)
                .modifiedDatetime(LocalDateTime.now())
                .build();
    }

    public Buyer convertEntity() {
        return Buyer
                .builder()
                .seq(this.seq)
                .buyerCode(this.buyerCode)
                .buyerType(this.buyerType)
                .buyerState(this.buyerState)
                .corpName(this.corpName)
                .corpAddress(this.corpAddress)
                .addressPostNo(this.addressPostNo)
                .addressDetail(this.addressDetail)
                .corpShippingAddress(this.corpShippingAddress)
                .shippingAddressPostNo(this.shippingAddressPostNo)
                .shippingAddressDetail(this.shippingAddressDetail)
                .corpFaxNo(this.corpFaxNo)
                .corpNo(this.corpNo)
                .corpTelNo(this.corpTelNo)
                .corpStaffName(this.corpStaffName)
                .corpEmail(this.corpEmail)
                .pharmacyNo(this.pharmacyNo)
                .healthcareFacilityCode(this.healthcareFacilityCode)
                .recommender(this.recommender)
                .ci(this.ci)
                .approveDatetime(this.approveDatetime)
                .createdId(this.createdId)
                .createdDatetime(this.createdDatetime)
                .modifiedId(this.modifiedId)
                .modifiedDatetime(this.modifiedDatetime)
                .build();
    }

}
