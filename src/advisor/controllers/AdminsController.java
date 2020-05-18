package advisor.controllers;

import advisor.views.layout.LayoutController;
import advisor.Advisor;
import advisor.models.Admin;

public class AdminsController {
    
    public static void addAdmin() {
        LayoutController.showAlert(Advisor.component("admins/AddAdmin"), 500, 500);
    }

    public static void view(Admin admin) {
        LayoutController.route(Advisor.view(Advisor.component("accounts/Account")));
    }
}
