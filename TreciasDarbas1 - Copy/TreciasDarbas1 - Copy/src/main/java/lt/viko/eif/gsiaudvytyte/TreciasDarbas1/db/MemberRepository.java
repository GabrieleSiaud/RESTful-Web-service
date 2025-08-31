package lt.viko.eif.gsiaudvytyte.TreciasDarbas1.db;

import lt.viko.eif.gsiaudvytyte.TreciasDarbas1.modal.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on {@link Member} entities.
 * Extends {@link JpaRepository} to inherit standard data access methods.
 */

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByBandId(Long customerId);
}
