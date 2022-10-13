package org.jenkinsci.backend.ircbot;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.jvnet.hudson.test.Issue;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumingThat;

/**
 *
 * @author Oleg Nenashev <o.v.nenashev@gmail.com>
 */
public class IrcBotBuildInfoTest {

    @Issue("INFRA-135")
    public void testVersionInfoStub() throws IOException {
        IrcBotBuildInfo info = IrcBotBuildInfo.readResourceFile("/versionInfo_test.properties");
        assertEquals("a", info.getBuildNumber());
        assertEquals("b", info.getBuildDate());
        assertEquals("c", info.getBuildID());
        assertEquals("d", info.getBuildURL());
        assertEquals("e", info.getGitCommit());
        System.out.println(info);
    }

    /**
     * Reads the real version info.
     * This test is expected to work in https://ci.jenkins-ci.org/job/infra_ircbot/ job only.
     * @throws IOException
     */
    @Test
    @Issue("INFRA-135")
    public void testVersionInfoReal() throws IOException {
        InputStream istream = IrcBotBuildInfo.class.getResourceAsStream("/versionInfo.properties");
        assumingThat("This test is expected to work in https://ci.jenkins-ci.org/job/infra_ircbot/ job only", istream, not(nullValue()));
        IrcBotBuildInfo info = IrcBotBuildInfo.readResourceFile("/versionInfo.properties");
        System.out.println(info);
    }
}
