package com.bill.crm.controller;

import com.bill.crm.service.CompanyService;
import com.bill.crm.service.UserService;
import com.bill.crm.vo.request.CompanyRequestVo;
import com.bill.crm.vo.response.ResponseOneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/company")
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @GetMapping("/view/{id}")
    public ResponseOneVo getOne(@PathVariable Long id) {
        return companyService.get(id)
                .map(ResponseOneVo::success)
                .orElse(ResponseOneVo.fail());
    }

    @PostMapping("/add")
    public ResponseOneVo addOne(@RequestBody CompanyRequestVo companyRequestVo) {
        String username = userService.getCurrentUsername();
        return companyService.add(companyRequestVo, username)
                .map(ResponseOneVo::success)
                .orElse(ResponseOneVo.fail());
    }

    @PutMapping("/update")
    public ResponseOneVo updateOne(@RequestBody CompanyRequestVo companyRequestVo) {
        String username = userService.getCurrentUsername();
        return companyService.update(companyRequestVo, username)
                .map(ResponseOneVo::success)
                .orElse(ResponseOneVo.fail());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseOneVo deleteOne(@PathVariable Long id) {
        boolean success = companyService.delete(id);
        if (success) {
            return ResponseOneVo.success();
        } else {
            return ResponseOneVo.fail();
        }
    }
}
