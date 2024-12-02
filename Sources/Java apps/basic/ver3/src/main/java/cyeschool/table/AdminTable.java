package cyeschool.table;

import entities.Admin;
import entities.Teacher;
import services.AdminService;

import java.util.List;

public class AdminTable {
    public static String generateTable(){
        AdminService adminService = new AdminService();
        List<Admin> adminList=adminService.getAllAdmins(); //Appelle le service pour obtenir tous les comptes admin présents dans la base de données
        StringBuilder table = new StringBuilder();
        for (Admin admin : adminList) {
            table.append("<tr><td>");
            table.append(admin.getFirstname());
            table.append("</td><td>");
            table.append(admin.getLastname());
            table.append("</td><td>");
            table.append(admin.getMail());
            table.append("</td><td>");
            table.append("<button>Check profile</button>");
            table.append("</td></tr>");
        }
        return table.toString();
    }
}
