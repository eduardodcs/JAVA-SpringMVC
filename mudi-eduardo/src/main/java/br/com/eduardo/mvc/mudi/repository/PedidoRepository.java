package br.com.eduardo.mvc.mudi.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.eduardo.mvc.mudi.model.Pedido;
import br.com.eduardo.mvc.mudi.model.StatusPedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	@Cacheable("pedidos")
	List<Pedido> findByStatus(StatusPedido status, Pageable paginacao);

	@Query("SELECT p FROM Pedido p JOIN p.user u WHERE u.username = :username ")
	List<Pedido> findAllByUsuario(@Param("username") String username);
	
	@Query("SELECT p FROM Pedido p JOIN p.user u WHERE u.username = :username and p.status = :status ")
	List<Pedido> findByStatusAndUsuario(@Param("status")StatusPedido status, @Param("username")String username);
		
}
