package kr.co.memberapi.admin.auth.menu;

import kr.co.memberapi.admin.domain.dto.AdminMenuAuthorityDto;
import kr.co.memberapi.admin.domain.entity.AdminMenuAuthority;
import kr.co.memberapi.admin.domain.repository.AdminMenuAuthorityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminAuthMenuService {

    private final AdminMenuAuthorityRepository adminMenuAuthorityRepository;

    public AdminAuthMenuService(AdminMenuAuthorityRepository adminMenuAuthorityRepository) {
        this.adminMenuAuthorityRepository = adminMenuAuthorityRepository;
    }

    public void addAdminMenuAuthority(AdminMenuAuthorityDto adminMenuAuthorityDto) {
        adminMenuAuthorityRepository.save(adminMenuAuthorityDto.insertEntity());
    }

    public List<AdminMenuAuthority> getAdminMenuAuthorityList() {
        List<AdminMenuAuthority> adminMenuAuthorityList = adminMenuAuthorityRepository.findAll();
        return adminMenuAuthorityList;
    }

    public AdminMenuAuthority getAdminMenuAuthority(Long seq) {
        AdminMenuAuthority adminMenuAuthority = adminMenuAuthorityRepository.findById(seq).orElseThrow(() -> new RuntimeException("getAdminMenuAuthority select null!!"));
        return adminMenuAuthority;
    }

}
