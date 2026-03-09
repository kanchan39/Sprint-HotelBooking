package cleaning;

import model.Booking;
import java.util.List;

public class DuplicateResult {

    private List<Booking> cleaned;
    private int duplicateCount;

    public DuplicateResult(List<Booking> cleaned, int duplicateCount) {
        this.cleaned = cleaned;
        this.duplicateCount = duplicateCount;
    }

    public List<Booking> getCleaned() {
        return cleaned;
    }

    public int getDuplicateCount() {
        return duplicateCount;
    }
}