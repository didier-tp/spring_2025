package tp.appliSpring.bank.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tp.appliSpring.bank.persistence.entity.OperationEntity;

import java.util.Date;
import java.util.List;

public interface OperationRepository extends JpaRepository<OperationEntity,Long> {

    //A COMPLETER EN TP

}
