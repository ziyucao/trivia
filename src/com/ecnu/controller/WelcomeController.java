package com.ecnu.controller;

import com.ecnu.dao.DBConnection;
import com.ecnu.entities.AvailableGroupEntity;
import com.ecnu.entities.CurrentGroupEntity;
import com.ecnu.entities.PlayerEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WelcomeController {
    @RequestMapping("/")
    public String WelcomeController() {
        return "welcome";
    }

    @RequestMapping(value = "/waitingroom", method = RequestMethod.POST)
    public String login(ModelMap model, @RequestParam String name) {
        Session s = DBConnection.getSession();
        String hql = "select userId from PlayerEntity where userId = ?";
        Query q = s.createQuery(hql);
        q.setParameter(0, name);
        List l = q.list();
        String redirect;
        if (l == null || l.size() == 0) {
            Transaction t = s.beginTransaction();
            PlayerEntity p = new PlayerEntity();
            p.setUserId(name);
            List<CurrentGroupEntity> cg = s.createQuery("from CurrentGroupEntity ").list();
            CurrentGroupEntity cge = cg.get(0);
            if (cge.getId() == 0) {
                cge.setId(1);
                cge.setPlayerSum(1);
                p.setGroupId(1);
                p.setIdInGroup(1);
                p.setIsAnswering(0);
                p.setPosition(0);
            } else {
                p.setGroupId(cge.getId());
                p.setIdInGroup(cge.getPlayerSum() + 1);
                p.setIsAnswering(0);
                p.setPosition(0);
                if (cge.getPlayerSum() == 5) {
                    List<AvailableGroupEntity> al = s.createQuery("select id from AvailableGroupEntity ").list();
                    if (al == null || al.size() == 0)
                        cge.setId(cge.getId() + 1);
                    else {
                        cge.setId(al.get(0).getId());
                        Query temp = s.createQuery("delete from AvailableGroupEntity where id = ?");
                        temp.setParameter(0, cge.getId());
                        temp.executeUpdate();
                    }
                    cge.setPlayerSum(0);
                }
            }
            s.createQuery("delete from CurrentGroupEntity ").executeUpdate();
            s.save(cge);
            s.save(p);
            t.commit();
            redirect = "waitingroom";
            model.addAttribute("name", name);
        } else {
            redirect = "welcome";
            model.addAttribute("errorMessage", "用户名重复！");
        }
        s.close();
        return redirect;
    }
}
