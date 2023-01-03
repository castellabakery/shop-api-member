package kr.co.memberapi.admin.domain.repository;

import kr.co.memberapi.admin.domain.entity.AdminGroupMenuAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminGroupMenuAuthorityRepository extends JpaRepository<AdminGroupMenuAuthority, Long> {
}
