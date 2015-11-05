databaseChangeLog {
    include(file: 'youpeople-migrations/schema/linkedin-person-auditing/person-auditing-migrations.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/linkedin-person-auditing/10-addcolumn-revtype-person-auditing.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/linkedin-person-auditing/20-addcolumn-locality-industry-connection-picture.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/linkedin-person-auditing/30-removecolumn-industry.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/linkedin-person-auditing/40-remove-notnull-constraints-linkedin-person-aud.groovy', relativeToChangelogFile: false)
}
