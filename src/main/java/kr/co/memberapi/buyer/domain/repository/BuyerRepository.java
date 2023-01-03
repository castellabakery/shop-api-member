package kr.co.memberapi.buyer.domain.repository;

import kr.co.memberapi.buyer.domain.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {

    boolean existsByBuyerCode(String BuyerCode);

    boolean existsByCorpEmailAndBuyerStateNot(String Email, String state);

    Optional<Buyer> findByBuyerCode(String buyerCode);
}
