package pl.wojciechsiwek.OpenProfWarehouse.contrahent;

import org.springframework.stereotype.Service;
import pl.wojciechsiwek.OpenProfWarehouse.parts.PartRepository;

@Service
public class ContrahentService {
    private ContrahentRepository contrahentRepository;
    private PartRepository partRepository;

    public ContrahentService(ContrahentRepository repository, PartRepository partRepository) {
        this.contrahentRepository = repository;
        this.partRepository = partRepository;
    }

    void addContrahent(Contrahent contrahent) throws Exception{
        if (contrahentRepository.existsByAliasEquals(contrahent.getAlias()) || contrahentRepository.existsByFullNameEquals(contrahent.getFullName())){
            throw new DuplicatedEntryException("Alias or Fullname of contrahent exists in DB");
        }
        contrahentRepository.save(contrahent);
    }

    public void delete(int id) throws ContrahentRemoveNotAllowedException {
        if (partRepository.existsByContrahentEquals(contrahentRepository.findById(id).get().getFullName())){
            throw new ContrahentRemoveNotAllowedException("Contrahent in use, con not delete");
        }
        contrahentRepository.deleteById(id);
    }
}
