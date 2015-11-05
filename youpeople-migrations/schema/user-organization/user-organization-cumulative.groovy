databaseChangeLog {
    include(file: 'youpeople-migrations/schema/user-organization/user-organization-migrations.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/user-organization/10-addcolumn-facebook-linkedin-to-user-organization-table.groovy', relativeToChangelogFile: false)
}