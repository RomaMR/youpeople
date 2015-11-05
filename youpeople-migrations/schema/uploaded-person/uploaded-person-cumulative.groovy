databaseChangeLog {
    include(file: 'youpeople-migrations/schema/uploaded-person/uploaded-person-migrations.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/uploaded-person/10-addcolumn-organization-to-uploaded-person.groovy', relativeToChangelogFile: false)
}