package com.bill.crm.service;

import com.bill.crm.bo.CompanyBo;
import com.bill.crm.dao.ClientDaoImpl;
import com.bill.crm.dao.CompanyDaoImpl;
import com.bill.crm.vo.request.CompanyRequestVo;
import com.bill.crm.vo.response.CompanyResponseVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Log4j2
@Service
public class CompanyService {

    @Autowired
    private CompanyDaoImpl companyDao;

    @Autowired
    private ClientDaoImpl clientDao;

    public Optional<CompanyResponseVo> get(Long id) {
        return companyDao.get(id)
                .map(CompanyResponseVo::valueOf);
    }

    public Optional<CompanyBo> add(CompanyRequestVo companyRequestVo, String username) {
        CompanyBo companyBo = CompanyBo.builder()
                .name(companyRequestVo.getName())
                .address(companyRequestVo.getAddress())
                .createdBy(username)
                .createdAt(Timestamp.from(Instant.now()))
                .build();

        return companyDao.add(companyBo);
    }

    public Optional<CompanyBo> update(CompanyRequestVo companyRequestVo, String username) {
        CompanyBo companyBo = CompanyBo.builder()
                .id(companyRequestVo.getId())
                .name(companyRequestVo.getName())
                .address(companyRequestVo.getAddress())
                .updatedBy(username)
                .updatedAt(Timestamp.from(Instant.now()))
                .build();

        return companyDao.update(companyBo);
    }

    public boolean delete(Long id) {
        if (clientDao.getByCompanyId(id).size() > 0) {
            log.debug("id={} is in use.", id);
            return false;
        }
        return companyDao.delete(id);
    }
}
