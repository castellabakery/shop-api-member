package kr.co.memberapi.buyer.domain.repository;

import kr.co.memberapi.buyer.domain.entity.TmpBuyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TmpBuyerRepository extends JpaRepository<TmpBuyer, Long> {
    boolean existsByBuyerIdentificationIdAndStateIn(String buyerIdentificationId, List<String> state);

    boolean existsByStaffEmailAndCorpEmailAndStateIn(String staffEmail, String corpEmail, List<String> state);
}
