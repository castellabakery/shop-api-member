package kr.co.memberapi.buyer.domain.repository;

import kr.co.memberapi.buyer.domain.entity.BuyerIdentification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerIdentificationRepository extends JpaRepository<BuyerIdentification, Long> {
    boolean existsByBuyerIdentificationIdAndBuyerIdentificationStateNot(String buyerIdentificationId, String state);

    boolean existsByStaffEmailAndBuyerIdentificationStateNot(String email, String state);

    BuyerIdentification findByBuyerIdentificationId(String buyerIdentificationId);
    BuyerIdentification findByBuyerIdentificationCode(String buyerIdentificationCode);
}
