import dao.CommandeModifieeDAO;
import model.CommandeModifiée;
import model.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    CommandeModifieeDAO cm;


    @PostMapping("/commandemodifiee/{idcommandeAncienne}")
    public void modifierProduitsApresCommande(@PathVariable int idcommandeAncienne, @RequestBody List<Produit> ps){
            CommandeModifiée c = new CommandeModifiée();
            c.setIdCommande(idcommandeAncienne);
            c.setProduits(ps);
            cm.save(c);
    }

}
