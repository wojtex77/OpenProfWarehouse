package pl.wojciechsiwek.OpenProfWarehouse.parts;

import org.springframework.stereotype.Service;
import pl.wojciechsiwek.OpenProfWarehouse.contrahent.DuplicatedEntryException;

@Service
public class PartService {
    private final PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public void addPart(Part part) throws DuplicatedEntryException {
        if (partRepository.existsByPartNameEquals(part.getPartName())){
            throw new DuplicatedEntryException("Part with such name exists");
        }
        partRepository.save(part);
    }
}
