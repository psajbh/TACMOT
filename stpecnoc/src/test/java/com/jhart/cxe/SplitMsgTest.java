package com.jhart.cxe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SplitMsgTest {
    
    private static final String NON_ADMIN_NULL_OR_INACTIVE_LDAP = "The user you attempted to add is associated | with other agencies than the ones you administer. | Please contact an Application Manager to add this user";
    private static final String SITE_ADMIN_CANNOT_ADD_DELETED_USERS = "Site Admins and Local Site Admins may not add deleted users, | please contact an Application Manager";
    // if you split on "\" you will generate a new string with every character.  Need to add delimiter "\\|" to generate on three list entries.
    @Test
    public void testSplittingMessages() throws Exception{
        List<String> list = new ArrayList<>(); 
        String msg = SplitMsgTest.NON_ADMIN_NULL_OR_INACTIVE_LDAP;
        String[] msgSplit = msg.split("\\|");
        for (String s : msgSplit) {
            list.add(s);
        }
        
    }
    

}
