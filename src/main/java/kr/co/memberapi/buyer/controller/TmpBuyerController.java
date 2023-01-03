package kr.co.memberapi.buyer.controller;

import kr.co.memberapi.buyer.domain.dto.TmpBuyerDto;
import kr.co.memberapi.buyer.service.BuyerService;
import kr.co.memberapi.common.util.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/tmp-buyer")
public class TmpBuyerController {

    private final BuyerService buyerService;

    @Autowired
    public TmpBuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    /**
     * 관리자 - 임시회원 리스트 조회.
     * 사용자 - 본인 임시회원 리스트 조회.
     *
     * @param buyerType
     * @param buyerIdentificationId
     * @param corpName
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getTmpBuyerList(@RequestParam(value = "buyerType", required = false) String buyerType,
                                                       @RequestParam(value = "buyerIdentificationId", required = false) String buyerIdentificationId,
                                                       @RequestParam(value = "corpName", required = false) String corpName,
                                                       @RequestParam(value = "buyerSeq", required = false) Long buyerSeq,
                                                       @RequestParam(value = "buyerState", required = false) String buyerState,
                                                       @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
                                                       @RequestParam int page,
                                                       @RequestParam int pageSize) {
        return ResponseEntity.ok(ApiResponse.success(buyerService.getTmpBuyerList(buyerType, buyerIdentificationId, corpName, buyerSeq, buyerState, searchKeyword, page, pageSize)));
    }

    /**
     * 관리자 - 임시회원 상세 조회.
     * 사용자 - 임시회원 상세 조회.
     *
     * @param tmpSeq
     * @return
     */
    @GetMapping
    public ResponseEntity<ApiResponse> getTmpBuyerInfo(@RequestParam Long tmpSeq) {
        return ResponseEntity.ok(ApiResponse.success(buyerService.getTmpBuyerInfo(tmpSeq)));
    }

    //TODO. 가입 거절시 SMS 발송 추가.

    /**
     * 관리자 - 임시 회원 승인/반려
     *
     * @param tmpSeq
     * @return
     */
    @PatchMapping("/state")
    public ResponseEntity<ApiResponse> modifyTmpBuyerState(@RequestParam Long tmpSeq,
                                                           @RequestParam String userCode,
                                                           @RequestParam String changeState,
                                                           @RequestParam(value = "rejectedMsg", required = false) String rejectedMsg
                                                           ) {
        return ResponseEntity.ok(ApiResponse.success(buyerService.modifyTmpBuyerState(userCode, tmpSeq, changeState, rejectedMsg)));
    }

    /**
     * 사용자 - 임시 회원 가입 요청
     * 사용자 - 임시 회원 등록 요청
     *
     * @param tmpBuyerDto
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiResponse> registerTmpBuyer(@RequestBody TmpBuyerDto tmpBuyerDto) {
        return ResponseEntity.ok(ApiResponse.success(buyerService.registerTmpBuyer(tmpBuyerDto)));
    }

}
