package kr.co.memberapi.admin;

import kr.co.memberapi.admin.domain.dto.AdminDto;
import kr.co.memberapi.admin.domain.entity.Admin;
import kr.co.memberapi.admin.domain.repository.AdminRepository;
import kr.co.memberapi.common.ApiRequestFailExceptionMsg;
import kr.co.memberapi.common.constants.ApiResponseCodeConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public AdminDto getAdminInfoById(String adminId){
        Admin admin = Optional.ofNullable(adminRepository.findByAdminIdAndState(adminId, "D")).orElseThrow(() -> new ApiRequestFailExceptionMsg(ApiResponseCodeConstants.NOT_EXISTS, "NO SEARCH ADMIN", adminId));
        AdminDto adminDto = AdminDto.selectByEntity().admin(admin).build();
        return adminDto;
    }

    public AdminDto getAdminInfoByCode(String adminCode){
        Admin admin = Optional.ofNullable(adminRepository.findByAdminCodeAndState(adminCode, "D")).orElseThrow(() -> new ApiRequestFailExceptionMsg(ApiResponseCodeConstants.NOT_EXISTS, "NO SEARCH ADMIN", adminCode));
        return AdminDto.selectByEntity().admin(admin).build().removePassword();
    }

}
