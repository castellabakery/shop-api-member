package kr.co.memberapi.buyer.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.co.memberapi.buyer.domain.entity.Buyer;
import kr.co.memberapi.buyer.domain.entity.BuyerIdentification;
import kr.co.memberapi.buyer.domain.entity.TmpBuyer;
import kr.co.memberapi.common.constants.IdentificationTypeConstants;
import kr.co.memberapi.common.constants.StateTypeConstants;
import kr.co.memberapi.common.constants.UserTypeConstants;
import kr.co.memberapi.common.util.DateFormatUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuyerIdentificationDto {

    private Long seq;
    private BuyerDto buyer;
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

    public BuyerIdentificationDto() {
    }

    // 기본
    @Builder(builderClassName = "byCreate", builderMethodName = "byCreate")
    public BuyerIdentificationDto(Long seq, BuyerDto buyer, String buyerIdentificationCode, String identificationType, String buyerIdentificationState, String buyerIdentificationId, String password, String staffName, String staffPhoneNo, String staffEmail, String createdId, String modifiedId, LocalDateTime createdDatetime, LocalDateTime modifiedDatetime) {
        this.seq = seq;
        this.buyer = buyer;
        this.buyerIdentificationCode = buyerIdentificationCode;
        this.identificationType = identificationType;
        this.buyerIdentificationId = buyerIdentificationId;
        this.buyerIdentificationState = buyerIdentificationState;
        this.password = password;
        this.staffName = staffName;
        this.staffPhoneNo = staffPhoneNo;
        this.staffEmail = staffEmail;
        this.createdId = createdId;
        this.modifiedId = modifiedId;
        this.createdDatetime = createdDatetime;
        this.modifiedDatetime = modifiedDatetime;
    }

    @Builder(builderClassName = "selectByEntity", builderMethodName = "selectByEntity")
    public BuyerIdentificationDto(BuyerIdentification buyerIdentification, Buyer buyer) {
        this.seq = buyerIdentification.getSeq();
        this.buyer = BuyerDto.selectByEntity().buyer(buyer).build();
        this.buyerIdentificationCode = buyerIdentification.getBuyerIdentificationCode();
        this.buyerIdentificationState = buyerIdentification.getBuyerIdentificationState();
        this.identificationType = buyerIdentification.getIdentificationType();
        this.buyerIdentificationId = buyerIdentification.getBuyerIdentificationId();
        this.staffName = buyerIdentification.getStaffName();
        this.staffPhoneNo = buyerIdentification.getStaffPhoneNo();
        this.staffEmail = buyerIdentification.getStaffEmail();
        this.createdId = buyerIdentification.getCreatedId();
        this.modifiedId = buyerIdentification.getModifiedId();
        this.createdDatetime = buyerIdentification.getCreatedDatetime();
        this.modifiedDatetime = buyerIdentification.getModifiedDatetime();
    }

    @Builder(builderClassName = "entityConvertDto", builderMethodName = "entityConvertDto")
    public BuyerIdentificationDto(BuyerIdentification buyerIdentification) {
        this.seq = buyerIdentification.getSeq();
        this.buyer = BuyerDto.selectByEntity().buyer(buyerIdentification.getBuyer()).build();
        this.buyerIdentificationCode = buyerIdentification.getBuyerIdentificationCode();
        this.identificationType = buyerIdentification.getIdentificationType();
        this.buyerIdentificationId = buyerIdentification.getBuyerIdentificationId();
        this.buyerIdentificationState = buyerIdentification.getBuyerIdentificationState();
        this.password = buyerIdentification.getPassword();
        this.staffName = buyerIdentification.getStaffName();
        this.staffPhoneNo = buyerIdentification.getStaffPhoneNo();
        this.staffEmail = buyerIdentification.getStaffEmail();
        this.createdId = buyerIdentification.getCreatedId();
        this.createdDatetime = buyerIdentification.getCreatedDatetime();
        this.modifiedId = buyerIdentification.getModifiedId();
        this.modifiedDatetime = buyerIdentification.getModifiedDatetime();
    }

    @Builder(builderClassName = "tmpBuyerDtoConvert", builderMethodName = "tmpBuyerDtoConvert")
    public BuyerIdentificationDto(TmpBuyerDto tmpBuyerDto) {
        this.buyer = BuyerDto.tmpBuyerDtoConvert().tmpBuyerDto(tmpBuyerDto).build();
        this.buyerIdentificationCode = tmpBuyerDto.getBuyerIdentificationCode();
        this.identificationType = tmpBuyerDto.getBuyerType();
        this.buyerIdentificationId = tmpBuyerDto.getBuyerIdentificationId();
        this.buyerIdentificationState = tmpBuyerDto.getState();
        this.password = tmpBuyerDto.getPassword();
        this.staffName = tmpBuyerDto.getStaffName();
        this.staffPhoneNo = tmpBuyerDto.getStaffPhoneNo();
        this.staffEmail = tmpBuyerDto.getStaffEmail();
        this.createdId = tmpBuyerDto.getCreatedId();
        this.createdDatetime = tmpBuyerDto.getCreatedDatetime();
        this.modifiedId = tmpBuyerDto.getModifiedId();
        this.modifiedDatetime = tmpBuyerDto.getModifiedDatetime();
    }

    @Builder(builderClassName = "tmpBuyerConvert", builderMethodName = "tmpBuyerConvert")
    public BuyerIdentificationDto(TmpBuyer tmpBuyer) {
        this.buyer = BuyerDto.tmpBuyerConvert().tmpBuyer(tmpBuyer).build();
        this.buyerIdentificationCode = tmpBuyer.getBuyerIdentificationCode();
        this.identificationType = IdentificationTypeConstants.MAIN;
        this.buyerIdentificationId = tmpBuyer.getBuyerIdentificationId();
        this.buyerIdentificationState = tmpBuyer.getState();
        this.password = tmpBuyer.getPassword();
        this.staffName = tmpBuyer.getStaffName();
        this.staffPhoneNo = tmpBuyer.getStaffPhoneNo();
        this.staffEmail = tmpBuyer.getStaffEmail();
        this.createdId = tmpBuyer.getCreatedId();
        this.createdDatetime = tmpBuyer.getCreatedDatetime();
        this.modifiedId = tmpBuyer.getModifiedId();
        this.modifiedDatetime = tmpBuyer.getModifiedDatetime();
    }

    // 회원 등록.
    public BuyerIdentification insertEntity(Long buyerSeq, String userCode) {
        // 서브일때는 ID 자동 생성.
        if (Objects.equals(identificationType, IdentificationTypeConstants.SUB)) {
            this.buyerIdentificationCode = UserTypeConstants.BUYER_IDENTIFICATION_CODE + LocalDateTime.now().format(DateFormatUtils.DATETIME_CODE);
        }
        return BuyerIdentification
                .builder()
                .buyer(Buyer.builder().seq(buyerSeq).build())
                .buyerIdentificationCode(this.buyerIdentificationCode)
                .identificationType(this.identificationType)
                .buyerIdentificationId(this.buyerIdentificationId)
                .buyerIdentificationState(StateTypeConstants.DONE)
                .password(this.password)
                .staffName(this.staffName)
                .staffPhoneNo(this.staffPhoneNo)
                .staffEmail(this.staffEmail)
                .createdId(userCode)
                .createdDatetime(LocalDateTime.now())
                .build();
    }

    // 메인 회원 정보 갱신.
    public BuyerIdentification updateEntity(BuyerIdentificationDto newDto, String state) {
        return BuyerIdentification
                .builder()
                .seq(this.seq)
                .buyer(this.buyer.updateEntity(newDto, state))
                .buyerIdentificationCode(this.buyerIdentificationCode)
                .identificationType(this.identificationType)
                .buyerIdentificationId(this.buyerIdentificationId)
                .buyerIdentificationState(state)
                .password(this.password)
                .staffName(newDto.getStaffName())
                .staffPhoneNo(newDto.getStaffPhoneNo())
                .staffEmail(newDto.getStaffEmail())
                .createdId(this.createdId)
                .createdDatetime(this.createdDatetime)
                .modifiedId(newDto.getModifiedId())
                .modifiedDatetime(LocalDateTime.now())
                .build();
    }

    // 계정 삭제 처리.
    public BuyerIdentification deleteEntity(String requestUserCode) {

        return BuyerIdentification
                .builder()
                .seq(this.seq)
                .buyer(this.buyer.convertEntity())
                .buyerIdentificationCode(this.buyerIdentificationCode)
                .identificationType(this.identificationType)
                .buyerIdentificationId(this.buyerIdentificationId)
                .buyerIdentificationState(StateTypeConstants.EXPIRED)
                .password("")
                .staffName("")
                .staffPhoneNo("")
                .staffEmail("")
                .createdId(this.createdId)
                .createdDatetime(this.createdDatetime)
                .modifiedId(requestUserCode)
                .modifiedDatetime(LocalDateTime.now())
                .build();
    }

    public BuyerIdentification modifyPassword(String password, String userCode) {
        return BuyerIdentification
                .builder()
                .seq(this.seq)
                .buyer(this.buyer.convertEntity())
                .buyerIdentificationCode(this.buyerIdentificationCode)
                .identificationType(this.identificationType)
                .buyerIdentificationId(this.buyerIdentificationId)
                .buyerIdentificationState(this.buyerIdentificationState)
                .password(password)
                .staffName(this.staffName)
                .staffPhoneNo(this.staffPhoneNo)
                .staffEmail(this.staffEmail)
                .createdId(this.createdId)
                .createdDatetime(this.createdDatetime)
                .modifiedId(userCode)
                .modifiedDatetime(LocalDateTime.now())
                .build();
    }

}
