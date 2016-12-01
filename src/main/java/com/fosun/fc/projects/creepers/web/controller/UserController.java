package com.fosun.fc.projects.creepers.web.controller;

import java.util.Date;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fosun.fc.projects.creepers.dto.UserDTO;
import com.fosun.fc.projects.creepers.service.IUserService;

//@Controller
//@RequestMapping(value = "/mongo")
public class UserController {

    @SuppressWarnings("unused")
    @Autowired
    private IUserService userService;

    private Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "insert", method = RequestMethod.GET)
    public String init(Model model, RedirectAttributes redirectAttributes) {
        log.debug("----mongo init page");
        model.addAttribute("action", "insert");
        model.addAttribute("longTime", new Date().getTime());
        return "mongo/mongoEdit";
    }

    @RequestMapping(value = "query", method = RequestMethod.GET)
    public String initQuery(Model model, RedirectAttributes redirectAttributes) {
        log.debug("----mongo list page");
        model.addAttribute("action", "list");
        return "mongo/mongoList";
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public String create(@Valid UserDTO userDTO, RedirectAttributes redirectAttributes) {

        /*
         * User user = new User(); BeanMapper.copy(userDTO, user);
         * userService.saveUser(user);
         */
        redirectAttributes.addFlashAttribute("message", "mongo insert 測試成功");
        return "redirect:/mongo/list";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute("userDTO") UserDTO userDTO, RedirectAttributes redirectAttributes) {
        // User entity = BeanMapper.map(userDTO, User.class);
        // userService.updateUser(userDTO);
        redirectAttributes.addFlashAttribute("message", "客户信息更新成功");
        return "redirect:/mongo/mongoList";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = "15") int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
            ServletRequest request) {
        @SuppressWarnings("unused")
        String name = request.getParameter("userName");
        // List<User> users = userService.findByName(name);
        // model.addAttribute("users", users);
        model.addAttribute("sortType", sortType);
        return "mongo/mongoList";
    }
    /*
     * @ResponseBody
     * 
     * @RequestMapping(value = "/ajaxList") public Map<String, Object>
     * ajaxList(@RequestParam(value = "page", defaultValue = "1") int
     * pageNumber,
     * 
     * @RequestParam(value = "page.size", defaultValue =
     * CreditOfficerConstant.PAGE_SIZE) int pageSize,
     * 
     * @RequestParam(value = "sortType", defaultValue = "auto") String sortType,
     * Model model, ServletRequest request) { Map<String, Object> searchParams =
     * Servlets.getParametersStartingWith(request, "search_");
     * Page<CreditCustomerDTO> customers =
     * creditCustomerService.getCreditCustomerList(searchParams, pageNumber,
     * pageSize, sortType);
     * 
     * Map<String, Object> resutlMap = new HashMap<String, Object>();
     * resutlMap.put("customers", customers);
     * 
     * return resutlMap; }
     * 
     * @RequestMapping(value = "update/{id}", method = RequestMethod.GET) public
     * String updateForm(@PathVariable("id") Long id, Model model) {
     * model.addAttribute("creditCustomer",
     * creditCustomerService.findByIdAndFlag(id,
     * GlobalConstant.LOGIC_DELETE_FLAG__NO_DELETE));
     * model.addAttribute("creditCustomerType",
     * creditDictServcie.findByCategory(CreditOfficerConstant.
     * DICT_CATEGORY__CUSTOMER_LEVEL));
     * model.addAttribute("creditCustomerStatus",
     * creditDictServcie.findByCategory(CreditOfficerConstant.
     * DICT_CATEGORY__CUSTOMER_STATUS)); model.addAttribute("action", "update");
     * return "creditofficer/creditCustomerForm"; }
     * 
     * @RequestMapping(value = "update", method = RequestMethod.POST) public
     * String update(@ModelAttribute("tCreditCustomerDTO") CreditCustomerDTO
     * tCreditCustomerDTO, RedirectAttributes redirectAttributes) {
     * CreditCustomer entity = BeanMapper.map(tCreditCustomerDTO,
     * CreditCustomer.class); creditCustomerService.saveCustomer(entity);
     * redirectAttributes.addFlashAttribute("message", "客户信息更新成功"); return
     * "redirect:/creditCustomer/list"; }
     * 
     * @RequestMapping(value = "checkDuplicate", method = RequestMethod.POST)
     * public String checkDuplicate(@PathVariable("name") String name) {
     * CreditCustomer tCreditCustomer= creditCustomerService.findByName(name);
     * if(tCreditCustomer != null) { return CreditOfficerConstant.TRUE; } return
     * CreditOfficerConstant.FALSE; }
     */
}
