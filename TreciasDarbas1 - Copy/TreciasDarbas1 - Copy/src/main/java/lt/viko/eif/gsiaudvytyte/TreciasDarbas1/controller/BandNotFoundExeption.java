package lt.viko.eif.gsiaudvytyte.TreciasDarbas1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a {@link lt.viko.eif.gsiaudvytyte.TreciasDarbas1.modal.Band} with a specified ID is not found.
 * This is a custom runtime exception used for signaling 404 Not Found scenarios.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BandNotFoundExeption extends RuntimeException {
    public BandNotFoundExeption(Long bandId) {
        super("Could not find band " + bandId);
    }
}
