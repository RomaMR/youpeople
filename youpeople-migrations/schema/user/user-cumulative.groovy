databaseChangeLog {
    include(file: 'youpeople-migrations/schema/user/user-migrations.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/user/10-addcolumn-firstname-lastname-to-user-table.groovy', relativeToChangelogFile: false)
}