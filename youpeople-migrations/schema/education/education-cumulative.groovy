databaseChangeLog {
    include(file: 'education-migrations.groovy', relativeToChangelogFile: true)
    include(file: '10-addprimarykey-education.groovy', relativeToChangelogFile: true)
    include(file: '20-addcolumn-for-envers-changes-auditing.groovy', relativeToChangelogFile: true)
    include(file: '30-addforeignkey-person-education.groovy', relativeToChangelogFile: true)
}