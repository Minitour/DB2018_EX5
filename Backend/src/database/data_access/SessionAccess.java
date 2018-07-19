package database.data_access;

import database.GenericAccess;
import model.Session;

/**
 * Created By Tony on 19/07/2018
 */
public class SessionAccess extends GenericAccess<Session> {
    public SessionAccess() { super(Session.class,"TBL_Session"); }
}
