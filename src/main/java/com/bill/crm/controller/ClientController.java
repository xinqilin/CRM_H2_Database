package com.bill.crm.controller;

import com.bill.crm.service.ClientService;
import com.bill.crm.service.CompanyService;
import com.bill.crm.service.UserService;
import com.bill.crm.vo.request.ClientRequestVo;
import com.bill.crm.vo.response.ResponseOneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/client")
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @GetMapping("/view/{id}")
    public ResponseOneVo getOne(@PathVariable Long id) {
        return clientService.get(id)
                .map(ResponseOneVo::success)
                .orElse(ResponseOneVo.empty());
    }

    @PostMapping("/add")
    public ResponseOneVo addOne(@RequestBody ClientRequestVo clientRequestVo) {
        String username = userService.getCurrentUsername();
        return clientService.add(clientRequestVo, username)
                .map(ResponseOneVo::success)
                .orElse(ResponseOneVo.fail());
    }

    @PostMapping("/add/many")
    public ResponseOneVo addMany(@RequestBody List<ClientRequestVo> clientReqDtoList) {
        String username = userService.getCurrentUsername();
        List<Long> idList = clientService.addMany(clientReqDtoList, username);
        return ResponseOneVo.success(idList);
    }

    @PutMapping("/update")
    public ResponseOneVo updateOne(@RequestBody ClientRequestVo clientReqDto) {
        String username = userService.getCurrentUsername();
        return clientService.updateOne(clientReqDto, username)
                .map(ResponseOneVo::success)
                .orElse(ResponseOneVo.fail());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseOneVo deleteOne(@PathVariable Long id) {
        boolean success = clientService.delete(id);
        if (success) {
            return ResponseOneVo.success();
        } else {
            return ResponseOneVo.fail();
        }
    }

}
