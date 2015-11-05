databaseChangeLog {
    include(file: 'volunteering-migrations.groovy', relativeToChangelogFile: true)
    include(file: '10-addprimarykey-volunteering.groovy', relativeToChangelogFile: true)
    include(file: '20-addcolumn-for-envers-changes-auditing.groovy', relativeToChangelogFile: true)
    include(file: '30-addforeignkey-person-volunteering.groovy', relativeToChangelogFile: true)
}