package com.bill.crm.service;

import com.bill.crm.bo.ClientBo;
import com.bill.crm.dao.ClientDaoImpl;
import com.bill.crm.dao.CompanyDaoImpl;
import com.bill.crm.vo.request.ClientRequestVo;
import com.bill.crm.vo.response.ClientResponseVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class ClientService {

    @Autowired
    private ClientDaoImpl clientDao;

    @Autowired
    private CompanyDaoImpl companyDao;

    public Optional<Long> add(ClientResponseVo clientResponseVo, String username) {
        if (isCompanyIdNotExist(clientResponseVo.getCompanyId())) {
            log.debug("companyId={} doesn't exist", clientResponseVo.getCompanyId());
            return Optional.empty();
        }

        ClientBo clientBo = ClientBo.builder()
                .companyId(clientResponseVo.getCompanyId())
                .name(clientResponseVo.getName())
                .email(clientResponseVo.getEmail())
                .phone(clientResponseVo.getPhone())
                .createdBy(username)
                .createdAt(Timestamp.from(Instant.now()))
                .build();

        return clientDao.add(clientBo);
    }

    private boolean isCompanyIdNotExist(Long companyId) {
        return !companyDao.isExist(companyId);
    }

    @Transactional
    public List<Long> addMany(List<ClientRequestVo> clientReqVoList, String username) {

        Timestamp currentTimestamp = Timestamp.from(Instant.now());
        List<ClientBo> clientBoList = clientReqVoList.stream()
                .map(e -> ClientBo.builder()
                        .companyId(e.getCompanyId())
                        .name(e.getName())
                        .email(e.getEmail())
                        .phone(e.getPhone())
                        .createdBy(username)
                        .createdAt(currentTimestamp)
                        .build()).collect(Collectors.toList());

        return clientDao.addMany(clientBoList);
    }

    public Optional<Long> updateOne(ClientRequestVo clientRequestVo, String username) {
        if (isCompanyIdNotExist(clientRequestVo.getCompanyId())) {
            log.debug("companyId={} doesn't exist", clientRequestVo.getCompanyId());
            return Optional.empty();
        }

        ClientBo clientBo = ClientBo.builder()
                .id(clientRequestVo.getId())
                .companyId(clientRequestVo.getCompanyId())
                .name(clientRequestVo.getName())
                .email(clientRequestVo.getEmail())
                .phone(clientRequestVo.getPhone())
                .updatedBy(username)
                .updatedAt(Timestamp.from(Instant.now()))
                .build();

        return clientDao.update(clientBo);
    }

    public boolean delete(Long id) {
        return clientDao.delete(id);
    }
}
