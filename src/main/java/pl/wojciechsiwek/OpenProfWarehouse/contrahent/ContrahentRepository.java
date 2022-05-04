package pl.wojciechsiwek.OpenProfWarehouse.contrahent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface ContrahentRepository extends JpaRepository<Contrahent, Integer> {
    boolean existsByAliasEquals(@NonNull String alias);

    boolean existsByFullNameEquals(@NonNull String fullName);


}
