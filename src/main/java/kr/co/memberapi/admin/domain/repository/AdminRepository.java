package kr.co.memberapi.admin.domain.repository;

import kr.co.memberapi.admin.domain.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByAdminIdAndState(String adminId, String state);
    Admin findByAdminCodeAndState(String adminCode, String state);
}
