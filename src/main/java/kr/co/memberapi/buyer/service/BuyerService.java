package kr.co.memberapi.buyer.service;

import kr.co.memberapi.buyer.domain.dto.BuyerDto;
import kr.co.memberapi.buyer.domain.dto.BuyerIdentificationDto;
import kr.co.memberapi.buyer.domain.dto.TmpBuyerDto;
import kr.co.memberapi.buyer.domain.entity.Buyer;
import kr.co.memberapi.buyer.domain.entity.BuyerIdentification;
import kr.co.memberapi.buyer.domain.entity.TmpBuyer;
import kr.co.memberapi.buyer.domain.repository.*;
import kr.co.memberapi.common.ApiRequestFailExceptionMsg;
import kr.co.memberapi.common.constants.ApiResponseCodeConstants;
import kr.co.memberapi.common.constants.StateTypeConstants;
import kr.co.memberapi.common.constants.UserTypeConstants;
import kr.co.memberapi.common.util.DateFormatUtils;
import kr.co.memberapi.buyer.domain.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class) // TODO. readOnly = true 옵션 확인해보기.
public class BuyerService {

    private final TmpBuyerRepository tmpBuyerRepository;
    private final BuyerRepository buyerRepository;
    private final BuyerIdentificationRepositorySupport buyerIdentificationRepositorySupport;
    private final TmpBuyerRepositorySupport tmpBuyerRepositorySupport;
    private final BuyerIdentificationService buyerIdentificationService;
    private final BuyerIdentificationRepository buyerIdentificationRepository;

    @Autowired
    public BuyerService(TmpBuyerRepository tmpBuyerRepository, BuyerRepository buyerRepository, BuyerIdentificationRepositorySupport buyerIdentificationRepositorySupport, TmpBuyerRepositorySupport tmpBuyerRepositorySupport, BuyerIdentificationService buyerIdentificationService, BuyerIdentificationRepository buyerIdentificationRepository) {
        this.tmpBuyerRepository = tmpBuyerRepository;
        this.buyerRepository = buyerRepository;
        this.buyerIdentificationRepositorySupport = buyerIdentificationRepositorySupport;
        this.tmpBuyerRepositorySupport = tmpBuyerRepositorySupport;
        this.buyerIdentificationService = buyerIdentificationService;
        this.buyerIdentificationRepository = buyerIdentificationRepository;
    }

    // 회원 가입 신청
    public Map<String, Object> registerTmpBuyer(TmpBuyerDto tmpBuyerDto) {

        Map<String, Object> resultMap = new HashMap<>();
        Long seq = 0L;
        String buyerIdentificationCode = "";

        // buyerIdentificationCode 로 기존 정보 조회.
        BuyerIdentification identification = buyerIdentificationRepository.findByBuyerIdentificationCode(tmpBuyerDto.getBuyerIdentificationCode());
        BuyerIdentificationDto identificationDto = null;
        if (!ObjectUtils.isEmpty(identification)) {
            identificationDto = BuyerIdentificationDto.entityConvertDto().buyerIdentification(identification).build();
            identificationDto.getBuyer().setCi(identification.getBuyer().getCi());
        }

        // 가입 요청일때
        if (Objects.equals(tmpBuyerDto.getState(), StateTypeConstants.READY)) {
            // 조회된 정보가 있으면 Exception
            if (!ObjectUtils.isEmpty(identificationDto)) {
                throw new ApiRequestFailExceptionMsg(ApiResponseCodeConstants.IS_EXISTS, "ALREADY EXISTS ID TMP", tmpBuyerDto.getBuyerIdentificationId());
            }
            if (this.existsId(tmpBuyerDto.getBuyerIdentificationId())) {
                throw new ApiRequestFailExceptionMsg(ApiResponseCodeConstants.IS_EXISTS, "ALREADY EXISTS ID BUYER", tmpBuyerDto.getBuyerIdentificationId());
            }

            String buyerCode = UserTypeConstants.BUYER_CODE + LocalDateTime.now().format(DateFormatUtils.FULL_DATETIME);
            tmpBuyerDto.setBuyerCode(buyerCode);
            buyerIdentificationCode = UserTypeConstants.BUYER_IDENTIFICATION_CODE + LocalDateTime.now().format(DateFormatUtils.DATETIME_CODE);
            tmpBuyerDto.setBuyerIdentificationCode(buyerIdentificationCode);
            tmpBuyerDto.setCreatedId(buyerIdentificationCode);
            seq = tmpBuyerRepository.save(tmpBuyerDto.insertTemplate()).getSeq();
        }

        // 수정 요청일때
        if (Objects.equals(tmpBuyerDto.getState(), StateTypeConstants.WAIT)) { // 수정 요청일때
            // 조회된 정보가 없으면 Exception
            if (ObjectUtils.isEmpty(identificationDto)) {
                throw new ApiRequestFailExceptionMsg(ApiResponseCodeConstants.IS_EMPTY, "THE ID DOES NOT EXIST", tmpBuyerDto.getBuyerIdentificationId());
            }
            buyerIdentificationCode = identificationDto.getBuyerIdentificationCode();
            seq = tmpBuyerRepository.save(tmpBuyerDto.updateTemplate(identificationDto)).getSeq();
        }

        resultMap.put("seq", seq);
        resultMap.put("buyerIdentificationCode", buyerIdentificationCode);
        return resultMap;
    }

    // 임시회원 리스트 조회.
    public Page<TmpBuyerDto> getTmpBuyerList(String buyerType, String buyerIdentificationId, String corpName, Long buyerSeq, String buyerState, String searchKeyword, int page, int pageSize) {
        return tmpBuyerRepositorySupport.findTmpBuyerList(buyerType, buyerIdentificationId, corpName, buyerSeq, buyerState, searchKeyword, PageRequest.of(page, pageSize));
    }

    // 임시회원 메인 계정 상세 조회.
    public TmpBuyerDto getTmpBuyerInfo(Long tmpSeq) {
        return Optional.ofNullable(tmpBuyerRepositorySupport.getTmpBuyerInfo(tmpSeq, null)).orElseThrow(() -> new ApiRequestFailExceptionMsg(ApiResponseCodeConstants.NOT_EXISTS, "TMP_BUYER_SEQ", tmpSeq));
    }

    // 임시 회원가입 혹은 변경요청의 승인/반려 처리.
    public BuyerIdentificationDto modifyTmpBuyerState(String userCode, Long tmpSeq, String changeState, String rejectedMsg) {

        // 임시테이블에서 시퀀스로 변경할 정보 조회하기.
        TmpBuyer tmpBuyer = tmpBuyerRepository.findById(tmpSeq).orElseThrow(() -> new ApiRequestFailExceptionMsg(ApiResponseCodeConstants.NOT_EXISTS, "TMP_BUYER_SEQ", tmpSeq));

        if (!(Objects.equals(tmpBuyer.getState(), StateTypeConstants.WAIT) || Objects.equals(tmpBuyer.getState(), StateTypeConstants.READY))) {
            throw new ApiRequestFailExceptionMsg(ApiResponseCodeConstants.ALREADY_REQUEST, "ALREADY_REQUEST", tmpSeq);
        }

        BuyerIdentificationDto convertDto = BuyerIdentificationDto.tmpBuyerConvert().tmpBuyer(tmpBuyer).build();

        String getBuyerId = convertDto.getBuyerIdentificationId();
        String getBuyerState = tmpBuyer.getState();
        Long buyerSeq = 0L;
        if (Objects.equals(changeState, StateTypeConstants.DONE)) {
            if (Objects.equals(getBuyerState, StateTypeConstants.READY)) { // 가입 요청인 경우
                buyerSeq = buyerIdentificationService.addBuyerIdentification(convertDto, userCode);
            } else if (Objects.equals(getBuyerState, StateTypeConstants.WAIT)) { // 변경 요청인 경우
                buyerSeq = buyerIdentificationService.updateBuyerIdentification(convertDto, userCode);
            }
        }

        convertDto.getBuyer().setSeq(buyerSeq);

        TmpBuyerDto tmpBuyerDto = TmpBuyerDto.bySelectEntity().tmpBuyer(tmpBuyer).build();
        tmpBuyerDto.setPassword(tmpBuyer.getPassword());
        tmpBuyerDto.setCi(tmpBuyer.getCi());
        tmpBuyerDto.setState(changeState);
        tmpBuyerDto.setRejectedMsg(rejectedMsg);
        tmpBuyerDto.setModifiedId(userCode);
        tmpBuyerRepository.save(tmpBuyerDto.updateStateTmpBuyer(convertDto, changeState));


        if (buyerSeq > 0) {
            convertDto = this.getBuyerInformation(null, null, getBuyerId, null, null, null);
        }

        return convertDto;
    }

    // 회원 계정 리스트 조회.
    public Page<BuyerIdentificationDto> getBuyerList(String identificationType,
                                                     Long buyerSeq,
                                                     String buyerType,
                                                     String buyerIdentificationId,
                                                     String corpName,
                                                     String staffName,
                                                     String staffEmail,
                                                     String approveDatetime,
                                                     String buyerState,
                                                     String buyerCode,
                                                     String searchKeyword,
                                                     int page, int pageSize) {
        return buyerIdentificationRepositorySupport.getBuyerList(
                identificationType,
                buyerSeq,
                buyerType,
                buyerIdentificationId,
                corpName,
                staffName,
                staffEmail,
                approveDatetime,
                buyerState,
                buyerCode,
                searchKeyword,
                PageRequest.of(page, pageSize));
    }

    // 회원 계정 상세 조회.
    public BuyerIdentificationDto getBuyerInformation(Long buyerIdentificationSeq, Long buyerSeq, String id, String buyerCode, String buyerIdentificationCode, String identificationType) {
        if (ObjectUtils.isEmpty(buyerIdentificationSeq) && ObjectUtils.isEmpty(buyerSeq) && ObjectUtils.isEmpty(id) && ObjectUtils.isEmpty(buyerCode) && ObjectUtils.isEmpty(buyerIdentificationCode) && ObjectUtils.isEmpty(identificationType)) {
            throw new ApiRequestFailExceptionMsg(ApiResponseCodeConstants.IS_EMPTY, "EMPTY_REQUIRED");
        }
        return Optional.ofNullable(buyerIdentificationRepositorySupport.getBuyerInformation(buyerIdentificationSeq, buyerSeq, id, buyerCode, buyerIdentificationCode, identificationType))
                .orElseThrow(() -> new ApiRequestFailExceptionMsg(ApiResponseCodeConstants.NOT_EXISTS, "NOT EXISTS BUYER"));
    }

//    // 회원 계정 상세 조회. ver 2.0
//    public BuyerIdentificationDto getBuyerInformationV2(Long buyerIdentificationSeq, Long buyerSeq, String id, String buyerCode, String buyerIdentificationCode, String identificationType) {
//        if (ObjectUtils.isEmpty(buyerIdentificationSeq) && ObjectUtils.isEmpty(buyerSeq) && ObjectUtils.isEmpty(id) && ObjectUtils.isEmpty(buyerCode) && ObjectUtils.isEmpty(buyerIdentificationCode) && ObjectUtils.isEmpty(identificationType)) {
//            throw new ApiRequestFailExceptionMsg(ApiResponseCodeConstants.IS_EMPTY, "EMPTY_REQUIRED");
//        }
//        return Optional.ofNullable(buyerIdentificationRepositorySupport.getBuyerInformationV2(buyerIdentificationSeq, buyerSeq, id, buyerCode, buyerIdentificationCode, identificationType))
//                .orElseThrow(() -> new ApiRequestFailExceptionMsg(ApiResponseCodeConstants.NOT_EXISTS, "NOT EXISTS BUYER"));
//    }

    // 회원 상태 변경 (정지, 휴면, 정상)
    public void modifyBuyerState(Long buyerSeq, String changeState) {
        Buyer buyer = buyerRepository.findById(buyerSeq).orElseThrow(() -> new ApiRequestFailExceptionMsg(ApiResponseCodeConstants.NOT_EXISTS, "NOT_EXISTS", buyerSeq));
        BuyerDto buyerDto = BuyerDto.selectByEntity().buyer(buyer).build();
        buyerRepository.save(buyerDto.updateByState(changeState));
    }

    // 패스워드 변경
    public void modifyPassword(String buyerIdentificationCode, String oldPassword, String newPassword) {
        BuyerIdentification buyerIdentification = buyerIdentificationRepository.findByBuyerIdentificationCode(buyerIdentificationCode);
        if (!Objects.equals(buyerIdentification.getPassword(), oldPassword)) {
            throw new ApiRequestFailExceptionMsg(ApiResponseCodeConstants.NOT_MATCH, "The old password is incorrect.");
        }
        BuyerIdentificationDto buyerIdentificationDto = BuyerIdentificationDto.entityConvertDto().buyerIdentification(buyerIdentification).build();
        buyerIdentificationRepository.save(buyerIdentificationDto.modifyPassword(newPassword, buyerIdentificationDto.getBuyerIdentificationCode()));
    }

    // 패스워드 초기화
    public void initPassword(String buyerIdentificationId, String password, String userCode) {
        BuyerIdentificationDto dto = this.getBuyerInformation(null, null, buyerIdentificationId, null, null, null);
        buyerIdentificationRepository.save(dto.modifyPassword(password, userCode));
    }


    // ID 중복 검사
    public boolean existsId(String buyerId) {
        if (tmpBuyerRepository.existsByBuyerIdentificationIdAndStateIn(buyerId, Arrays.asList(StateTypeConstants.WAIT, StateTypeConstants.READY))
                || buyerIdentificationRepository.existsByBuyerIdentificationIdAndBuyerIdentificationStateNot(buyerId, StateTypeConstants.EXPIRED)) {
            return true;
        }
        return false;
    }

    // 이메일 중복 검사
    public boolean existsEmail(String email) {
        if (tmpBuyerRepository.existsByStaffEmailAndCorpEmailAndStateIn(email, email, Arrays.asList(StateTypeConstants.WAIT, StateTypeConstants.READY))
                || buyerIdentificationRepository.existsByStaffEmailAndBuyerIdentificationStateNot(email, StateTypeConstants.EXPIRED)
                || buyerRepository.existsByCorpEmailAndBuyerStateNot(email, StateTypeConstants.EXPIRED)) {
            return true;
        }
        return false;
    }

    //회원코드 존재 여부 검사.
    public boolean existsBuyerCode(String buyerCode) {
        if (buyerRepository.existsByBuyerCode(buyerCode)) {
            return true;
        }
        return false;
    }

    // 회원 상세 조회
    public Buyer getBuyerDetail(String buyerCode) {
        if (ObjectUtils.isEmpty(buyerCode)) {
            throw new ApiRequestFailExceptionMsg(ApiResponseCodeConstants.IS_EMPTY, "EMPTY_REQUIRED");
        }

        return buyerRepository.findByBuyerCode(buyerCode).orElseThrow(() -> new ApiRequestFailExceptionMsg(ApiResponseCodeConstants.NOT_EXISTS, "NOT EXISTS BUYER"));
    }

}
