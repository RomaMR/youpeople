databaseChangeLog {
    /* schema change log files */
    include(file: 'youpeople-migrations/schema/job/job-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/organization/organization-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/linkedin-person/person-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/searchlog/searchlog-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/user/user-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/right/right-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/common/common-cumulative.groovy', relativeToChangelogFile: false)
	include(file: 'youpeople-migrations/schema/education/education-cumulative.groovy', relativeToChangelogFile: false)
	include(file: 'youpeople-migrations/schema/volunteering/volunteering-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/linkedin-person-auditing/person-auditing-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/revision-info/revision-info-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/job-auditing/job-auditing-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'schema/volunteering-auditing/volunteering-auditing-cumulative.groovy', relativeToChangelogFile: true)
    include(file: 'youpeople-migrations/schema/user-auditing/user-auditing-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/searchlog-auditing/searchlog-auditing-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/organization-auditing/organization-auditing-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'schema/education-auditing/education-auditing-cumulative.groovy', relativeToChangelogFile: true)
    include(file: 'youpeople-migrations/schema/industry/industry-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/industry/group/industry-group-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/industry/industrygroup/industry-industry-group-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/uploaded-person/uploaded-person-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/person/person-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/user-organization/user-organization-cumulative.groovy', relativeToChangelogFile: false)
    /* views */
    include(file: 'youpeople-migrations/schema/views/industry/industry-group-statistic-view-cumulative.groovy', relativeToChangelogFile: false)
    /* procedures */
    include(file: 'youpeople-migrations/schema/procedures/industry-by-group-statistic/industry-by-group-statistic-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/procedures/industry-by-group-and-organization-statistic/industry-by-group-and-organization-statistic' +
            '-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/procedures/industry-group-by-organization-statistic/industry-group-by-organization-statistic' +
            '-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/procedures/industry-by-main-group-and-organization-statistic/industry-by-main-group-and-organization' +
            '-statistic-cumulative.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/procedures/industry-main-group-by-organization-statistic/industry-main-group-by-organization' +
            '-statistic-cumulative.groovy', relativeToChangelogFile: false)
    /* data change log files */
    include(file: 'youpeople-migrations/data/batch/data-batch-cumulative.groovy', relativeToChangelogFile: false)
}
