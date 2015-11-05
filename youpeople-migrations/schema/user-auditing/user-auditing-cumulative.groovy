databaseChangeLog {
    include(file: 'youpeople-migrations/schema/user-auditing/user-auditing-migrations.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/user-auditing/10-addcolumn-firstname-lastname-to-user-table.groovy', relativeToChangelogFile: false)
}