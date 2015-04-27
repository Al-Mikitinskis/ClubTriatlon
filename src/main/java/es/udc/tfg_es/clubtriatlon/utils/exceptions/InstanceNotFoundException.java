package es.udc.tfg_es.clubtriatlon.utils.exceptions;
/* BSD License */

@SuppressWarnings("serial")
public class InstanceNotFoundException extends InstanceException {

    public InstanceNotFoundException(Object key, String className) {
        super("Instance not found", key, className);
    }
    
}
