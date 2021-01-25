package dao;

import model.CommandeModifiée;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeModifieeDAO extends JpaRepository<CommandeModifiée, Integer> {
    Iterable<CommandeModifiée> findCommandeModifiéeByIdClient(int idClient);

}
