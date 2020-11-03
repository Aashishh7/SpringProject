package com.cdac.rto.controller;

import  com.cdac.rto.domain.Details;
import  com.cdac.rto.service.ContactService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/user/contact_form")
    public String contactForm(Model m) {
    	Details Details = new Details();
        m.addAttribute("command", Details);
        return "contact_form";
    }

    @RequestMapping(value = "/user/edit_contact")
    public String prepareEditForm(Model m, HttpSession session, @RequestParam("cid") Integer vehicleId) {
        session.setAttribute("aVehicleId", vehicleId);
        Details c = contactService.findById(vehicleId);
        m.addAttribute("command", c);
        return "contact_form";//JSP form view
    }

    @RequestMapping(value = "/user/save_contact")
    public String saveOrUpdateContact(@ModelAttribute("command") Details c, Model m, HttpSession session) {
        Integer vehicleId = (Integer) session.getAttribute("aVehicleId");
        if (vehicleId == null) {
    
            try {
                Integer userId = (Integer) session.getAttribute("userId");
                c.setUserId(userId);//FK ; logged in userId
                contactService.save(c);
                return "redirect:clist?act=sv";//redirect user to contact list url
            } catch (Exception e) {
                e.printStackTrace();
                m.addAttribute("err", "Failed to save contact");
                return "contact_form";
            }
        } else {
            //update task
            try {
                c.setVehicleId(vehicleId); //PK
                contactService.update(c);
                session.removeAttribute("aVehicleId");
                return "redirect:clist?act=ed";//redirect user to contact list url
            } catch (Exception e) {
                e.printStackTrace();
                m.addAttribute("err", "Failed to Edit contact");
                return "contact_form";
            }
        }
    }

    @RequestMapping(value = "/user/clist")
    public String contactList(Model m, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        m.addAttribute("contactList", contactService.findUserDetails(userId));
        return "clist"; //JSP
    }

    @RequestMapping(value = "/user/contact_search")
    public String contactSearch(Model m, HttpSession session, @RequestParam("freeText") String freeText) {
        Integer userId = (Integer) session.getAttribute("userId");
        m.addAttribute("contactList", contactService.findUserDetails(userId, freeText));
        return "clist"; //JSP
    }

    @RequestMapping(value = "/user/del_contact")
    public String deleteContact(@RequestParam("cid") Integer vehicleId) {
        contactService.delete(vehicleId);
        return "redirect:clist?act=del";
    }

    @RequestMapping(value = "/user/bulk_cdelete")
    public String deleteBulkContact(@RequestParam("cid") Integer[] vehicleIds) {
        contactService.delete(vehicleIds);
        return "redirect:clist?act=del";
    }
}
