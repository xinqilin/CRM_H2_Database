package com.bill.crm.dao;

import com.bill.crm.bo.ClientBo;
import com.bill.crm.entity.Client;
import com.bill.crm.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


//這個 service 參考肉豬寫法
@Service
public class ClientDaoImpl {


    private final ClientRepository clientRepository;

    public ClientDaoImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<ClientBo> get(Long id) {
        return clientRepository.findById(id)
                .map(ClientDaoImpl::convertToBo);
    }

    public List<ClientBo> getByCompanyId(Long companyId) {
        return clientRepository.findByCompanyId(companyId).stream()
                .map(ClientDaoImpl::convertToBo)
                .collect(Collectors.toList());
    }

    public Optional<Long> add(ClientBo clientBo) {
        Client client = convertFromBo(clientBo);
        client = clientRepository.save(client);
        return Optional.ofNullable(client.getId());
    }

    public List<Long> addMany(List<ClientBo> clientBoList) {
        List<Client> clients = clientBoList.stream()
                .map(ClientDaoImpl::convertFromBo)
                .collect(Collectors.toList());

        clients = clientRepository.saveAll(clients);
        return clients.stream()
                .map(Client::getId)
                .collect(Collectors.toList());
    }

    public Optional<Long> update(ClientBo clientBo) {
        return clientRepository.findById(clientBo.getId())
                .map(e -> {
                    e.setCompanyId(clientBo.getCompanyId());
                    e.setName(clientBo.getName());
                    e.setEmail(clientBo.getEmail());
                    e.setPhone(clientBo.getPhone());
                    e.setUpdatedBy(clientBo.getUpdatedBy());
                    e.setUpdatedAt(clientBo.getUpdatedAt());
                    return e;
                }).map(clientRepository::save)
                .map(Client::getId);
    }

    public boolean delete(Long id) {
        clientRepository.deleteById(id);
        return true;
    }

    public static ClientBo convertToBo(Client client) {
        return ClientBo.builder()
                .id(client.getId())
                .companyId(client.getCompanyId())
                .name(client.getName())
                .email(client.getEmail())
                .phone(client.getPhone())
                .createdBy(client.getCreatedBy())
                .createdAt(client.getCreatedAt())
                .updatedBy(client.getUpdatedBy())
                .updatedAt(client.getUpdatedAt())
                .build();

    }

    public static Client convertFromBo(ClientBo bo) {
        return Client.builder()
                .id(bo.getId())
                .companyId(bo.getCompanyId())
                .name(bo.getName())
                .email(bo.getEmail())
                .phone(bo.getPhone())
                .createdBy(bo.getCreatedBy())
                .createdAt(bo.getCreatedAt())
                .updatedBy(bo.getUpdatedBy())
                .updatedAt(bo.getUpdatedAt())
                .build();
    }
}
