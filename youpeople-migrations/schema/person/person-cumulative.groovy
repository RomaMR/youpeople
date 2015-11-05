databaseChangeLog {
    include(file: 'youpeople-migrations/schema/person/person-migrations.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/person/10-addforeignkey-to-linkedin-person.groovy', relativeToChangelogFile: false)
}