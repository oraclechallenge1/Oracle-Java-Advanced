package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.domainmodel.Dispensation;
import br.com.fiap.medsave.ProjectMedSave.domainmodel.repositories.DispensationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DispensationServiceImpl implements DispensationService {

    private final DispensationRepository dispensationRepository;

    @Override
    public List<Dispensation> findAll() {
        return this.dispensationRepository.findAll();
    }

    @Override
    public Optional<Dispensation> findById(Long id) {
        return this.dispensationRepository.findById(id);
    }

    @Override
    public Dispensation create(Dispensation dispensation) {
        return this.dispensationRepository.save(dispensation);
    }

    @Override
    public Dispensation partialUpdate(Long id, Dispensation dispensation) {

        Dispensation dispensationFromDatabase = this.dispensationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Dispensation not found for ID: " + id));

        Optional.ofNullable(dispensation.getDateDispensation())
                .ifPresent(dispensationFromDatabase::setDateDispensation);

        Optional.ofNullable(dispensation.getWithdrawalAmount())
                .ifPresent(dispensationFromDatabase::setWithdrawalAmount);

        Optional.ofNullable(dispensation.getSectorDestiny())
                .ifPresent(dispensationFromDatabase::setSectorDestiny);

        Optional.ofNullable(dispensation.getMovementType())
                .ifPresent(dispensationFromDatabase::setMovementType);

        Optional.ofNullable(dispensation.getObservation())
                .ifPresent(dispensationFromDatabase::setObservation);

        Optional.ofNullable(dispensation.getUserSys())
                .ifPresent(dispensationFromDatabase::setUserSys);

        Optional.ofNullable(dispensation.getStockBatch())
                .ifPresent(dispensationFromDatabase::setStockBatch);

        return this.dispensationRepository.save(dispensationFromDatabase);
    }

    @Override
    public boolean existsById(Long id) {
        return this.dispensationRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.dispensationRepository.deleteById(id);
    }

    @Override
    public void delete(Dispensation dispensation) {
        this.dispensationRepository.delete(dispensation);
    }
}
