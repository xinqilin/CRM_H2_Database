package com.bill.crm.dao;

import com.bill.crm.bo.CompanyBo;
import com.bill.crm.entity.Company;
import com.bill.crm.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyDaoImpl {

    private final CompanyRepository companyRepository;

    public CompanyDaoImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Optional<CompanyBo> get(Long id) {
        return companyRepository.findById(id)
                .map(CompanyDaoImpl::convertToBo);
    }

    private Optional<CompanyBo> save(Company entity) {
        Company dbCompany = companyRepository.save(entity);
        if (dbCompany != null) {
            return Optional.of(convertToBo(dbCompany));
        } else {
            return Optional.empty();
        }
    }

    public Optional<CompanyBo> add(CompanyBo bo) {
        return save(convertFromBo(bo));
    }

    public Optional<CompanyBo> update(CompanyBo bo) {
        Company dbCompany = companyRepository.findById(bo.getId()).orElse(null);

        if (dbCompany != null) {
            dbCompany.setName(bo.getName());
            dbCompany.setAddress(bo.getAddress());
            dbCompany.setUpdatedAt(bo.getUpdatedAt());
            dbCompany.setUpdatedBy(bo.getUpdatedBy());
        } else {
            dbCompany = convertFromBo(bo);
        }

        return save(dbCompany);

    }

    public boolean delete(Long id) {
        boolean isSuccess = false;
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            isSuccess = true;
        }
        return isSuccess;
    }

    public boolean isExist(Long id) {
        return companyRepository.existsById(id);
    }


    public static Company convertFromBo(CompanyBo bo) {
        return Company.builder()
                .id(bo.getId())
                .name(bo.getName())
                .address(bo.getAddress())
                .createdBy(bo.getCreatedBy())
                .createdAt(bo.getCreatedAt())
                .updatedBy(bo.getUpdatedBy())
                .updatedAt(bo.getUpdatedAt())
                .build();
    }

    public static CompanyBo convertToBo(Company company) {
        return CompanyBo.builder()
                .id(company.getId())
                .name(company.getName())
                .address(company.getAddress())
                .createdBy(company.getCreatedBy())
                .createdAt(company.getCreatedAt())
                .updatedBy(company.getUpdatedBy())
                .updatedAt(company.getUpdatedAt())
                .build();
    }
}
