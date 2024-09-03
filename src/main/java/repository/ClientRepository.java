package repository;

import model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для управления сущностями клиента в базе данных.
 * <p>
 * Этот интерфейс обеспечивает основные CRUD операции (создание, чтение, обновление и удаление)
 * для сущности {@link Client} за счет наследования от {@link JpaRepository}.
 *
 */
public interface ClientRepository extends JpaRepository<Client, Long> {}
