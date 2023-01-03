package kr.co.memberapi.admin.domain.repository;

import kr.co.memberapi.admin.domain.entity.AdminGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminGroupRepository extends JpaRepository<AdminGroup, Long> {
}
