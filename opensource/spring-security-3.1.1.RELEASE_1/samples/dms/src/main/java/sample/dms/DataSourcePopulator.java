package sample.dms;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;


/**
* Populates the DMS in-memory database with document and ACL information.
*
* @author Ben Alex
*/
public class DataSourcePopulator implements InitializingBean {
    protected static final int LEVEL_NEGATE_READ = 0;
    protected static final int LEVEL_GRANT_READ = 1;
    protected static final int LEVEL_GRANT_WRITE = 2;
    protected static final int LEVEL_GRANT_ADMIN = 3;
    protected JdbcTemplate template;
    protected DocumentDao documentDao;

    public DataSourcePopulator(DataSource dataSource, DocumentDao documentDao) {
       Assert.notNull(dataSource, "DataSource required");
       Assert.notNull(documentDao, "DocumentDao required");
       this.template = new JdbcTemplate(dataSource);
       this.documentDao = documentDao;
    }

    public void afterPropertiesSet() throws Exception {
        // ACL tables
        template.execute("CREATE TABLE ACL_SID(ID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 100) NOT NULL PRIMARY KEY,PRINCIPAL BOOLEAN NOT NULL,SID VARCHAR_IGNORECASE(100) NOT NULL,CONSTRAINT UNIQUE_UK_1 UNIQUE(SID,PRINCIPAL));");
        template.execute("CREATE TABLE ACL_CLASS(ID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 100) NOT NULL PRIMARY KEY,CLASS VARCHAR_IGNORECASE(100) NOT NULL,CONSTRAINT UNIQUE_UK_2 UNIQUE(CLASS));");
        template.execute("CREATE TABLE ACL_OBJECT_IDENTITY(ID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 100) NOT NULL PRIMARY KEY,OBJECT_ID_CLASS BIGINT NOT NULL,OBJECT_ID_IDENTITY BIGINT NOT NULL,PARENT_OBJECT BIGINT,OWNER_SID BIGINT,ENTRIES_INHERITING BOOLEAN NOT NULL,CONSTRAINT UNIQUE_UK_3 UNIQUE(OBJECT_ID_CLASS,OBJECT_ID_IDENTITY),CONSTRAINT FOREIGN_FK_1 FOREIGN KEY(PARENT_OBJECT)REFERENCES ACL_OBJECT_IDENTITY(ID),CONSTRAINT FOREIGN_FK_2 FOREIGN KEY(OBJECT_ID_CLASS)REFERENCES ACL_CLASS(ID),CONSTRAINT FOREIGN_FK_3 FOREIGN KEY(OWNER_SID)REFERENCES ACL_SID(ID));");
        template.execute("CREATE TABLE ACL_ENTRY(ID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 100) NOT NULL PRIMARY KEY,ACL_OBJECT_IDENTITY BIGINT NOT NULL,ACE_ORDER INT NOT NULL,SID BIGINT NOT NULL,MASK INTEGER NOT NULL,GRANTING BOOLEAN NOT NULL,AUDIT_SUCCESS BOOLEAN NOT NULL,AUDIT_FAILURE BOOLEAN NOT NULL,CONSTRAINT UNIQUE_UK_4 UNIQUE(ACL_OBJECT_IDENTITY,ACE_ORDER),CONSTRAINT FOREIGN_FK_4 FOREIGN KEY(ACL_OBJECT_IDENTITY) REFERENCES ACL_OBJECT_IDENTITY(ID),CONSTRAINT FOREIGN_FK_5 FOREIGN KEY(SID) REFERENCES ACL_SID(ID));");

        // Normal authentication tables
        template.execute("CREATE TABLE USERS(USERNAME VARCHAR_IGNORECASE(50) NOT NULL PRIMARY KEY,PASSWORD VARCHAR_IGNORECASE(50) NOT NULL,ENABLED BOOLEAN NOT NULL);");
        template.execute("CREATE TABLE AUTHORITIES(USERNAME VARCHAR_IGNORECASE(50) NOT NULL,AUTHORITY VARCHAR_IGNORECASE(50) NOT NULL,CONSTRAINT FK_AUTHORITIES_USERS FOREIGN KEY(USERNAME) REFERENCES USERS(USERNAME));");
        template.execute("CREATE UNIQUE INDEX IX_AUTH_USERNAME ON AUTHORITIES(USERNAME,AUTHORITY);");

        // Document management system business tables
        template.execute("CREATE TABLE DIRECTORY(ID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 100) NOT NULL PRIMARY KEY, DIRECTORY_NAME VARCHAR_IGNORECASE(50) NOT NULL, PARENT_DIRECTORY_ID BIGINT)");
        template.execute("CREATE TABLE FILE(ID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 100) NOT NULL PRIMARY KEY, FILE_NAME VARCHAR_IGNORECASE(50) NOT NULL, CONTENT VARCHAR_IGNORECASE(1024), PARENT_DIRECTORY_ID BIGINT)");

        // Populate the authentication and role tables
        template.execute("INSERT INTO USERS VALUES('rod','a564de63c2d0da68cf47586ee05984d7',TRUE);");
        template.execute("INSERT INTO USERS VALUES('dianne','65d15fe9156f9c4bbffd98085992a44e',TRUE);");
        template.execute("INSERT INTO USERS VALUES('scott','2b58af6dddbd072ed27ffc86725d7d3a',TRUE);");
        template.execute("INSERT INTO USERS VALUES('peter','22b5c9accc6e1ba628cedc63a72d57f8',FALSE);");
        template.execute("INSERT INTO USERS VALUES('bill','2b58af6dddbd072ed27ffc86725d7d3a',TRUE);");
        template.execute("INSERT INTO USERS VALUES('bob','2b58af6dddbd072ed27ffc86725d7d3a',TRUE);");
        template.execute("INSERT INTO USERS VALUES('jane','2b58af6dddbd072ed27ffc86725d7d3a',TRUE);");
        template.execute("INSERT INTO AUTHORITIES VALUES('rod','ROLE_USER');");
        template.execute("INSERT INTO AUTHORITIES VALUES('rod','ROLE_SUPERVISOR');");
        template.execute("INSERT INTO AUTHORITIES VALUES('dianne','ROLE_USER');");
        template.execute("INSERT INTO AUTHORITIES VALUES('scott','ROLE_USER');");
        template.execute("INSERT INTO AUTHORITIES VALUES('peter','ROLE_USER');");
        template.execute("INSERT INTO AUTHORITIES VALUES('bill','ROLE_USER');");
        template.execute("INSERT INTO AUTHORITIES VALUES('bob','ROLE_USER');");
        template.execute("INSERT INTO AUTHORITIES VALUES('jane','ROLE_USER');");

        // Now create an ACL entry for the root directory
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("rod", "ignored", AuthorityUtils.createAuthorityList(("ROLE_IGNORED"))));

        addPermission(documentDao, Directory.ROOT_DIRECTORY, "ROLE_USER", LEVEL_GRANT_WRITE);

        // Now go off and create some directories and files for our users
        createSampleData("rod", "koala");
        createSampleData("dianne", "emu");
        createSampleData("scott", "wombat");

    }

    /**
     * Creates a directory for the user, and a series of sub-directories. The
     * root directory is the parent for the user directory. The sub-directories
     * are "confidential" and "shared". The ROLE_USER will be given read and
     * write access to "shared".
     */
    private void createSampleData(String username, String password) {
        Assert.notNull(documentDao, "DocumentDao required");
        Assert.hasText(username, "Username required");

        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);

        try {
            // Set the SecurityContextHolder ThreadLocal so any subclasses
            // automatically know which user is operating
            SecurityContextHolder.getContext().setAuthentication(auth);

            // Create the home directory first
            Directory home = new Directory(username, Directory.ROOT_DIRECTORY);
            documentDao.create(home);
            addPermission(documentDao, home, username, LEVEL_GRANT_ADMIN);
            addPermission(documentDao, home, "ROLE_USER", LEVEL_GRANT_READ);
            createFiles(documentDao, home);

            // Now create the confidential directory
            Directory confid = new Directory("confidential", home);
            documentDao.create(confid);
            addPermission(documentDao, confid, "ROLE_USER", LEVEL_NEGATE_READ);
            createFiles(documentDao, confid);

            // Now create the shared directory
            Directory shared = new Directory("shared", home);
            documentDao.create(shared);
            addPermission(documentDao, shared, "ROLE_USER", LEVEL_GRANT_READ);
            addPermission(documentDao, shared, "ROLE_USER", LEVEL_GRANT_WRITE);
            createFiles(documentDao, shared);
        } finally {
            // Clear the SecurityContextHolder ThreadLocal so future calls are
            // guaranteed to be clean
            SecurityContextHolder.clearContext();
        }
    }

    private void createFiles(DocumentDao documentDao, Directory parent) {
        Assert.notNull(documentDao, "DocumentDao required");
        Assert.notNull(parent, "Parent required");
        int countBeforeInsert = documentDao.findElements(parent).length;
        for (int i = 0; i < 10; i++) {
            File file = new File("file_" + i + ".txt", parent);
            documentDao.create(file);
        }
        Assert.isTrue(countBeforeInsert + 10 == documentDao.findElements(parent).length,
                "Failed to increase count by 10");
    }

    /**
     * Allows subclass to add permissions.
     *
     * @param documentDao
     *            that will presumably offer methods to enable the operation to
     *            be completed
     * @param element
     *            to the subject of the new permissions
     * @param recipient
     *            to receive permission (if it starts with ROLE_ it is assumed
     *            to be a GrantedAuthority, else it is a username)
     * @param level
     *            based on the static final integer fields on this class
     */
    protected void addPermission(DocumentDao documentDao, AbstractElement element, String recipient, int level) {
    }
}
