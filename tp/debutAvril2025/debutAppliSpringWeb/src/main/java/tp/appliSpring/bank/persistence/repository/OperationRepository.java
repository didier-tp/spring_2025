package tp.appliSpring.bank.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tp.appliSpring.bank.persistence.entity.OperationEntity;

import java.util.Date;
import java.util.List;

public interface OperationRepository extends JpaRepository<OperationEntity,Long> {

    @Query("SELECT op FROM OperationEntity op WHERE op.compte.numero=?1 AND op.dateOp between ?2 and ?3")
    List<OperationEntity> findForCompteWithDateBetween(Long numCompte, Date date1, Date date2);

}
