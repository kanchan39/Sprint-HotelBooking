package cleaning;

import model.Booking;
import java.util.List;

public class ValidationResult {

    private List<Booking> valid;
    private List<Booking> invalid;

    public ValidationResult(List<Booking> valid, List<Booking> invalid) {
        this.valid = valid;
        this.invalid = invalid;
    }

    public List<Booking> getValid() {
        return valid;
    }

    public List<Booking> getInvalid() {
        return invalid;
    }
}