package com.asanarebel.githubapp.functionaltests.test

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.asanarebel.githubapp.R
import com.asanarebel.githubapp.functionaltests.rule.NeedsMockWebServer
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchReposTest : BaseGithubTest() {

    @Test
    fun shouldDisplayTitle() {
        baseScreen.shouldDisplayTitle(
                InstrumentationRegistry.getTargetContext().applicationContext
                        .getString(R.string.fragment_search_title))
    }


    @Test
    @Ignore
    @NeedsMockWebServer(setupMethod = "shouldDisplayAllReposFromSuccessfulResponse_setupMockWebServer")
    fun shouldDisplayAllReposFromSuccessfulResponse() {
        //before search
        baseScreen.shouldDisplayExpectedAmountOfRepos(0)
        //search
        baseScreen
                .searchWithQuery("tejvyn")
                .shouldDisplayExpectedAmountOfRepos(1)
                .shouldDisplayRepoWithName("tejvyn")
                .shouldDisplayRepoWitForksCount("Forks count: 0")
                .shouldDisplayRepoWithDescription("testable repo")
    }


    /**
     * @see .shouldDisplayAllReposFromSuccessfulResponse
     */
    //todo:load from JSON file
    fun shouldDisplayAllReposFromSuccessfulResponse_setupMockWebServer(mockWebServer: MockWebServer) {
        mockWebServer.enqueue(MockResponse().setBody("" +
                "{  \n" +
                "   \"total_count\":1,\n" +
                "   \"incomplete_results\":false,\n" +
                "   \"items\":[  \n" +
                "      {  \n" +
                "         \"id\":26093876,\n" +
                "         \"node_id\":\"MDEwOlJlcG9zaXRvcnkyNjA5Mzg3Ng==\",\n" +
                "         \"name\":\"tejvyn\",\n" +
                "         \"full_name\":\"zearen/tejvyn\",\n" +
                "         \"owner\":{  \n" +
                "            \"login\":\"zearen\",\n" +
                "            \"id\":678958,\n" +
                "            \"node_id\":\"MDQ6VXNlcjY3ODk1OA==\",\n" +
                "            \"avatar_url\":\"https://avatars1.githubusercontent.com/u/678958?v=4\",\n" +
                "            \"gravatar_id\":\"\",\n" +
                "            \"url\":\"https://api.github.com/users/zearen\",\n" +
                "            \"html_url\":\"https://github.com/zearen\",\n" +
                "            \"followers_url\":\"https://api.github.com/users/zearen/followers\",\n" +
                "            \"following_url\":\"https://api.github.com/users/zearen/following{/other_user}\",\n" +
                "            \"gists_url\":\"https://api.github.com/users/zearen/gists{/gist_id}\",\n" +
                "            \"starred_url\":\"https://api.github.com/users/zearen/starred{/owner}{/repo}\",\n" +
                "            \"subscriptions_url\":\"https://api.github.com/users/zearen/subscriptions\",\n" +
                "            \"organizations_url\":\"https://api.github.com/users/zearen/orgs\",\n" +
                "            \"repos_url\":\"https://api.github.com/users/zearen/repos\",\n" +
                "            \"events_url\":\"https://api.github.com/users/zearen/events{/privacy}\",\n" +
                "            \"received_events_url\":\"https://api.github.com/users/zearen/received_events\",\n" +
                "            \"type\":\"User\",\n" +
                "            \"site_admin\":false\n" +
                "         },\n" +
                "         \"private\":false,\n" +
                "         \"html_url\":\"https://github.com/zearen/tejvyn\",\n" +
                "         \"description\":\"testable repo\",\n" +
                "         \"fork\":false,\n" +
                "         \"url\":\"https://apigithub.com/repos/zearen/tejvyn\",\n" +
                "         \"forks_url\":\"https://api.github.com/repos/zearen/tejvyn/forks\",\n" +
                "         \"keys_url\":\"https://api.github.com/repos/zearen/tejvyn/keys{/key_id}\",\n" +
                "         \"collaborators_url\":\"https://api.github.com/repos/zearen/tejvyn/collaborators{/collaborator}\",\n" +
                "         \"teams_url\":\"https://api.github.com/repos/zearen/tejvyn/teams\",\n" +
                "         \"hooks_url\":\"https://api.github.com/repos/zearen/tejvyn/hooks\",\n" +
                "         \"issue_events_url\":\"https://api.github.com/repos/zearen/tejvyn/issues/events{/number}\",\n" +
                "         \"events_url\":\"https://api.github.com/repos/zearen/tejvyn/events\",\n" +
                "         \"assignees_url\":\"https://api.github.com/repos/zearen/tejvyn/assignees{/user}\",\n" +
                "         \"branches_url\":\"https://api.github.com/repos/zearen/tejvyn/branches{/branch}\",\n" +
                "         \"tags_url\":\"https://api.github.com/repos/zearen/tejvyn/tags\",\n" +
                "         \"blobs_url\":\"https://api.github.com/repos/zearen/tejvyn/git/blobs{/sha}\",\n" +
                "         \"git_tags_url\":\"https://api.github.com/repos/zearen/tejvyn/git/tags{/sha}\",\n" +
                "         \"git_refs_url\":\"https://api.github.com/repos/zearen/tejvyn/git/refs{/sha}\",\n" +
                "         \"trees_url\":\"https://api.github.com/repos/zearen/tejvyn/git/trees{/sha}\",\n" +
                "         \"statuses_url\":\"https://api.github.com/repos/zearen/tejvyn/statuses/{sha}\",\n" +
                "         \"languages_url\":\"https://api.github.com/repos/zearen/tejvyn/languages\",\n" +
                "         \"stargazers_url\":\"https://api.github.com/repos/zearen/tejvyn/stargazers\",\n" +
                "         \"contributors_url\":\"https://api.github.com/repos/zearen/tejvyn/contributors\",\n" +
                "         \"subscribers_url\":\"https://api.github.com/repos/zearen/tejvyn/subscribers\",\n" +
                "         \"subscription_url\":\"https://api.github.com/repos/zearen/tejvyn/subscription\",\n" +
                "         \"commits_url\":\"https://api.github.com/repos/zearen/tejvyn/commits{/sha}\",\n" +
                "         \"git_commits_url\":\"https://api.github.com/repos/zearen/tejvyn/git/commits{/sha}\",\n" +
                "         \"comments_url\":\"https://api.github.com/repos/zearen/tejvyn/comments{/number}\",\n" +
                "         \"issue_comment_url\":\"https://api.github.com/repos/zearen/tejvyn/issues/comments{/number}\",\n" +
                "         \"contents_url\":\"https://api.github.com/repos/zearen/tejvyn/contents/{+path}\",\n" +
                "         \"compare_url\":\"https://api.github.com/repos/zearen/tejvyn/compare/{base}...{head}\",\n" +
                "         \"merges_url\":\"https://api.github.com/repos/zearen/tejvyn/merges\",\n" +
                "         \"archive_url\":\"https://api.github.com/repos/zearen/tejvyn/{archive_format}{/ref}\",\n" +
                "         \"downloads_url\":\"https://api.github.com/repos/zearen/tejvyn/downloads\",\n" +
                "         \"issues_url\":\"https://api.github.com/repos/zearen/tejvyn/issues{/number}\",\n" +
                "         \"pulls_url\":\"https://api.github.com/repos/zearen/tejvyn/pulls{/number}\",\n" +
                "         \"milestones_url\":\"https://api.github.com/repos/zearen/tejvyn/milestones{/number}\",\n" +
                "         \"notifications_url\":\"https://api.github.com/repos/zearen/tejvyn/notifications{?since,all,participating}\",\n" +
                "         \"labels_url\":\"https://api.github.com/repos/zearen/tejvyn/labels{/name}\",\n" +
                "         \"releases_url\":\"https://api.github.com/repos/zearen/tejvyn/releases{/id}\",\n" +
                "         \"deployments_url\":\"https://api.github.com/repos/zearen/tejvyn/deployments\",\n" +
                "         \"created_at\":\"2014-11-02T21:33:46Z\",\n" +
                "         \"updated_at\":\"2016-08-09T18:52:00Z\",\n" +
                "         \"pushed_at\":\"2016-08-09T18:51:58Z\",\n" +
                "         \"git_url\":\"git://github.com/zearen/tejvyn.git\",\n" +
                "         \"ssh_url\":\"git@github.com:zearen/tejvyn.git\",\n" +
                "         \"clone_url\":\"https://github.com/zearen/tejvyn.git\",\n" +
                "         \"svn_url\":\"https://github.com/zearen/tejvyn\",\n" +
                "         \"homepage\":null,\n" +
                "         \"size\":7182,\n" +
                "         \"stargazers_count\":0,\n" +
                "         \"watchers_count\":0,\n" +
                "         \"language\":\"HTML\",\n" +
                "         \"has_issues\":true,\n" +
                "         \"has_projects\":true,\n" +
                "         \"has_downloads\":true,\n" +
                "         \"has_wiki\":true,\n" +
                "         \"has_pages\":false,\n" +
                "         \"forks_count\":0,\n" +
                "         \"mirror_url\":null,\n" +
                "         \"archived\":false,\n" +
                "         \"open_issues_count\":0,\n" +
                "         \"license\":{  \n" +
                "            \"key\":\"other\",\n" +
                "            \"name\":\"Other\",\n" +
                "            \"spdx_id\":null,\n" +
                "            \"url\":null,\n" +
                "            \"node_id\":\"MDc6TGljZW5zZTA=\"\n" +
                "         },\n" +
                "         \"forks\":0,\n" +
                "         \"open_issues\":0,\n" +
                "         \"watchers\":0,\n" +
                "         \"default_branch\":\"master\",\n" +
                "         \"score\":19.895498\n" +
                "      }\n" +
                "   ]\n" +
                "}"
        ))
    }

}