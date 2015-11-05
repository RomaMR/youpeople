databaseChangeLog {
    include(file: 'youpeople-migrations/schema/linkedin-person/person-migrations.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/linkedin-person/10-addprimarykey-person.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/linkedin-person/20-addforeignkey-person-job.groovy', relativeToChangelogFile: false)
    include(file: '30-addcolumn-linkedinprofileid.groovy', relativeToChangelogFile: true)
    include(file: '40-addcolumn-locality-industry-connection-picture.groovy', relativeToChangelogFile: true)
    include(file: 'youpeople-migrations/schema/linkedin-person/50-changecolumn-industry.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/linkedin-person/60-remove-notnull-constraints-linkedin-person.groovy', relativeToChangelogFile: false)
}
