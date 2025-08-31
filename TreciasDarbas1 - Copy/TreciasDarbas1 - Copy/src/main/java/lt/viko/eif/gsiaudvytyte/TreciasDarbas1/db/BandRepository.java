package lt.viko.eif.gsiaudvytyte.TreciasDarbas1.db;

import lt.viko.eif.gsiaudvytyte.TreciasDarbas1.modal.Band;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for performing CRUD operations on {@link Band} entities.
 * Extends {@link JpaRepository} to provide standard methods such as save, findAll, findById, delete, etc.
 */
public interface BandRepository extends JpaRepository<Band, Long> {
}
