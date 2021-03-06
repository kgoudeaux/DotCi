/*
The MIT License (MIT)

Copyright (c) 2014, Groupon, Inc.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */
package com.groupon.jenkins.dynamic.build.cause;

import com.google.common.base.Objects;
import com.groupon.jenkins.git.GitBranch;
import java.util.Collections;
import org.kohsuke.github.GHCommit;
import org.kohsuke.stapler.export.Exported;

public class ManualBuildCause extends BuildCause {

    private final String user;
    private final GitBranch branch;
    private final String sha;
    private final CommitInfo commitInfo;

    public ManualBuildCause(GitBranch branch, GHCommit commit, String user) {
        this.branch = branch;
        this.sha = commit.getSHA1();
        this.user = user;
        this.commitInfo = new CommitInfo(commit,branch);
    }

    @Override
    @Exported(visibility = 3)
    public String getShortDescription() {
        return "Started by: " + user;
    }

    @Override
    public String getSha() {
        return sha;
    }



    @Override
    public String getPusher() {
        return user;
    }

    @Override
    public String getPullRequestNumber() {
        return branch.isPullRequest() ? branch.pullRequestNumber() + "" : null;
    }

    @Override
    public CommitInfo getCommitInfo() {
        return commitInfo;
    }

    @Override
    public String getName() {
        return "MANUAL";
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(branch, sha, user);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ManualBuildCause other = (ManualBuildCause) obj;
        return Objects.equal(this.branch, other.branch) && Objects.equal(this.sha, other.sha) && Objects.equal(this.user, other.user);
    }

    @Override
    public GitBranch getBranch() {
        return branch;
    }

    @Override
    public Iterable<GithubLogEntry> getChangeLogEntries() {
        return Collections.emptyList();
    }
}
