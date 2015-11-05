databaseChangeLog {
    include(file: 'youpeople-migrations/schema/organization/organization-migrations.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/organization/10-addprimarykey-organization.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/organization/20-addforeignkey-organization-job.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/organization/30-addcolumn-picture-organization.groovy', relativeToChangelogFile: false)
}