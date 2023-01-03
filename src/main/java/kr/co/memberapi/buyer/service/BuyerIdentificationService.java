package kr.co.memberapi.buyer.service;

import kr.co.memberapi.buyer.domain.dto.BuyerIdentificationDto;
import kr.co.memberapi.buyer.domain.entity.BuyerIdentification;
import kr.co.memberapi.buyer.domain.repository.BuyerIdentificationRepository;
import kr.co.memberapi.buyer.domain.repository.BuyerIdentificationRepositorySupport;
import kr.co.memberapi.buyer.domain.repository.BuyerRepository;
import kr.co.memberapi.common.ApiRequestFailExceptionMsg;
import kr.co.memberapi.common.constants.ApiResponseCodeConstants;
import kr.co.memberapi.common.constants.IdentificationTypeConstants;
import kr.co.memberapi.common.constants.StateTypeConstants;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@DynamicUpdate
@Transactional(rollbackFor = Exception.class)
public class BuyerIdentificationService {

    private final BuyerIdentificationRepository buyerIdentificationRepository;
    private final BuyerRepository buyerRepository;
    private final BuyerIdentificationRepositorySupport buyerIdentificationRepositorySupport;

    @Autowired
    public BuyerIdentificationService(BuyerIdentificationRepository buyerIdentificationRepository, BuyerRepository buyerRepository, BuyerIdentificationRepositorySupport buyerIdentificationRepositorySupport) {
        this.buyerIdentificationRepository = buyerIdentificationRepository;
        this.buyerRepository = buyerRepository;
        this.buyerIdentificationRepositorySupport = buyerIdentificationRepositorySupport;
    }

    // 회원계정 등록.
    public Long addBuyerIdentification(BuyerIdentificationDto buyerIdentificationDto, String userCode) {
        if (buyerIdentificationRepository.existsByBuyerIdentificationIdAndBuyerIdentificationStateNot(buyerIdentificationDto.getBuyerIdentificationId(), StateTypeConstants.EXPIRED)) {
            throw new ApiRequestFailExceptionMsg(ApiResponseCodeConstants.IS_EXISTS, "BUYER_IDENTIFICATION_ID", buyerIdentificationDto.getBuyerIdentificationId());
        }
        Long buyerSeq = buyerRepository.save(buyerIdentificationDto.getBuyer().insertEntity(userCode)).getSeq();
        buyerIdentificationRepository.save(buyerIdentificationDto.insertEntity(buyerSeq, userCode));
        return buyerSeq;
    }

    // 회원 정보 갱신.
    public Long updateBuyerIdentification(BuyerIdentificationDto newDto, String userCode) {
        BuyerIdentification buyerIdentification = buyerIdentificationRepository.findByBuyerIdentificationId(newDto.getBuyerIdentificationId());
        BuyerIdentificationDto oldDto = BuyerIdentificationDto.entityConvertDto().buyerIdentification(buyerIdentification).build();

        oldDto.setModifiedId(userCode);
        oldDto.getBuyer().setModifiedId(userCode);
        BuyerIdentification updateEntity = oldDto.updateEntity(newDto, StateTypeConstants.DONE);

        Long buyerSeq = buyerRepository.save(updateEntity.getBuyer()).getSeq();
        buyerIdentificationRepository.save(updateEntity);
        return buyerSeq;
    }

    // 서브 계정 등록
    public void joinSubBuyer(BuyerIdentificationDto buyerIdentificationDto) {

        // buyerIdentificationCode 로 메인계정 조회.
        BuyerIdentificationDto getBuyerIdentificationDto = Optional.ofNullable(buyerIdentificationRepositorySupport.getBuyerMainInformation(buyerIdentificationDto.getBuyer().getBuyerCode()))
                .orElseThrow(() -> new ApiRequestFailExceptionMsg(ApiResponseCodeConstants.NOT_EXISTS, "BUYER_IDENTIFICATION_CODE", buyerIdentificationDto.getBuyerIdentificationCode()));

        // 서브 최근 아이디 조회.
        String mainId = getBuyerIdentificationDto.getBuyerIdentificationId();
        BuyerIdentificationDto lastSubDto = buyerIdentificationRepositorySupport.getBuyerSubIdentificationId(getBuyerIdentificationDto.getBuyer().getBuyerCode());

        // 서브 아이디 생성
        String subId = "";
        if (ObjectUtils.isEmpty(lastSubDto)) {
            subId = getBuyerIdentificationDto.getBuyerIdentificationId() + "001";
        } else {
            int subSeq = Integer.valueOf(lastSubDto.getBuyerIdentificationId().replaceAll(mainId, ""));
            subSeq = subSeq + 1;
            subId = mainId + String.format("%03d", subSeq);
        }

        // 입력된 dto를 등록.
        buyerIdentificationDto.setBuyerIdentificationId(subId);
        buyerIdentificationDto.setIdentificationType(IdentificationTypeConstants.SUB);
        Long buyerSeq = getBuyerIdentificationDto.getBuyer().getSeq();
        String buyerIdentificationCode = getBuyerIdentificationDto.getBuyerIdentificationCode();
        buyerIdentificationRepository.save(buyerIdentificationDto.insertEntity(buyerSeq, buyerIdentificationCode));
    }

    public void deleteBuyerIdentification(String buyerIdentificationCode, String requestUserCode) {
        // 탈퇴해야하는 계정 정보 조회.
        BuyerIdentification buyerIdentification = buyerIdentificationRepository.findByBuyerIdentificationCode(buyerIdentificationCode);
        BuyerIdentificationDto deleteDto = BuyerIdentificationDto.entityConvertDto().buyerIdentification(buyerIdentification).build();

        if (Objects.equals(IdentificationTypeConstants.MAIN, deleteDto.getIdentificationType())) { // 메인 계정 탈퇴 시 추가적으로 서브 계정 탈퇴.

            // 서브 계정 정보 전체 조회.
            List<BuyerIdentificationDto> identificationDtoList = buyerIdentificationRepositorySupport.getIdentificationListByDelete(deleteDto.getBuyer().getBuyerCode());

            if (identificationDtoList.size() > 0) {
                // 반복문으로 전부 탈퇴 처리 후 Main 계정 탈퇴 처리 진행.
                for (BuyerIdentificationDto dto : identificationDtoList) {
                    this.deleteIdentification(dto, requestUserCode);
                }
            }

        }
        // 탈퇴 처리.
        this.deleteIdentification(deleteDto, requestUserCode);

    }

    // 계정 탈퇴 빈 값으로 업데이트. * 정보 이관 하지 않음 *
    private void deleteIdentification(BuyerIdentificationDto dto, String requestUserCode) {
        log.info("!DELETE! DELETE-BUYER-ID : {}", dto.getBuyerIdentificationId());
        buyerIdentificationRepository.save(dto.deleteEntity(requestUserCode)); // 계정 삭제
        if (Objects.equals(IdentificationTypeConstants.MAIN, dto.getIdentificationType())) {
            buyerRepository.save(dto.getBuyer().deleteEntity(requestUserCode)); // 회원 삭제
        }
    }

    public void modifySubBuyer(BuyerIdentificationDto buyerIdentificationDto) {

        // 수정요청한 기존 서브 계정 정보 조회
        BuyerIdentification getBuyerIdentification = buyerIdentificationRepository.findById(buyerIdentificationDto.getSeq()).orElseThrow(() -> new ApiRequestFailExceptionMsg(ApiResponseCodeConstants.NOT_EXISTS, "NOT_EXISTS_BUYER", buyerIdentificationDto.getSeq()));
        BuyerIdentificationDto buyerSubDto = BuyerIdentificationDto.entityConvertDto().buyerIdentification(getBuyerIdentification).build();

        // 요청한 서브 계정의 buyerSeq 와 서브계정 조회한 buyerSeq 일치하는지 확인.
        if (Objects.equals(buyerSubDto.getBuyer().getSeq(), buyerIdentificationDto.getBuyer().getSeq())) {
            buyerIdentificationDto.setBuyer(buyerSubDto.getBuyer());
            buyerIdentificationRepository.save(buyerSubDto.updateEntity(buyerIdentificationDto, StateTypeConstants.DONE));
        } else {
            throw new ApiRequestFailExceptionMsg(ApiResponseCodeConstants.NOT_MATCH, "NOT_MATCH");
        }

    }

    public BuyerIdentificationDto getBuyerInfo(String buyerId) {
        BuyerIdentification tmp = buyerIdentificationRepository.findByBuyerIdentificationId(buyerId);
        if (tmp == null || tmp.getBuyerIdentificationId() == null) {
            return new BuyerIdentificationDto();
        }
        return BuyerIdentificationDto.entityConvertDto().buyerIdentification(tmp).build();
    }

}
