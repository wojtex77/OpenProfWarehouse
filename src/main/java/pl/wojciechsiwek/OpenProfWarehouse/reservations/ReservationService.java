package pl.wojciechsiwek.OpenProfWarehouse.reservations;

import org.springframework.stereotype.Service;
import pl.wojciechsiwek.OpenProfWarehouse.workspace.Workspace;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Workspace makeReservation(Workspace workspace) {
        List<Reservation> reservations = new ArrayList<>();
        for (int i = 0; i < workspace.getProfileNestedList().size(); i++) {
            if (workspace.getProfileNestedList().get(i).getItemsOnProfile().size() != 0) {
                for (int j = 1; j <= workspace.getProfileNestedList().get(i).getRepetition(); j++) {
                    reservations.add(new Reservation(workspace.getProfileNestedList().get(i).getStockItem().getSignature(), j));
                }
            }
        }
        reservationRepository.saveAll(reservations);
        return workspace;
    }
}
