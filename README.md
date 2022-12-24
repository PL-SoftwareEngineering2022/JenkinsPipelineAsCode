### Jenkins Pipeline as Code: A to Z
#### Jenkins Syntax:
1. agent
2. script
3. tools
4. retries and timeouts
5. errors and options
6. timestamps
7. skipDefaultCheckout
8. environment variables
9. when conditions
    - environment
    - equals
    - not
    - expression
    - allOf
    - anyOf
    - branch
    - buildingTag and Tag
    - changelog
    - changeRequest
    - changeset
    - beforeAgent
10. parallel
11. failFast
12. input
13. post
    - always
    - changed
    - fixed
    - regression
    - aborted
    - failure
    - success
    - unstable
    - unsuccessful
    - cleanup
14. buildDiscarder
15. disableConcurrentBuilds
16. overrideIndexTriggers
17. skipStagesAfterUnstable
18. checkoutToSubdirectory
19. newContainerPerStage
20. parameters
21. triggers
    - cron
    - pollSCM
    - upstream

#### References:
1. https://www.jenkins.io/doc/book/pipeline/jenkinsfile/#string-interpolation
2. https://jenkins.io/doc/book/pipeline/syntax/#when
3. https://www.jenkins.io/doc/book/pipeline/jenkinsfile/#string-interpolation
4. https://www.jenkins.io/doc/pipeline/steps/workflow-basic-steps/
5. https://issues.jenkins.io/browse/JENKINS-48556?focusedCommentId=324614&page=com.atlassian.jira.plugin.system.issuetabpanels%3Acomment-tabpanel
6. https://plugins.jenkins.io/timestamper
7. https://plugins.jenkins.io/file-parameters/
8. https://github.com/jenkinsci/pipeline-input-step-plugin/blob/master/docs/fileParameters.md
9. https://www.jenkins.io/doc/pipeline/steps/file-parameters/
10. https://www.jenkins.io/doc/book/pipeline/syntax/#post-conditions
11. https://www.jenkins.io/doc/book/pipeline/syntax/#cron-syntax
12. https://github.com/jenkinsci/jenkins/blob/master/core/src/main/resources/hudson/triggers/TimerTrigger/help-spec.jelly
13. 