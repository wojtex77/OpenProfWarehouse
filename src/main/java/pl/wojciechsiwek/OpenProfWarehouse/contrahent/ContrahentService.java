package pl.wojciechsiwek.OpenProfWarehouse.contrahent;

import org.springframework.stereotype.Service;

@Service
public class ContrahentService {
    private ContrahentRepository repository;

    public ContrahentService(ContrahentRepository repository) {
        this.repository = repository;
    }

    void addContrahent(Contrahent contrahent) {
        repository.save(contrahent);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
