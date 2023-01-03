package kr.co.memberapi.buyer.controller;

import kr.co.memberapi.buyer.domain.dto.BuyerIdentificationDto;
import kr.co.memberapi.buyer.domain.entity.Buyer;
import kr.co.memberapi.buyer.service.BuyerIdentificationService;
import kr.co.memberapi.buyer.service.BuyerService;
import kr.co.memberapi.common.util.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/buyer")
public class BuyerController {

    private final BuyerService buyerService;
    private final BuyerIdentificationService buyerIdentificationService;

    @Autowired
    public BuyerController(BuyerService buyerService, BuyerIdentificationService buyerIdentificationService) {
        this.buyerService = buyerService;
        this.buyerIdentificationService = buyerIdentificationService;
    }

    /**
     * 관리자 - 메인 계정 리스트 조회.
     * 관리자 - 하나의 메인 계정에 대한 서브 계정 리스트 조회.
     * 사용자 - 본인 메인 계정에 속해있는 서브 계정 리스트 조회.
     */
    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getBuyerList(@RequestParam(value = "identificationType", required = false) String identificationType,
                                                    @RequestParam(value = "buyerSeq", required = false) Long buyerSeq,
                                                    @RequestParam(value = "buyerType", required = false) String buyerType,
                                                    @RequestParam(value = "buyerIdentificationId", required = false) String buyerIdentificationId,
                                                    @RequestParam(value = "corpName", required = false) String corpName,
                                                    @RequestParam(value = "staffName", required = false) String staffName,
                                                    @RequestParam(value = "staffEmail", required = false) String staffEmail,
                                                    @RequestParam(value = "approveDatetime", required = false) String approveDatetime,
                                                    @RequestParam(value = "buyerState", required = false) String buyerState,
                                                    @RequestParam(value = "buyerCode", required = false) String buyerCode,
                                                    @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
                                                    @RequestParam int page,
                                                    @RequestParam int pageSize) {
        return ResponseEntity.ok(ApiResponse.success(buyerService.getBuyerList(identificationType, buyerSeq, buyerType, buyerIdentificationId, corpName, staffName, staffEmail, approveDatetime, buyerState, buyerCode, searchKeyword, page, pageSize)));
    }

    /**
     * 관리자 - 메인 계정 상세 조회.
     * 사용자 - 본인 계정 상세 조회.
     * 사용자 - 서브 계정 상세 조회.
     *
     * @param buyerIdentificationSeq
     * @param buyerIdentificationId
     * @param buyerCode
     * @param buyerIdentificationCode
     * @return
     */
    @GetMapping
    public ResponseEntity<ApiResponse> getBuyerIdentificationInfo(@RequestParam(value = "buyerIdentificationSeq", required = false) Long buyerIdentificationSeq,
                                                                  @RequestParam(value = "buyerSeq", required = false) Long buyerSeq,
                                                                  @RequestParam(value = "buyerIdentificationId", required = false) String buyerIdentificationId,
                                                                  @RequestParam(value = "buyerCode", required = false) String buyerCode,
                                                                  @RequestParam(value = "identificationType", required = false) String identificationType,
                                                                  @RequestParam(value = "buyerIdentificationCode", required = false) String buyerIdentificationCode) {
        return ResponseEntity.ok(ApiResponse.success(buyerService.getBuyerInformation(buyerIdentificationSeq, buyerSeq, buyerIdentificationId, buyerCode, buyerIdentificationCode, identificationType)));
    }

//    /**
//     * ver 2.0 메인/서브 타입 추가
//     * 관리자 - 메인 계정 상세 조회.
//     * 사용자 - 본인 계정 상세 조회.
//     * 사용자 - 서브 계정 상세 조회.
//     *
//     * @param buyerIdentificationSeq
//     * @param buyerIdentificationId
//     * @param buyerCode
//     * @param buyerIdentificationCode
//     * @return
//     */
//    @GetMapping("/ver2")
//    public ResponseEntity<ApiResponse> getBuyerIdentificationInfoVer2(@RequestParam(value = "buyerIdentificationSeq", required = false) Long buyerIdentificationSeq,
//                                                                  @RequestParam(value = "buyerSeq", required = false) Long buyerSeq,
//                                                                  @RequestParam(value = "buyerIdentificationId", required = false) String buyerIdentificationId,
//                                                                  @RequestParam(value = "buyerCode", required = false) String buyerCode,
//                                                                  @RequestParam(value = "buyerIdentificationCode", required = false) String buyerIdentificationCode,
//                                                                  @RequestParam(value = "identificationType", required = false) String identificationType) {
//        return ResponseEntity.ok(ApiResponse.success(buyerService.getBuyerInformationV2(buyerIdentificationSeq, buyerSeq, buyerIdentificationId, buyerCode, buyerIdentificationCode, identificationType)));
//    }

    /**
     * 관리자 - 회원 상태 변경. (정지, 휴면, 정상)
     *
     * @param buyerSeq
     * @param changeState
     * @return
     */
    @PatchMapping("/buyer-state")
    public ResponseEntity<ApiResponse> modifyBuyerState(@RequestParam Long buyerSeq,
                                                        @RequestParam String changeState) {
        buyerService.modifyBuyerState(buyerSeq, changeState);
        return ResponseEntity.ok(ApiResponse.success());
    }

    /**
     * 사용자 - 회원 부계정 등록.
     *
     * @param buyerIdentificationDto
     * @return
     */
    @PostMapping("/sub")
    public ResponseEntity<ApiResponse> joinSubBuyer(@RequestBody BuyerIdentificationDto buyerIdentificationDto) {
        buyerIdentificationService.joinSubBuyer(buyerIdentificationDto);
        return ResponseEntity.ok(ApiResponse.success());
    }

    /**
     * 사용자 - 회원 부계정 수정.
     *
     * @param buyerIdentificationDto
     * @return
     */
    @PatchMapping("/sub")
    public ResponseEntity<ApiResponse> modifySubBuyer(@RequestBody BuyerIdentificationDto buyerIdentificationDto) {
        buyerIdentificationService.modifySubBuyer(buyerIdentificationDto);
        return ResponseEntity.ok(ApiResponse.success());
    }

    /**
     * 사용자 - 회원 코드 존재 여부.
     *
     * @param buyerCode
     * @return
     */
    @GetMapping("/exists/buyer-code")
    public ResponseEntity<ApiResponse> existsBuyerCode(@RequestParam String buyerCode) {
        return ResponseEntity.ok(ApiResponse.success(buyerService.existsBuyerCode(buyerCode)));
    }

    /**
     * 사용자 - 회원 아이디 존재 여부.
     *
     * @param buyerIdentificationId
     * @return
     */
    @GetMapping("/exists/buyer-identification-id")
    public ResponseEntity<ApiResponse> existsBuyerIdentificationId(@RequestParam String buyerIdentificationId) {
        return ResponseEntity.ok(ApiResponse.success(buyerService.existsId(buyerIdentificationId)));
    }

    /**
     * 사용자 - 회원 이메일 존재 여부.
     *
     * @param email
     * @return
     */
    @GetMapping("/exists/email")
    public ResponseEntity<ApiResponse> existsEmail(@RequestParam String email) {
        return ResponseEntity.ok(ApiResponse.success(buyerService.existsEmail(email)));
    }

    /**
     * buyerIdentificationCode 조회 -> 패스워드 변경
     *
     * @param buyerIdentificationCode
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PatchMapping("/password")
    public ResponseEntity<ApiResponse> modifyPassword(@RequestParam String buyerIdentificationCode, @RequestParam String oldPassword, @RequestParam String newPassword) {
        buyerService.modifyPassword(buyerIdentificationCode, oldPassword, newPassword);
        return ResponseEntity.ok(ApiResponse.success());
    }

    /**
     * buyerIdentificationId 조회 -> 패스워드 초기화
     *
     * @param buyerIdentificationId
     * @param password
     * @param userCode
     * @return
     */
    @PatchMapping("/password/init")
    public ResponseEntity<ApiResponse> initPassword(@RequestParam(value = "buyerIdentificationId", required = false) String buyerIdentificationId,
                                                    @RequestParam(value = "password", required = false) String password,
                                                    @RequestParam(value = "userCode", required = false) String userCode) {
        buyerService.initPassword(buyerIdentificationId, password, userCode);
        return ResponseEntity.ok(ApiResponse.success());
    }

    /**
     * 사용자 - 회원 탈퇴 처리.
     *
     * @param buyerIdentificationCode
     * @param requestUserCode
     * @return
     */
    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteBuyerIdentification(@RequestParam(value = "buyerIdentificationCode", required = false) String buyerIdentificationCode,
                                                                 @RequestParam(value = "requestUserCode", required = false) String requestUserCode) {
        buyerIdentificationService.deleteBuyerIdentification(buyerIdentificationCode, requestUserCode);
        return ResponseEntity.ok(ApiResponse.success());
    }


    /**
     * 사용자 - 로그인 처리용 데이터 조회
     *
     * @param buyerId
     * @return
     */
    @GetMapping("/login")
    public ResponseEntity<ApiResponse> getBuyerInfo(@RequestParam String buyerId) {
        BuyerIdentificationDto result;
        try {
            result = buyerIdentificationService.getBuyerInfo(buyerId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(ApiResponse.fail("nodata"));
        }
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 사용자 - 회원 상세 조회
     *
     * @param buyerCode
     * @return
     */
    @GetMapping("/detail")
    public ResponseEntity<ApiResponse> getBuyerDetail(@RequestParam String buyerCode) {
        Buyer result;
        try {
            result = buyerService.getBuyerDetail(buyerCode);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(ApiResponse.fail("nodata"));
        }
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
