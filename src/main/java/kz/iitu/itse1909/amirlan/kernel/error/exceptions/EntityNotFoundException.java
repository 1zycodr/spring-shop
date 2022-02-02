package kz.iitu.itse1909.amirlan.kernel.error.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityId) {
        super(String.format("Entity with id %s not found", entityId));
    }
}
