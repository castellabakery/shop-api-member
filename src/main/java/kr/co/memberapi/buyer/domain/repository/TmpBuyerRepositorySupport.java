package kr.co.memberapi.buyer.domain.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.memberapi.buyer.domain.dto.TmpBuyerDto;
import kr.co.memberapi.buyer.domain.entity.QTmpBuyer;
import kr.co.memberapi.common.constants.StateTypeConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.List;


@Slf4j
@Repository
public class TmpBuyerRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public TmpBuyerRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    // 임시회원 리스트 조회
    // 검색 조건 = (전체조회, 유저구분, id, , 담당자, 계정상태)
    public Page<TmpBuyerDto> findTmpBuyerList(String buyerType, String buyerIdentificationId, String corpName, Long buyerSeq, String buyerState, String searchKeyword, Pageable pageable) {

        QTmpBuyer qTmpBuyer = QTmpBuyer.tmpBuyer;

        List<TmpBuyerDto> tmpBuyerDtoList = jpaQueryFactory.select(Projections.constructor(TmpBuyerDto.class, qTmpBuyer))
                .from(qTmpBuyer)
                .where(
                        eqBuyerType(buyerType)
                        , eqBuyerSeq(buyerSeq)
                        , containsId(buyerIdentificationId)
                        , containsCorpName(corpName)
                        , eqBuyerState(buyerState)
                        , containsSearchKeyword(searchKeyword)
                        , notEqualsDoneAndRejected()
                )
                .orderBy(qTmpBuyer.createdDatetime.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = jpaQueryFactory
                .select(qTmpBuyer.count())
                .from(qTmpBuyer)
                .where(
                        eqBuyerType(buyerType)
                        , eqBuyerSeq(buyerSeq)
                        , containsId(buyerIdentificationId)
                        , containsCorpName(corpName)
                        , eqBuyerState(buyerState)
                        , containsSearchKeyword(searchKeyword)
                        , notEqualsDoneAndRejected()
                )
                .fetchOne();

        return new PageImpl<>(tmpBuyerDtoList, pageable, count);
    }

    // 임시회원 메인계정 상세 조회.
    public TmpBuyerDto getTmpBuyerInfo(Long tmpSeq, String tmpBuyerIdentificationId) {

        QTmpBuyer qTmpBuyer = QTmpBuyer.tmpBuyer;

        return jpaQueryFactory.select(Projections.constructor(TmpBuyerDto.class, qTmpBuyer))
                .from(qTmpBuyer)
                .where(
                        eqTmpSeq(tmpSeq)
                        , eqId(tmpBuyerIdentificationId)
                        , notEqualsDoneAndRejected()
                )
                .fetchOne();
    }

    private BooleanExpression eqTmpSeq(Long tmpSeq) {
        if (ObjectUtils.isEmpty(tmpSeq)) {
            return null;
        }
        return QTmpBuyer.tmpBuyer.seq.eq(tmpSeq);
    }

    private BooleanExpression eqBuyerState(String buyerState) {
        if (ObjectUtils.isEmpty(buyerState)) {
            return null;
        }
        return QTmpBuyer.tmpBuyer.state.eq(buyerState);
    }

    private BooleanExpression eqBuyerSeq(Long buyerSeq) {
        if (ObjectUtils.isEmpty(buyerSeq)) {
            return null;
        }
        return QTmpBuyer.tmpBuyer.buyerSeq.eq(buyerSeq);
    }


    private BooleanExpression eqBuyerType(String buyerType) {
        if (ObjectUtils.isEmpty(buyerType)) {
            return null;
        }
        return QTmpBuyer.tmpBuyer.buyerType.eq(buyerType);
    }

    private BooleanExpression eqId(String id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return QTmpBuyer.tmpBuyer.buyerIdentificationId.eq(id);
    }

    private BooleanExpression containsId(String id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return QTmpBuyer.tmpBuyer.buyerIdentificationId.contains(id);
    }

    private BooleanExpression containsSearchKeyword(String searchKeyword) {
        if (ObjectUtils.isEmpty(searchKeyword)) {
            return null;
        }
        return QTmpBuyer.tmpBuyer.corpName.contains(searchKeyword).or(QTmpBuyer.tmpBuyer.buyerIdentificationId.contains(searchKeyword));
    }

    private BooleanExpression containsCorpName(String corpName) {
        if (ObjectUtils.isEmpty(corpName)) {
            return null;
        }
        return QTmpBuyer.tmpBuyer.corpName.contains(corpName);
    }

    private BooleanExpression eqStaffName(String staffName) {
        if (ObjectUtils.isEmpty(staffName)) {
            return null;
        }
        return QTmpBuyer.tmpBuyer.staffName.eq(staffName);
    }

    private BooleanExpression notEqualsDoneAndRejected() {
        return QTmpBuyer.tmpBuyer.state.notEqualsIgnoreCase(StateTypeConstants.DONE)
                .and(QTmpBuyer.tmpBuyer.state.notEqualsIgnoreCase(StateTypeConstants.REJECTED));
    }

}
