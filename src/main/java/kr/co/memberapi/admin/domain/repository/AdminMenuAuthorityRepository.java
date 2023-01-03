package kr.co.memberapi.admin.domain.repository;

import kr.co.memberapi.admin.domain.entity.AdminMenuAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMenuAuthorityRepository extends JpaRepository<AdminMenuAuthority, Long> {
}
