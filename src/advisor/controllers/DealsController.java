package advisor.controllers;

import advisor.views.layout.LayoutController;
import advisor.Advisor;

public class DealsController {
    
    public static void addAdmin() {
        LayoutController.showAlert(Advisor.component("admins/AddAdmin"), 500, 500);
    }
}
