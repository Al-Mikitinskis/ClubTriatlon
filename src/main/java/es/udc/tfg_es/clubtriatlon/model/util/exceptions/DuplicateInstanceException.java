package es.udc.tfg_es.clubtriatlon.model.util.exceptions;
/* BSD License */

@SuppressWarnings("serial")
public class DuplicateInstanceException extends InstanceException {

    public DuplicateInstanceException(Object key, String className) {
        super("Duplicate instance", key, className);
    }
    
}
