package kr.co.memberapi.buyer.domain.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.memberapi.buyer.domain.dto.BuyerIdentificationDto;
import kr.co.memberapi.buyer.domain.entity.QBuyer;
import kr.co.memberapi.buyer.domain.entity.QBuyerIdentification;
import kr.co.memberapi.common.constants.IdentificationTypeConstants;
import kr.co.memberapi.common.constants.StateTypeConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Repository
public class BuyerIdentificationRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public BuyerIdentificationRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    // 회원 계정 리스트 조회
    public Page<BuyerIdentificationDto> getBuyerList(String identificationType, Long buyerSeq, String buyerType,
                                                     String buyerIdentificationId, String corpName, String staffName,
                                                     String staffEmail, String approveDatetime,
                                                     String buyerState, String buyerCode, String searchKeyword, Pageable pageable) {

        QBuyer qBuyer = QBuyer.buyer;
        QBuyerIdentification qBuyerIdentification = QBuyerIdentification.buyerIdentification;

        List<BuyerIdentificationDto> buyerIdentificationDtoList = jpaQueryFactory.select(Projections.constructor(BuyerIdentificationDto.class, qBuyerIdentification, qBuyer))
                .from(qBuyerIdentification)
                .leftJoin(qBuyer).on(qBuyer.seq.eq(qBuyerIdentification.buyer.seq))
                .where(
                        eqIdentificationType(identificationType) // 조회타입 : 메인계정 or 서브계정
                        , eqBuyerSeq(buyerSeq) // 회원시퀀스
                        , eqBuyerType(buyerType) //
                        , eqStaffName(staffName) // 담당자명
                        , eqStaffEmail(staffEmail) // 담당자 이메일
                        , eqApproveDatetime(approveDatetime) // 승인일
                        , eqBuyerState(buyerState) // 회원 상태(정지, 휴면, 정상)
                        , eqBuyerCode(buyerCode) // 회원코드
                        , containsId(buyerIdentificationId) // 회원아이디
                        , containsCorpName(corpName) // 법인명
                        , containsSearchKeyword(searchKeyword)// like 검색절 : ID, 담당자 -- 수정필요
                        , notInExpired()

                )
                .orderBy(QBuyer.buyer.createdDatetime.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = jpaQueryFactory
                .select(qBuyerIdentification.count())
                .from(qBuyerIdentification)
                .leftJoin(qBuyer).on(qBuyer.seq.eq(qBuyerIdentification.buyer.seq))
                .where(
                        eqIdentificationType(identificationType) // 조회타입 : 메인계정 or 서브계정
                        , eqBuyerSeq(buyerSeq) // 회원시퀀스
                        , eqBuyerType(buyerType) // , ,
                        , eqStaffName(staffName) // 담당자명
                        , eqStaffEmail(staffEmail) // 담당자 이메일
                        , eqApproveDatetime(approveDatetime) // 승인일
                        , eqBuyerState(buyerState) // 회원 상태(정지, 휴면, 정상)
                        , eqBuyerCode(buyerCode) // 회원코드
                        , containsId(buyerIdentificationId) // 회원아이디
                        , containsCorpName(corpName) // 법인명
                        , containsSearchKeyword(searchKeyword)//  like 검색절 : ID, 담당자 -- 수정필요
                        , notInExpired()
                )
                .fetchOne();

        return new PageImpl<>(buyerIdentificationDtoList, pageable, count);
    }

    // 회원 계정 상세 조회.
    public BuyerIdentificationDto getBuyerInformation(Long buyerIdentificationSeq, Long buyerSeq, String buyerIdentificationId, String buyerCode, String buyerIdentificationCode, String identificationType) {
        QBuyer qBuyer = QBuyer.buyer;
        QBuyerIdentification qBuyerIdentification = QBuyerIdentification.buyerIdentification;

        return jpaQueryFactory.select(Projections.constructor(BuyerIdentificationDto.class, qBuyerIdentification, qBuyer))
                .from(qBuyerIdentification)
                .leftJoin(qBuyer).on(qBuyerIdentification.buyer.seq.eq(qBuyer.seq))
                .where(
                        eqBuyerIdentificationSeq(buyerIdentificationSeq)
                        , eqId(buyerIdentificationId)
                        , eqBuyerCode(buyerCode)
                        , eqBuyerIdentificationCode(buyerIdentificationCode)
                        , eqBuyerSeq(buyerSeq)
                        , eqIdentificationType(identificationType)
                        , notInExpired()
                )
                .fetchOne();
    }

    public List<BuyerIdentificationDto> getIdentificationListByDelete(String buyerCode) {
        QBuyerIdentification qBuyerIdentification = QBuyerIdentification.buyerIdentification;

        return jpaQueryFactory.select(Projections.constructor(BuyerIdentificationDto.class, qBuyerIdentification))
                .from(qBuyerIdentification)
                .where(
                        eqIdentificationType(IdentificationTypeConstants.SUB)
                        , qBuyerIdentification.buyer.buyerCode.eq(buyerCode)
                        , notInExpired()
                ).fetch();

    }

//    // 회원 계정 상세 조회. ver 2.0
//    public BuyerIdentificationDto getBuyerInformationV2(Long buyerIdentificationSeq, Long buyerSeq, String buyerIdentificationId, String buyerCode, String buyerIdentificationCode, String identificationType) {
//        QBuyer qBuyer = QBuyer.buyer;
//        QBuyerIdentification qBuyerIdentification = QBuyerIdentification.buyerIdentification;
//
//        return jpaQueryFactory.select(Projections.constructor(BuyerIdentificationDto.class, qBuyerIdentification, qBuyer))
//                .from(qBuyerIdentification)
//                .leftJoin(qBuyer).on(qBuyerIdentification.buyer.seq.eq(qBuyer.seq))
//                .where(
//                        eqBuyerIdentificationSeq(buyerIdentificationSeq)
//                        , eqId(buyerIdentificationId)
//                        , eqBuyerCode(buyerCode)
//                        , eqBuyerIdentificationCode(buyerIdentificationCode)
//                        , eqBuyerSeq(buyerSeq)
//                        , eqIdentificationType(identificationType)
//                        , notEqualsDoneAndRejected()
//                )
//                .fetchOne();
//    }

    public BuyerIdentificationDto getBuyerMainInformation(String buyerCode) {
        QBuyer qBuyer = QBuyer.buyer;
        QBuyerIdentification qBuyerIdentification = QBuyerIdentification.buyerIdentification;

        return jpaQueryFactory.select(Projections.constructor(BuyerIdentificationDto.class, qBuyerIdentification, qBuyer))
                .from(qBuyerIdentification)
                .leftJoin(qBuyer).on(qBuyerIdentification.buyer.seq.eq(qBuyer.seq))
                .where(
                        eqBuyerCode(buyerCode)
                        , eqIdentificationType(IdentificationTypeConstants.MAIN)
                )
                .fetchOne();
    }

    // 상태 상관없이 메인계정 소속 등록된 최근 계정 조회.
    public BuyerIdentificationDto getBuyerSubIdentificationId(String buyerCode) {
        QBuyer qBuyer = QBuyer.buyer;
        QBuyerIdentification qBuyerIdentification = QBuyerIdentification.buyerIdentification;

        return jpaQueryFactory.select(Projections.constructor(BuyerIdentificationDto.class, qBuyerIdentification))
                .from(qBuyerIdentification)
                .leftJoin(qBuyer).on(qBuyer.seq.eq(qBuyerIdentification.buyer.seq))
                .where(
                        eqBuyerCode(buyerCode)
                        , eqIdentificationType(IdentificationTypeConstants.SUB)
                )
                .orderBy(qBuyerIdentification.createdDatetime.desc())
                .limit(1)
                .fetchOne();
    }

    private BooleanExpression notInExpired() {
        return QBuyerIdentification.buyerIdentification.buyerIdentificationState
                .ne(StateTypeConstants.EXPIRED);
    }

    private BooleanExpression eqBuyerIdentificationSeq(Long buyerIdentificationSeq) {
        if (ObjectUtils.isEmpty(buyerIdentificationSeq)) {
            return null;
        }
        return QBuyerIdentification.buyerIdentification.seq.eq(buyerIdentificationSeq);
    }

    private BooleanExpression eqBuyerSeq(Long buyerSeq) {
        if (ObjectUtils.isEmpty(buyerSeq)) {
            return null;
        }
        return QBuyer.buyer.seq.eq(buyerSeq);
    }

    private BooleanExpression containsSearchKeyword(String searchKeyword) {
        if (ObjectUtils.isEmpty(searchKeyword)) {
            return null;
        }
        // like 검색절 : ID, 담당자
        return QBuyerIdentification.buyerIdentification.buyerIdentificationId.contains(searchKeyword)
                .or(QBuyerIdentification.buyerIdentification.staffName.contains(searchKeyword));
    }

    private BooleanExpression eqBuyerType(String buyerType) {
        if (ObjectUtils.isEmpty(buyerType)) {
            return null;
        }
        return QBuyer.buyer.buyerType.eq(buyerType);
    }

    private BooleanExpression containsId(String buyerIdentificationId) {
        if (ObjectUtils.isEmpty(buyerIdentificationId)) {
            return null;
        }
        return QBuyerIdentification.buyerIdentification.buyerIdentificationId.contains(buyerIdentificationId);
    }

    private BooleanExpression eqId(String buyerIdentificationId) {
        if (ObjectUtils.isEmpty(buyerIdentificationId)) {
            return null;
        }
        return QBuyerIdentification.buyerIdentification.buyerIdentificationId.eq(buyerIdentificationId);
    }

    private BooleanExpression eqStaffEmail(String staffEmail) {
        if (ObjectUtils.isEmpty(staffEmail)) {
            return null;
        }
        return QBuyerIdentification.buyerIdentification.staffEmail.eq(staffEmail);
    }

    private BooleanExpression eqApproveDatetime(String approveDatetime) {
        if (ObjectUtils.isEmpty(approveDatetime)) {
            return null;
        }
        return QBuyer.buyer.approveDatetime.between(
                LocalDateTime.parse(approveDatetime + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                LocalDateTime.parse(approveDatetime + " 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }

    private BooleanExpression eqBuyerCode(String buyerCode) {
        if (ObjectUtils.isEmpty(buyerCode)) {
            return null;
        }
        return QBuyer.buyer.buyerCode.eq(buyerCode);
    }

    private BooleanExpression eqBuyerIdentificationCode(String buyerIdentificationCode) {
        if (ObjectUtils.isEmpty(buyerIdentificationCode)) {
            return null;
        }
        return QBuyerIdentification.buyerIdentification.buyerIdentificationCode.eq(buyerIdentificationCode);
    }

    private BooleanExpression containsCorpName(String corpName) {
        if (ObjectUtils.isEmpty(corpName)) {
            return null;
        }
        return QBuyer.buyer.corpName.contains(corpName);
    }

    private BooleanExpression eqStaffName(String staffName) {
        if (ObjectUtils.isEmpty(staffName)) {
            return null;
        }
        return QBuyerIdentification.buyerIdentification.staffName.eq(staffName);
    }

    private BooleanExpression eqBuyerState(String buyerState) {
        if (ObjectUtils.isEmpty(buyerState)) {
            return null;
        }
        return QBuyer.buyer.buyerState.eq(buyerState);
    }

    private BooleanExpression eqIdentificationType(String identificationType) {
        if (ObjectUtils.isEmpty(identificationType)) {
            return null;
        }
        return QBuyerIdentification.buyerIdentification.identificationType.eq(identificationType);
    }

}
